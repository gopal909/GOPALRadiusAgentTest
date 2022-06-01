package com.gopal.myapplication.data.repositories

import androidx.annotation.WorkerThread
import com.gopal.myapplication.data.model.FacilitiesAPI
import com.gopal.myapplication.data.room.PropertyDao
import kotlinx.coroutines.flow.Flow

class FacilitiesRepositories(private val propertyDao : PropertyDao) {


    @WorkerThread
    suspend fun insertData(facilities: FacilitiesAPI.Facilities) {
        propertyDao.insertFacilities(facilities)
    }

    val allFacilitiesList: Flow<List<FacilitiesAPI.Facilities>> = propertyDao.getAllFacilityList()

}