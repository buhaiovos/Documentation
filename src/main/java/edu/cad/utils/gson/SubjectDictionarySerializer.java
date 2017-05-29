package edu.cad.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.cad.entities.SubjectDictionary;
import java.lang.reflect.Type;

public class SubjectDictionarySerializer implements JsonSerializer<SubjectDictionary>{

    @Override
    public JsonElement serialize(SubjectDictionary instance, Type type, JsonSerializationContext jsc) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        JsonElement jsonElement = gson.toJsonTree(instance);
        
        if(instance.getSuperSubject() != null)
            jsonElement.getAsJsonObject().addProperty("superSubject", instance.getSuperSubject().getId());
        
        if(instance.getDepartment() != null)
            jsonElement.getAsJsonObject().addProperty("department", instance.getDepartment().getId());
        
        if(instance.getCurriculumSection() != null)
            jsonElement.getAsJsonObject().addProperty("curriculumSection", instance.getCurriculumSection().getId());
        
        if(instance.getWorkplanSection() != null)
            jsonElement.getAsJsonObject().addProperty("workplanSection", instance.getWorkplanSection().getId());
        
        if(instance.getType() != null)
            jsonElement.getAsJsonObject().addProperty("type", instance.getType().getId());
       
        return jsonElement.getAsJsonObject();
    }
    
}
