package szernov.moviekotlintestapp.presentation.base

interface BasePresenter<T> {
    fun attachView(view: T)
    fun detachView()
}