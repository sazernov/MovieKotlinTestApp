package szernov.moviekotlintestapp.domain.interactors.executers

import szernov.moviekotlintestapp.domain.interactors.ErrorOrProgressUseCase
import szernov.moviekotlintestapp.domain.repository.MovieRepository

class ErrorOrProgressUseCaseImp(private val repository: MovieRepository) : ErrorOrProgressUseCase {
    override fun setListener(listener: ErrorOrProgressUseCase.CallbackListener) {
        repository.setErrorOrProgressListener(listener)
    }
}