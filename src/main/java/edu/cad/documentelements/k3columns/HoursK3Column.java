package edu.cad.documentelements.k3columns;

import edu.cad.entities.Subject;
import edu.cad.functionalinterfaces.SubjectProperty;
import org.apache.poi.ss.usermodel.Row;

public class HoursK3Column extends AbstractK3Column{
    private SubjectProperty property;
    
    public HoursK3Column(int columnNumber, SubjectProperty property) {
        super(columnNumber);
        this.property = property;
    }

    @Override
    public void fill(Row row, Subject subject) {
        fill(row, property.getValue(subject));
    }
}
