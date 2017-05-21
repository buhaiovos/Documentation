package edu.cad.documentelements.k3columns;

import edu.cad.documentelements.columns.AbstractColumn;
import edu.cad.entities.CurriculumSubject;
import org.apache.poi.ss.usermodel.Row;

public class StudyLoadColumn extends AbstractColumn {
    
    private final StudyLoadType type;
    
    public StudyLoadColumn(int columnNumber, StudyLoadType type) {
        super(columnNumber);
        this.type = type;
    }
    
    public StudyLoadType getColumnType() {
        return this.type;
    }
    
    @Override
    public void fill(Row row, CurriculumSubject record) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
