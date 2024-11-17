package ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import coil.network.HttpException
import javax.inject.Inject

class DeletelikeTuitUseCase
    @Inject
    constructor(
        private val tuitRepository: TuitRepository,
    ) {
        suspend operator fun invoke(tuit: Tuit) {
            try {
                // tuit.likes--
                tuitRepository.deleteLike(tuit.id)
            } catch (e: HttpException) {
                Log.e("DeletelikeTuitUseCase", "Error al eliminar like: ${e.message}")
                throw e
            }
        }
    }
