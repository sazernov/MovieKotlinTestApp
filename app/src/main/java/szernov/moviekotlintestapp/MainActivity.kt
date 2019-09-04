package szernov.moviekotlintestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import szernov.moviekotlintestapp.presentation.movieList.MovieListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if(savedInstanceState!=null)
            return
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, MovieListFragment())
            .commit()

    }



}
