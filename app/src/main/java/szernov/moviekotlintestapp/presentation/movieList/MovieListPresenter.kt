package szernov.moviekotlintestapp.presentation.movieList

import szernov.moviekotlintestapp.domain.interactors.DownloadListOfMoviesUseCase
import szernov.moviekotlintestapp.domain.interactors.ErrorOrProgressUseCase
import szernov.moviekotlintestapp.domain.model.Movie
import javax.inject.Inject

class MovieListPresenter() : MovieListContract.Presenter,
    DownloadListOfMoviesUseCase.ResponseListener,
    ErrorOrProgressUseCase.CallbackListener{

    private var mView: MovieListContract.View? = null

    private lateinit var downloadListOfMoviesUseCase: DownloadListOfMoviesUseCase
    private lateinit var errorOrProgressUseCase: ErrorOrProgressUseCase


    @Inject
    constructor(
        downloadListOfMoviesUseCase: DownloadListOfMoviesUseCase,
        errorOrProgressUseCase: ErrorOrProgressUseCase
    ) : this(){
        this.downloadListOfMoviesUseCase = downloadListOfMoviesUseCase
        this.errorOrProgressUseCase = errorOrProgressUseCase
        downloadListOfMoviesUseCase.setListener(this)
        errorOrProgressUseCase.setListener(this)
    }

    override fun showMovies(movies: List<Movie>) {
        mView?.showMovies(movies)
    }

    override fun setVisibilityProgress(isVisible: Boolean) {
        mView?.progressVisibility(isVisible)
    }

    override fun showError(message: String) {
        mView?.showError(message)
    }

    override fun getMovies() {
        downloadListOfMoviesUseCase.execute()
    }

    override fun attachView(view: MovieListContract.View) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}