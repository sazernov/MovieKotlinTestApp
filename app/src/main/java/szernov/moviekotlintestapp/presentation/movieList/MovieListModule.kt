package szernov.moviekotlintestapp.presentation.movieList

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import szernov.moviekotlintestapp.presentation.movieList.MovieListFragment
import szernov.moviekotlintestapp.presentation.movieList.MovieListPresenter

@Module
abstract class MovieListModule {

    @ContributesAndroidInjector
    internal abstract fun movieListFragment(): MovieListFragment

    @Binds
    abstract fun movieListPresenter(presenter: MovieListPresenter): MovieListContract.Presenter
}