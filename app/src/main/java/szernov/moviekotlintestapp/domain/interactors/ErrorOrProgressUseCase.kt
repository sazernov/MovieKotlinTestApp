package szernov.moviekotlintestapp.domain.interactors

interface ErrorOrProgressUseCase {
    interface CallbackListener {
        fun setVisibilityProgress(isVisible: Boolean)
        fun showError(message: String)
    }

    fun setListener(listener: CallbackListener)
}