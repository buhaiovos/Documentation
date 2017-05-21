package edu.cad.documentelements.k3columns;

import edu.cad.entities.Subject;
import edu.cad.entities.interfaces.SubjectProperty;

public class HoursK3Column extends AbstractK3Column{
    private final SubjectProperty property;
    
    public HoursK3Column(int columnNumber, SubjectProperty property) {
        super(columnNumber);
        this.property = property;
    }

    @Override
    public String getValue(Subject subject) {
        return Double.toString(property.getValue(subject));
        //fill(row, property.getValue(subject));
    }
}
