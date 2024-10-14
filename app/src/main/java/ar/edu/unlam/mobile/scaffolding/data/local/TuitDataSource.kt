package ar.edu.unlam.mobile.scaffolding.data.local

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TuitDataSource
    @Inject
    constructor() : TuitRepository {
        private val tuits = mutableListOf<Tuit>()

        init {
            val newTuits =
                listOf(
                    Tuit(
                        id = 1,
                        content = "algo",
                        authorName = "pepe",
                        avatar = "https://cdn-icons-png.flaticon.com/512/4908/4908415.png",
                        likes = 0,
                        liked = false,
                        replies = 0,
                        reply = { id -> },
                    ),
                )
            tuits.addAll(newTuits)
        }

        override fun getTuits(): Flow<List<Tuit>> =
            flow {
                delay(500L)
                if (tuits.isEmpty()) {
                    throw Exception("No tuits available")
                }
                emit(tuits)
            }

        override fun createTuit(tuit: Tuit) {
            tuits.add(tuit)
        }
    }
