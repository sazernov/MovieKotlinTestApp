package szernov.moviekotlintestapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(indices = [Index(value = ["id"], unique = true)])
data class Movie(

    @PrimaryKey
    val id: Int? = null,

    val title: String? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    val overview: String? = null
) : Parcelable