package tv.olaris.android.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val coroutineModule = module {
    factory { SupervisorJob() }
    factory { CoroutineScope(Dispatchers.IO + get<Job>()) }
}
