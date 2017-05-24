package edu.cad.generators;

import edu.cad.entities.Department;
import edu.cad.entities.EducationForm;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;

public class FormK3Generator implements IDocumentGenerator{
    private final Department department;
    private final Sheet sheet;
    private final EducationForm educationForm;
    private final SourceOfFinancing source;
    
    public FormK3Generator(Sheet sheet, Department department, 
            EducationForm educationForm, SourceOfFinancing source){
        this.department = department;
        this.sheet = sheet;
        this.educationForm = educationForm;
        this.source = source;
    }
    
    @Override
    public void generate() throws IOException {
        List<K3SubjectEntity> subjects = K3SubjectListCreator.createList(educationForm, source, department, 1);
        
        
    }
    
    
}
