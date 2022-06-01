package com.gopal.myapplication.data.room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gopal.myapplication.data.model.FacilitiesAPI;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class ExclusionConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromOptionValuesList(List<List<FacilitiesAPI.Exclusion>> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<List<FacilitiesAPI.Exclusion>>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<List<FacilitiesAPI.Exclusion>> toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<List<FacilitiesAPI.Exclusion>>>() {
        }.getType();
        List<List<FacilitiesAPI.Exclusion>> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }
}
