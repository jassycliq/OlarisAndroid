package tv.olaris.android.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import tv.olaris.android.data.model.Server

fun apolloModule(servers: List<Server>) = module {
    for (server in servers) {
        factory<ApolloClient>(named("${server.id}")) {
            olarisClient(
                okHttpClient = get<OkHttpClient>(named("${server.id} $AUTH_OKHTTP_CLIENT")),
                serverUrl = server.url,
            )
        }
    }
}

fun olarisClient(okHttpClient: OkHttpClient, serverUrl: String) = ApolloClient.Builder()
    .okHttpClient(okHttpClient)
    .serverUrl(serverUrl)
    .build()
