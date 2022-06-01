package com.gopal.myapplication.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gopal.myapplication.data.model.FacilitiesAPI

@Database(entities = [FacilitiesAPI.Facilities::class], version = 1)
@TypeConverters(FacilitiesTypeConverter::class, ExclusionConverter::class)
abstract class PropertyRoomDatabase : RoomDatabase(){
    abstract fun propertyDao(): PropertyDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PropertyRoomDatabase? = null

        fun getDatabase(context: Context): PropertyRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PropertyRoomDatabase::class.java,
                    "facilities_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}
