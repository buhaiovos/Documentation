package edu.cad.documentelements;

import edu.cad.entities.Curriculum;
import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;

public abstract class AbstractColumn extends AbstractDocumentElement {
    protected final int columnNumber;
    protected final Curriculum curriculum;
    
    public AbstractColumn(Row row, String token, Curriculum curriculum) {
        columnNumber = findInRow(row, token);
        this.curriculum = curriculum;
    }
    
    public abstract void fill(Row row, Subject subject);
    
    
}
