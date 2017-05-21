package edu.cad.documentelements.k3columns;

import edu.cad.entities.AcademicGroup;
import edu.cad.entities.Subject;
import edu.cad.generators.SourceOfFinancing;

public class StudentsK3Column extends AbstractK3Column{
    private final SourceOfFinancing groupSource;
    private final SourceOfFinancing studentSource;
    
    public StudentsK3Column(int columnNumber, SourceOfFinancing groupSource,
            SourceOfFinancing studentSource) {
        super(columnNumber);
        this.groupSource = groupSource;
        this.studentSource = studentSource;
    }

    @Override
    public String getValue(Subject subject) {
        int total = 0;
        
        for(AcademicGroup group : subject.getGroups()){
            if(groupSource.sourceEquals(group)){
                total += studentSource.getStudents(group);
            }
        }
        
        if(total > 0){
            return Integer.toString(total);
        }
        
        return "";
    }
    
}
