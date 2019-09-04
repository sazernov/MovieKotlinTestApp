package szernov.moviekotlintestapp.presentation.movieList

import szernov.moviekotlintestapp.domain.model.Movie
import szernov.moviekotlintestapp.presentation.base.BasePresenter

interface MovieListContract {

    interface Presenter: BasePresenter<View>{
        fun getMovies()
    }


    interface View {
        fun showMovies(movies: List<Movie>)
        fun progressVisibility(b: Boolean)
        fun showError(error: String)
    }
}