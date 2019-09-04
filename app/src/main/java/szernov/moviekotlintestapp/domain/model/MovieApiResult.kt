package szernov.moviekotlintestapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieApiResult(@SerializedName("results")
                          val movies: List<Movie>)