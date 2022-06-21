package tv.olaris.android.di

import io.github.aakira.napier.Napier
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import org.koin.core.qualifier.named
import org.koin.dsl.module
import tv.olaris.android.data.model.LoginRequest
import tv.olaris.android.data.model.Server
import tv.olaris.android.tool.CustomHttpLoggingInterceptor
import java.net.URL
import java.util.concurrent.TimeUnit

const val No_AUTH_OKHTTP_CLIENT = "NoAuthOkHttpClient"
const val AUTH_OKHTTP_CLIENT = "AuthOkHttpClient"

fun okHttpModule(serverList: List<Server>) = module {
    for (server in serverList) {

        factory(named("${server.id} $No_AUTH_OKHTTP_CLIENT")) {
            okHttpClient
        }

        factory(named("${server.id}")) {
            TokenRefreshAuthenticator(
                okhttpClient = get<OkHttpClient>(named("${server.id} $No_AUTH_OKHTTP_CLIENT")),
                url = server.url,
                username = server.username,
                password = server.password,
            )
        }

        factory(named("${server.id} $AUTH_OKHTTP_CLIENT")) {
            okHttpClientAuth(
                okhttpClient = get<OkHttpClient>(named("${server.id} $No_AUTH_OKHTTP_CLIENT")),
                tokenRefreshAuth = get<TokenRefreshAuthenticator>(named("${server.id}")),
            )
        }
    }
}

val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(2, TimeUnit.SECONDS)
    .writeTimeout(3, TimeUnit.SECONDS)
    .readTimeout(10, TimeUnit.SECONDS)
    .addNetworkInterceptor(CustomHttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    .build()

fun okHttpClientAuth(okhttpClient: OkHttpClient, tokenRefreshAuth: TokenRefreshAuthenticator) =
    okhttpClient.newBuilder()
        .authenticator(tokenRefreshAuth)
        .build()

class TokenRefreshAuthenticator(
    private val okhttpClient: OkHttpClient,
    private val url: String,
    private val username: String,
    private val password: String,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? = when {
        response.retryCount > 2 -> null
        else -> response.createSignedRequest()
    }

    private fun Response.createSignedRequest(): Request? = try {
        request.signWithToken(getJwt())
    } catch (error: Throwable) {
        Napier.e("Failed to re-sign request")
        null
    }

    private val Response.retryCount: Int
        get() {
            var currentResponse = priorResponse
            var result = 0
            while (currentResponse != null) {
                result++
                currentResponse = currentResponse.priorResponse
            }
            return result
        }

    private fun Request.signWithToken(jwt: String) =
        newBuilder()
            .header("Authorization", "Bearer $jwt")
            .build()

    private fun getJwt(): String {
        val loginRequest = LoginRequest(username = username, password = password)
        val mediaTypeMarkdown = "application/json; charset=utf-8".toMediaType()
        val requestBody = Json.encodeToString(loginRequest).toRequestBody(mediaTypeMarkdown)

        var result: String? = null
        try {
            // Create URL
            val _url = URL(url)
            // Build request
            val request = Request.Builder()
                .url(_url)
                .post(requestBody)
                .build()
            // Execute request
            val response = okhttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                result = response.body?.string()
            } else {
                throw IOException(response.code.toString())
            }
        } catch (err: Error) {
            Napier.e("Error when executing get request: ${err.localizedMessage}")
        }
        return result ?: error("Unknown error!")
    }
}
