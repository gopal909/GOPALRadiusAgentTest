package com.gopal.myapplication.data.room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gopal.myapplication.data.model.FacilitiesAPI;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class FacilitiesTypeConverter implements Serializable {
 /*   @TypeConverter
    public String fromCountryLangList(List<FacilitiesAPI.Facilities> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<FacilitiesAPI.Facilities>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<FacilitiesAPI.Facilities> toCountryLangList(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<FacilitiesAPI.Facilities>>() {}.getType();
        List<FacilitiesAPI.Facilities> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }*/
 @TypeConverter // note this annotation
 public String fromOptionValuesList(List<FacilitiesAPI.Facility> optionValues) {
     if (optionValues == null) {
         return (null);
     }
     Gson gson = new Gson();
     Type type = new TypeToken<List<FacilitiesAPI.Facility>>() {
     }.getType();
     String json = gson.toJson(optionValues, type);
     return json;
 }

    @TypeConverter // note this annotation
    public List<FacilitiesAPI.Facility> toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<FacilitiesAPI.Facility>>() {
        }.getType();
        List<FacilitiesAPI.Facility> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }



}
