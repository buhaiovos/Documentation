package edu.cad.documentelements;

import org.apache.poi.ss.usermodel.Row;

public abstract class AbstractDocumentElement {
    
    protected int findInRow(Row row, String token){
   
        return -1;
    }
}
