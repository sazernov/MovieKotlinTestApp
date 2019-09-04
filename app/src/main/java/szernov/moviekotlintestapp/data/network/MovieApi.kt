package szernov.moviekotlintestapp.data.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import szernov.moviekotlintestapp.domain.model.MovieApiResult

interface MovieApi {

    @GET("popular")
    fun getMovies(): Single<Response<MovieApiResult>>

}