package szernov.moviekotlintestapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import szernov.moviekotlintestapp.domain.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}