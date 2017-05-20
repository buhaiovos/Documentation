package edu.cad.generators;

import edu.cad.entities.Department;
import edu.cad.entities.EducationForm;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;

public class FormK3Generator implements IDocumentGenerator{
    private Department department;
    private Sheet sheet;
    private EducationForm educationForm;
    private SourceOfFinancing source;
    
    public FormK3Generator(Sheet sheet, Department department, 
            EducationForm educationForm, SourceOfFinancing source){
        this.department = department;
        this.sheet = sheet;
        this.educationForm = educationForm;
        this.source = source;
    }
    
    @Override
    public void generate() throws IOException {
        
    }
}
