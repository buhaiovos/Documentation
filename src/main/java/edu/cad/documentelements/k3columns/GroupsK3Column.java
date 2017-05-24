package edu.cad.documentelements.k3columns;

import edu.cad.generators.K3SubjectEntity;
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
    public String getValue(K3SubjectEntity subject) {
        return Integer.toString(subject.getSubgroup(type));
    }
    
}
