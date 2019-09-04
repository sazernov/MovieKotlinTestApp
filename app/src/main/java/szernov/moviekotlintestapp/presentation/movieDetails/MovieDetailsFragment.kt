package szernov.moviekotlintestapp.presentation.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_details.*
import szernov.moviekotlintestapp.BuildConfig
import szernov.moviekotlintestapp.R
import szernov.moviekotlintestapp.domain.model.Movie
import szernov.moviekotlintestapp.presentation.utils.GlideApp

class MovieDetailsFragment : DaggerFragment() {


    companion object{
        private val ARG_MOVIE = "ARG_MOVIE"

        fun newInstance(movie: Movie) : MovieDetailsFragment{

            val fragment = MovieDetailsFragment()
            val args = Bundle()
            args.putParcelable(ARG_MOVIE, movie)

            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie: Movie? = arguments?.getParcelable(ARG_MOVIE)
        movieTitle.text = movie?.title
        movieDescription.text = movie?.overview
        GlideApp.with(moviePoster)
            .load(BuildConfig.IMAGES_URL + movie?.posterPath)
            .into(moviePoster)
    }

}