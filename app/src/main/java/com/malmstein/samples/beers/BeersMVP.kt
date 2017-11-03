package com.malmstein.samples.beers

sealed class ViewState {
    object Loading : ViewState()
    data class Error(val exception: Exception) : ViewState()
}

sealed class BeersViewState : ViewState() {
    data class Beers(val beers: List<Beer>): BeersViewState()
}

interface ViewCallback {
    fun render(state: ViewState)
}

class BeersPresenter(val view: ViewCallback, val getBeersUseCase: GetBeersUseCase) {

    fun showBeers() {
        view.render(ViewState.Loading)
        val beers = getBeersUseCase()
        view.render(BeersViewState.Beers(beers))
    }

}