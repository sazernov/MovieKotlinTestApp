package szernov.moviekotlintestapp

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import szernov.moviekotlintestapp.di.DaggerAppComponent

class MovieApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }


}