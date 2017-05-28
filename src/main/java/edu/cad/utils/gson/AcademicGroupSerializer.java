/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cad.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.cad.entities.AcademicGroup;
import java.lang.reflect.Type;

/**
 *
 * @author Олена
 */
public class AcademicGroupSerializer implements JsonSerializer<AcademicGroup>{

    @Override
    public JsonElement serialize(AcademicGroup instance, Type type, JsonSerializationContext jsc) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        JsonElement jsonElement = gson.toJsonTree(instance);
        
        if(instance.getSpecialization() != null)
            jsonElement.getAsJsonObject().addProperty("specialization", instance.getSpecialization().getId());
        
        if(instance.getQualification() != null)
            jsonElement.getAsJsonObject().addProperty("qualification", instance.getQualification().getId());
        
        if(instance.getEducationForm() != null)
            jsonElement.getAsJsonObject().addProperty("educationForm", instance.getEducationForm().getId());
        
        if(instance.getWorkplan() != null)
            jsonElement.getAsJsonObject().addProperty("workplan", instance.getWorkplan().getId());
       
        return jsonElement.getAsJsonObject();
    }
    
}
