package com.gopal.myapplication

import android.app.Application
import com.gopal.myapplication.data.repositories.FacilitiesRepositories
import com.gopal.myapplication.data.room.PropertyRoomDatabase

class FacilitiesApplication : Application() {
    private val database by lazy {PropertyRoomDatabase.getDatabase(this@FacilitiesApplication) }

    val repository by lazy { FacilitiesRepositories(database.propertyDao()) }
}