package ar.edu.unlam.mobile.scaffolding.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.local.entities.TuitEntity
import kotlinx.coroutines.flow.Flow


@Dao interface TuitDao {
    @Query("SELECT * FROM TuitEntity")
    fun getAllDrafts(): Flow<List<TuitEntity>>

    @Query("SELECT * FROM TuitEntity WHERE id = :tuitId")
    suspend fun getDraft(tuitId: Int): TuitEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDraft(tuitEntity: TuitEntity)

    @Delete
    suspend fun deleteDraft(tuitEntity: TuitEntity)

    @Query("SELECT * FROM TuitEntity WHERE id = :id LIMIT 1")
    suspend fun getDraftById(id: Int): TuitEntity?

}


