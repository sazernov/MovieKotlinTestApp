package szernov.moviekotlintestapp.domain.interactors.executers

import szernov.moviekotlintestapp.domain.interactors.DownloadListOfMoviesUseCase
import szernov.moviekotlintestapp.domain.repository.MovieRepository

class DownloadListOfMoviesUseCaseIml (private val repository: MovieRepository) : DownloadListOfMoviesUseCase {

    override fun execute() {
        repository.downloadMovie()
    }

    override fun setListener(listener: DownloadListOfMoviesUseCase.ResponseListener) {
        repository.setDownloadListener(listener)
    }
}