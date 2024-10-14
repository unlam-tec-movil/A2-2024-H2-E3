package ar.edu.unlam.mobile.scaffolding.domain.tuit.models

data class Tuit(
    val id: Int,
    val authorName: String,
    val content: String,
    val avatar: String,
    var likes: Int,
    val liked: Boolean,
    var replies: Int,
    val reply: (id: Int) -> Unit,
)
