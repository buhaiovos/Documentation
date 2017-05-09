package edu.cad.documentelements;

import org.apache.poi.ss.usermodel.Sheet;

public abstract class AbstractSubjectList extends AbstractDocumentArea {
    
    public AbstractSubjectList(Sheet sheet, String token, int startRow) {
        super(sheet, token, startRow);
    }
    
}
