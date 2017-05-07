package edu.cad.documentelements;

import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;

public class DepartmentColumn extends AbstractColumn {
    
    public DepartmentColumn(Row row) {
        super(row, "#department", null);
    }

    @Override
    public void fill(Row row, Subject subject) {
        row.getCell(columnNumber).setCellValue(subject.getSubject().getDepartment().getDenotation());
    }
    
}
