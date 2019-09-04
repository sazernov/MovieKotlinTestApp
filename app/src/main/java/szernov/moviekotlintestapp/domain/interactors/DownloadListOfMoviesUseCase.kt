package szernov.moviekotlintestapp.domain.interactors

import szernov.moviekotlintestapp.domain.model.Movie

interface DownloadListOfMoviesUseCase {

    interface ResponseListener {
        fun showMovies(movies: List<Movie>)
    }

    fun execute()
    fun setListener(listener: ResponseListener)
}