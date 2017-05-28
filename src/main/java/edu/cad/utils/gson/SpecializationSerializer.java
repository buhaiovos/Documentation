package edu.cad.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.cad.entities.Specialization;
import java.lang.reflect.Type;

public class SpecializationSerializer implements JsonSerializer<Specialization>{

    @Override
    public JsonElement serialize(Specialization instance, Type type, JsonSerializationContext jsc) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        JsonElement jsonElement = gson.toJsonTree(instance);
        
        if(instance.getDepartment() != null)
            jsonElement.getAsJsonObject().addProperty("department", instance.getDepartment().getId());
       
        return jsonElement.getAsJsonObject();
    }
    
}
