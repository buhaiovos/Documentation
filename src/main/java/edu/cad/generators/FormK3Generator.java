package edu.cad.generators;

import edu.cad.daos.HibernateDAO;
import edu.cad.entities.Curriculum;
import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Department;
import edu.cad.entities.EducationForm;
import edu.cad.entities.Subject;
import edu.cad.entities.Workplan;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
        
        
        //subjects.iterator().next().
        
        
    }
    
    
}
