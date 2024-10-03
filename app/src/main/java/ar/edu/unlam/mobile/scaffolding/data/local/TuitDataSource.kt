package ar.edu.unlam.mobile.scaffolding.data.local

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository

class TuitDataSource : TuitRepository {
    override fun loadTuits(): List<Tuit> =
        listOf(
            Tuit(
                id = 1,
                content = "algo",
                authorName = "pepe",
                avatar = "",
                likes = 0,
                liked = false,
                replies = 0,
            ),
            Tuit(id = 2, content = "algo1", authorName = "martinez", avatar = "", likes = 0, liked = false, replies = 0),
            Tuit(id = 3, content = "algo2", authorName = "diego", avatar = "", likes = 0, liked = false, replies = 0),
            Tuit(id = 4, content = "algo3", authorName = "martinez", avatar = "", likes = 0, liked = false, replies = 0),
            Tuit(id = 5, content = "algo4", authorName = "diego", avatar = "", likes = 0, liked = false, replies = 0),
            Tuit(id = 6, content = "algo5", authorName = "martinez", avatar = "", likes = 0, liked = false, replies = 0),
        )
}