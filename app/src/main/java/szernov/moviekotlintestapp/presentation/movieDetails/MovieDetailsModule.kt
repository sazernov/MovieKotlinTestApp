package szernov.moviekotlintestapp.presentation.movieDetails

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieDetailsModule{

    @ContributesAndroidInjector
    internal abstract fun movieDetailsFragment() : MovieDetailsFragment
}