package com.capstone.wishata.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capstone.wishata.data.local.database.entity.Place
import kotlin.concurrent.Volatile

@Database(
    entities = [Place::class],
    version = 1,
    exportSchema = false
    )
abstract class PlaceRoomDatabase: RoomDatabase() {
    abstract fun placeDao(): PlaceDao

    companion object {
        @Volatile
        private var INSTANCE: PlaceRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): PlaceRoomDatabase {
            if (INSTANCE == null) {
                synchronized(PlaceRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PlaceRoomDatabase::class.java,
                        "placeDatabase"
                    ).build()
                }
            }
            return INSTANCE as PlaceRoomDatabase
        }
    }
}