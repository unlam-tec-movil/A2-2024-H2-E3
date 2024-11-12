package ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import coil.network.HttpException
import javax.inject.Inject

class LikeTuitUseCase
    @Inject
    constructor(
        private val tuitRepository: TuitRepository,
    ) {
        suspend operator fun invoke(tuit: Tuit) {
            try {
                tuit.likes++
                tuitRepository.addLike(tuit.id)
            } catch (e: HttpException) {
                Log.e("LikeTuitUseCase", "Error en addLike: ${e.message}")
                throw e
            }
        }
    }
