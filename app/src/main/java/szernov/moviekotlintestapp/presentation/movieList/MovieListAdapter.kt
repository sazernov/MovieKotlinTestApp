package szernov.moviekotlintestapp.presentation.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import szernov.moviekotlintestapp.BuildConfig
import szernov.moviekotlintestapp.R
import szernov.moviekotlintestapp.domain.model.Movie
import szernov.moviekotlintestapp.presentation.utils.GlideApp

class MovieListAdapter(val showDesc: (Movie) -> Unit) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    var mMoviesList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return mMoviesList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(mMoviesList[position])
        holder.itemView.setOnClickListener { showDesc(mMoviesList[position]) }
    }


    fun setMovieList(list: List<Movie>){
        mMoviesList = list as ArrayList<Movie>
        notifyDataSetChanged()
    }

    class MovieViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindView(item: Movie){
            tvMovieTitle.text = item.title
            GlideApp.with(ivPoster)
                .load(BuildConfig.IMAGES_URL + item.posterPath)
                .into(ivPoster)
        }
    }
}