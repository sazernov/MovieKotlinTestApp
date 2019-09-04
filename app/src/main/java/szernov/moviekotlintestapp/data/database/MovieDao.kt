package szernov.moviekotlintestapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import szernov.moviekotlintestapp.domain.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM movie")
    fun getMovies(): Single<List<Movie>>
}
