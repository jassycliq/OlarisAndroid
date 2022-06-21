package tv.olaris.android.data.database.server

import androidx.room.Database
import androidx.room.RoomDatabase
import tv.olaris.android.data.model.Server

@Database(entities = [Server::class], version = 1, exportSchema = true)
abstract class ServerDatabase : RoomDatabase() {
    abstract fun serverDoa(): ServerDoa
}
