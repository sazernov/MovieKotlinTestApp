package szernov.moviekotlintestapp.data.repository

import android.annotation.SuppressLint
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import szernov.moviekotlintestapp.data.database.MoviesDatabase
import szernov.moviekotlintestapp.data.database.SharedPref
import szernov.moviekotlintestapp.data.network.MovieApi
import szernov.moviekotlintestapp.domain.interactors.DownloadListOfMoviesUseCase
import szernov.moviekotlintestapp.domain.interactors.ErrorOrProgressUseCase
import szernov.moviekotlintestapp.domain.model.Movie
import szernov.moviekotlintestapp.domain.model.MovieApiResult
import szernov.moviekotlintestapp.domain.repository.MovieRepository
import java.util.*

class MovieRepositoryImp(
    private var mMovieApi: MovieApi,
    private var mSharedPref: SharedPref,
    private var mMoviesDatabase: MoviesDatabase
) : MovieRepository{

    private var downloadListener: DownloadListOfMoviesUseCase.ResponseListener? = null

    private var errorOrProgressListener: ErrorOrProgressUseCase.CallbackListener? = null


    override fun setDownloadListener(listener: DownloadListOfMoviesUseCase.ResponseListener) {
        downloadListener = listener
    }

    override fun setErrorOrProgressListener(listener: ErrorOrProgressUseCase.CallbackListener) {
        errorOrProgressListener = listener
    }


    @SuppressLint("CheckResult")
    override fun downloadMovie() {
        errorOrProgressListener?.setVisibilityProgress(true)
        getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({items ->
                errorOrProgressListener?.setVisibilityProgress(false)
                downloadListener?.showMovies(items)
            }) { throwable ->
                errorOrProgressListener?.setVisibilityProgress(false)
                errorOrProgressListener?.showError(throwable.message ?: "")}
    }


    private fun getMovies() : Single<List<Movie>>{
        if(updateFromNetwork()){
            return mMovieApi.getMovies()
                .flatMap { response ->
                    if(response.isSuccessful){
                        val items = response.body()!!.movies
                        mSharedPref.saveLastUpdatedTime(getCurrentTime())
                        mMoviesDatabase.movieDao().insertMovies(items)
                        return@flatMap Single.just(items)
                    } else
                        return@flatMap mMoviesDatabase.movieDao().getMovies()
                }
        } else
            return mMoviesDatabase.movieDao().getMovies()
    }


    private fun getCurrentTime(): Long {
        return Calendar.getInstance().timeInMillis
    }

    private fun updateFromNetwork(): Boolean {
        val lastUpdated = mSharedPref.lastUpdatedTime
        if (lastUpdated < 0)
            return true
        val currentTime = Calendar.getInstance().timeInMillis
        return currentTime - lastUpdated > 1000 * 60 * 60 * 2 // more than 2 hours
    }


}