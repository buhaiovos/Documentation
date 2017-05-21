package edu.cad.documentelements.k3columns;

import edu.cad.entities.AcademicGroup;
import edu.cad.entities.Subject;
import edu.cad.generators.SourceOfFinancing;
import edu.cad.generators.TypeOfGroupWork;

public class GroupsK3Column extends AbstractK3Column{
    private final SourceOfFinancing source;
    private final TypeOfGroupWork type;
    
    public GroupsK3Column(int columnNumber, SourceOfFinancing source,
            TypeOfGroupWork type) {
        super(columnNumber);
        this.source = source;
        this.type = type;
    }

    @Override
    public String getValue(Subject subject) {
        int total = 0;
        
        for(AcademicGroup group : subject.getGroups()){
            if(source.sourceEquals(group)){
                total += group.getTotalStudents();
            }
        }
        
        int subgroups = (int) Math.ceil(total / (double) type.getMaxStudents());
        
        return Integer.toString(subgroups);
    }
    
}
