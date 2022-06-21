package tv.olaris.android.data.database.server

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import tv.olaris.android.data.model.Server

@Dao
interface ServerDoa {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertServer(server: Server)

    @Query("select * from servers")
    fun getServers(): Flow<List<Server>>

    @Query("select * from servers")
    suspend fun getServersOnce(): List<Server>

    @get:Query("select count(*) from servers")
    val serverCount: Int

    @Query("select * from servers WHERE id = :id")
    suspend fun getServerById(id: Int): Server

    @Query("select * from servers WHERE isCurrent = 1")
    suspend fun getCurrentServer(): Server?

    @Update(entity = Server::class)
    suspend fun update(obj: Server)

    @Delete
    suspend fun delete(model: Server)
}
