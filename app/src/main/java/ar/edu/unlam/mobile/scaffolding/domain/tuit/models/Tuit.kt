package ar.edu.unlam.mobile.scaffolding.domain.tuit.models

import com.google.gson.annotations.SerializedName

data class Tuit(
    @SerializedName("id") val id: Int,
    @SerializedName("author") val authorName: String,
    @SerializedName("message") val content: String,
    @SerializedName("avatar_url") val avatar: String,
    @SerializedName("likes") var likes: Int,
    @SerializedName("liked") var liked: Boolean,
    var replies: Int,
    @SerializedName("date") val date: String,
    val reply: (id: Int) -> Unit,
)
