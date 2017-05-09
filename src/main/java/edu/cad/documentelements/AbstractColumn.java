package edu.cad.documentelements;

import edu.cad.entities.CurriculumSubject;
import org.apache.poi.ss.usermodel.Row;

public abstract class AbstractColumn extends AbstractDocumentElement {
    protected final int columnNumber;
    
    public AbstractColumn(Row row, String token) {
        columnNumber = findInRow(row, token);
    }
    
    public abstract void fill(Row row, CurriculumSubject record);
    
    public void fill(Row row, String value){
        row.getCell(columnNumber).setCellValue(value);
    }
}
