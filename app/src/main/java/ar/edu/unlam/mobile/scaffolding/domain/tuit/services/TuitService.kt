package ar.edu.unlam.mobile.scaffolding.domain.tuit.services

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.DeletelikeTuitUseCase
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.LikeTuitUseCase
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TuitService
    @Inject
    constructor(
        private val likeTuitUseCase: LikeTuitUseCase,
        private val deletelikeTuitUseCase: DeletelikeTuitUseCase,
        private val tuitRepository: TuitRepository,
    ) {
        suspend fun toggleLike(tuit: Tuit) {
            if (tuit.liked) {
                deletelikeTuitUseCase(tuit)
            } else {
                likeTuitUseCase(tuit)
            }
            tuit.liked = !tuit.liked
        }

        suspend fun fetchTuits(): List<Tuit> = tuitRepository.getTuits().first()
    }
