package tv.olaris.android

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tv.olaris.android.di.apolloModule
import tv.olaris.android.di.coroutineModule
import tv.olaris.android.di.okHttpModule
import tv.olaris.android.di.serverDatabaseModule
import tv.olaris.android.tool.CustomKoinLogger

class OlarisApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setLogging()
        setDI()
    }

    private fun setDI() {
        startKoin {
            CustomKoinLogger
            androidContext(this@OlarisApplication)
            modules(listOf(
                coroutineModule,
                serverDatabaseModule,
                // TODO: These modules will likely be moved elsewhere, need to be loaded after we
                //  receive server list and also need to unload when changes are made to server list
                okHttpModule(listOf()),
                apolloModule(listOf()),
            ))
        }
    }

    private fun setLogging() {
//        if (BuildConfig.DEBUG) {
//            // Debug build
//
//            // disable firebase crashlytics
//            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false)
//            // init napier
            Napier.base(DebugAntilog())
//        } else {
//            // Others(Release build)
//
//            // enable firebase crashlytics
//            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
//            // init napier
//            Napier.base(CrashlyticsAntilog(this))
//        }
    }
}
