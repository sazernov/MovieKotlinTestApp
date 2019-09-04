package szernov.moviekotlintestapp.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import szernov.moviekotlintestapp.MovieApp
import szernov.moviekotlintestapp.data.DataModule
import szernov.moviekotlintestapp.presentation.movieDetails.MovieDetailsModule
import szernov.moviekotlintestapp.presentation.movieList.MovieListModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
                        AndroidSupportInjectionModule::class,
                        MovieListModule::class,
                        DataModule::class,
                        MovieDetailsModule::class])
interface AppComponent : AndroidInjector<MovieApp>{

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance applicationContext: Application): AppComponent
    }

}