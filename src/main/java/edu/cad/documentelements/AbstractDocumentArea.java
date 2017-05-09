package edu.cad.documentelements;

import edu.cad.entities.Curriculum;
import org.apache.poi.ss.usermodel.Sheet;

public abstract class AbstractDocumentArea extends AbstractDocumentElement {
    protected Sheet sheet;
    protected int rowNumber;
    
    public AbstractDocumentArea(Sheet sheet, String token, int startRow) {
        this.sheet = sheet;
        findRowNumber(startRow, token);
    }
    
    public abstract void fill(Curriculum curriculum);
    
    private void findRowNumber(int startRow, String token){
        int columnNumber;
        
        do{
            columnNumber = findInRow(sheet.getRow(startRow), token);
            
            if(columnNumber != -1)
                break;
            
            startRow++;
        } while(startRow < sheet.getLastRowNum());
        
        rowNumber = startRow;
    }
}
