package com.gopal.myapplication.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gopal.myapplication.data.model.FacilitiesAPI
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFacilities(facilities: FacilitiesAPI.Facilities)

    @Query("SELECT * FROM facilities_table ORDER BY id ASC")
    fun getAllFacilityList(): Flow<List<FacilitiesAPI.Facilities>>

}