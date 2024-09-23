package ar.edu.unlam.mobile.scaffolding.domain.tuit.repository

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit

interface TuitRepository {
    fun loadTuits(): List<Tuit>
}