package szernov.moviekotlintestapp.presentation.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_list.*
import szernov.moviekotlintestapp.R
import szernov.moviekotlintestapp.domain.model.Movie
import szernov.moviekotlintestapp.presentation.movieDetails.MovieDetailsFragment
import javax.inject.Inject

class MovieListFragment : DaggerFragment(), MovieListContract.View {

    @Inject
    lateinit var mPresenter: MovieListContract.Presenter

    private lateinit var mAdapter: MovieListAdapter

    companion object {
        const val SAVED_MOVIE_ARRAY = "SAVED_MOVIE_ARRAY"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_movie_list, container, false)
        ButterKnife.bind(this, rootView)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this)
        val showDesc = {
                movie: Movie ->
            if(fragmentManager!=null){
                fragmentManager!!.beginTransaction()
                    .add(R.id.main_container, MovieDetailsFragment.newInstance(movie))
                    .addToBackStack(null)
                    .commit()
            }
        }
        mAdapter = MovieListAdapter(showDesc)
        movieListRV.adapter = mAdapter
        movieListRV.layoutManager = LinearLayoutManager(context)
        movieListRV.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))

        if(savedInstanceState!=null)
            showMovies(savedInstanceState.getParcelableArrayList(SAVED_MOVIE_ARRAY)!!)
        else
            mPresenter.getMovies()
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(SAVED_MOVIE_ARRAY, ArrayList(mAdapter.mMoviesList))
        super.onSaveInstanceState(outState)
    }


    override fun showMovies(movies: List<Movie>) {
        mAdapter.setMovieList(movies)
    }

    override fun progressVisibility(b: Boolean) {
        progressBar.visibility = if (b) View.VISIBLE else View.GONE
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }
}