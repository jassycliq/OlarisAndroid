package tv.olaris.android.tool

import io.github.aakira.napier.Napier
import org.koin.core.logger.Level
import org.koin.core.logger.Level.*
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE

object CustomKoinLogger : Logger() {
    override fun log(level: Level, msg: MESSAGE) {
        when (level) {
            DEBUG -> Napier.d(msg)
            INFO -> Napier.i(msg)
            ERROR -> Napier.e(msg)
            NONE -> Napier.v(msg)
        }
    }
}
