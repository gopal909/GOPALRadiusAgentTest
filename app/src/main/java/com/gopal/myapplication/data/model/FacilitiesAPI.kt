package com.gopal.myapplication.data.model

import androidx.room.*
import com.gopal.myapplication.data.room.ExclusionConverter
import com.gopal.myapplication.data.room.FacilitiesTypeConverter
import java.io.Serializable

object FacilitiesAPI : Serializable {
    @Entity(tableName = "facilities_table")
    data class Facilities(
        @ColumnInfo(name = "exclusions")
        val exclusions: List<List<Exclusion>>,
        @TypeConverters(FacilitiesTypeConverter::class)
        @ColumnInfo(name = "facilities")
        val facilities: List<Facility>,
        @PrimaryKey(autoGenerate = false)
        var id: Int = 0
    )
    @TypeConverters(ExclusionConverter::class)
    data class Exclusion(
        val facility_id: String,
        val options_id: String
    )

    data class Facility(
        val facility_id: String,
        val name: String,
        val options: List<Option>
    )

    data class Option(
        val icon: String,
        val id: String,
        val name: String
    )
}