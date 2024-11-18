package ar.edu.unlam.mobile.scaffolding.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.mobile.scaffolding.data.local.dao.TuitDao
import ar.edu.unlam.mobile.scaffolding.data.local.entities.TuitEntity


@Database(entities = [TuitEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
 abstract fun tuitDao(): TuitDao
}