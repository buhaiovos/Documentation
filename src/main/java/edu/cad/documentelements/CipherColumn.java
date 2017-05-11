package edu.cad.documentelements;

import edu.cad.entities.CurriculumSubject;
import org.apache.poi.ss.usermodel.Row;

public class CipherColumn extends AbstractColumn {
    
    public CipherColumn(Row row) {
        super(row, "#cipher");
    }

    @Override
    public void fill(Row row, CurriculumSubject record) {
         fill(row, record.getCipher());
    }
}
