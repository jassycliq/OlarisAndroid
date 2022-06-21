package tv.olaris.android.di

import android.content.Context
import androidx.room.Room
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import tv.olaris.android.data.database.server.ServerDatabase
import tv.olaris.android.data.database.server.ServerDoa
import tv.olaris.android.data.repository.ServersRepository

val serverDatabaseModule = module {
    singleOf(::getDatabase)
    singleOf(::provideDao)
    singleOf(::ServersRepository)
}

fun getDatabase(context: Context): ServerDatabase {
    return Room.databaseBuilder(
        context,
        ServerDatabase::class.java,
        "servers_db",
    )
        .fallbackToDestructiveMigration()
        .build()
}

fun provideDao(database: ServerDatabase): ServerDoa {
    return database.serverDoa()
}
