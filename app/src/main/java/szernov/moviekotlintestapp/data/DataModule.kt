package szernov.moviekotlintestapp.data

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import szernov.moviekotlintestapp.BuildConfig
import szernov.moviekotlintestapp.data.database.MoviesDatabase
import szernov.moviekotlintestapp.data.database.SharedPref
import szernov.moviekotlintestapp.data.network.MovieApi
import szernov.moviekotlintestapp.data.repository.MovieRepositoryImp
import szernov.moviekotlintestapp.domain.interactors.DownloadListOfMoviesUseCase
import szernov.moviekotlintestapp.domain.interactors.ErrorOrProgressUseCase
import szernov.moviekotlintestapp.domain.interactors.executers.DownloadListOfMoviesUseCaseIml
import szernov.moviekotlintestapp.domain.interactors.executers.ErrorOrProgressUseCaseImp
import szernov.moviekotlintestapp.domain.repository.MovieRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {


    @Provides
    internal fun provideDownloadListOfMoviesUseCase(repository: MovieRepository) : DownloadListOfMoviesUseCase{
        return DownloadListOfMoviesUseCaseIml(repository)
    }

    @Provides
    internal fun provideErrorOrProgressUserCase(repository: MovieRepository): ErrorOrProgressUseCase{
        return ErrorOrProgressUseCaseImp(repository)
    }

    @Provides
    fun provideHttpLogging(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)


    @Provides
    @Singleton
    fun provideMovieRepositoryIml(api: MovieApi, sharedPref: SharedPref, moviesDatabase: MoviesDatabase): MovieRepositoryImp{
        return MovieRepositoryImp(api, sharedPref, moviesDatabase)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(repository: MovieRepositoryImp): MovieRepository{
        return repository
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(context: Application) : MoviesDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            MoviesDatabase::class.java, "movies-database.db"
        )
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
    }

    @Provides
    @Singleton
    fun provideSharedPref(context: Application) : SharedPref{
        return SharedPref(context.applicationContext)
    }
}