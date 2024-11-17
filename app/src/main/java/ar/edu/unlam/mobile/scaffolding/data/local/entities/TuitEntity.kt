package ar.edu.unlam.mobile.scaffolding.data.local.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TuitEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val content: String
)