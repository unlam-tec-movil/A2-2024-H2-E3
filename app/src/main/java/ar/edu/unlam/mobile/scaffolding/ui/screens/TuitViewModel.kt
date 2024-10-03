package ar.edu.unlam.mobile.scaffolding.ui.screens

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository

class TuitViewModel(
    tuitRepository: TuitRepository,
) {
    var listTuits: List<Tuit> = tuitRepository.loadTuits()
}