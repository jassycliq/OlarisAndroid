package tv.olaris.android.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "servers")
data class Server constructor(
    var url: String,
    var username: String,
    var password: String,
    var name: String,
    var currentJWT: String,
    var version: String,
    @ColumnInfo(defaultValue = "0")
    var isCurrent: Boolean,
    @ColumnInfo(defaultValue = "false")
    var isOnline: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
