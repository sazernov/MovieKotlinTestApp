package szernov.moviekotlintestapp.domain.repository

import szernov.moviekotlintestapp.domain.interactors.DownloadListOfMoviesUseCase
import szernov.moviekotlintestapp.domain.interactors.ErrorOrProgressUseCase
import szernov.moviekotlintestapp.domain.model.Movie

interface MovieRepository {

    fun setDownloadListener(listener: DownloadListOfMoviesUseCase.ResponseListener)

    fun setErrorOrProgressListener(listener: ErrorOrProgressUseCase.CallbackListener)

    fun downloadMovie()
}