package edu.cad.documentelements.columns;

import edu.cad.daos.HibernateDAO;
import edu.cad.documentelements.hourscolumns.EctsColumn;
import edu.cad.documentelements.hourscolumns.LabsColumn;
import edu.cad.documentelements.hourscolumns.LectionsColumn;
import edu.cad.documentelements.hourscolumns.PracticesColumn;
import edu.cad.documentelements.semestercolumns.SemesterColumn;
import edu.cad.documentelements.semestercolumns.SemesterLabsColumn;
import edu.cad.documentelements.semestercolumns.SemesterLectionsColumn;
import edu.cad.documentelements.semestercolumns.SemesterPracticesColumn;
import edu.cad.entities.ControlDictionary;
import edu.cad.utils.documentutils.ColumnTokenStringSplitter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ColumnFactory {
    
    public static AbstractColumn createColumn(Cell cell) {
        if (cell != null) {             
            String cellContent = getContentIfCellValid(cell);
            if (cellContent != null) {
                return createColumn(new ColumnTokenStringSplitter(cellContent), 
                                    cell.getColumnIndex());
            }      
        }  
        return null;
    }
    
    private static String getContentIfCellValid(Cell cell) {
        
        if (cell.getCellTypeEnum() == CellType.STRING) {
            String cellContent = cell.getStringCellValue();
            if ( (cellContent != null) && (cellContent.contains("#")) ) {
                return cellContent;
            }
        }
        return null;
    }

    private static AbstractColumn createColumn(ColumnTokenStringSplitter ctss, 
                                                int columnNumber) {
        switch (ctss.getType()) {
            case "cipher":
                return new CipherColumn(columnNumber);
            case "control":
                int id = Integer.parseInt(ctss.getFirstNumString());
                ControlDictionary cd = 
                        new HibernateDAO<>(ControlDictionary.class).get(id);
                return new ControlColumn(columnNumber, cd);
            case "department":
                return new DepartmentColumn(columnNumber);
            case "ects":
                return new EctsColumn(columnNumber);
            case "labs":
                return new LabsColumn(columnNumber);
            case "lections":
                return new LectionsColumn(columnNumber);
            case "practices":
                return new PracticesColumn(columnNumber);
            case "section":
                return new TitleColumn(columnNumber);
            case "semester":
                return new SemesterColumn(columnNumber, 
                        Integer.parseInt(ctss.getFirstNumString()), // semester
                        Integer.parseInt(ctss.getSecondNumString())); // weeks
            case "semlabs":
                return new SemesterLabsColumn(columnNumber, 
                        Integer.parseInt(ctss.getFirstNumString()),
                        Integer.parseInt(ctss.getSecondNumString()));
            case "semlections":
                return new SemesterLectionsColumn(columnNumber, 
                        Integer.parseInt(ctss.getFirstNumString()),
                        Integer.parseInt(ctss.getSecondNumString()));
            case "sempractices":
                return new SemesterPracticesColumn(columnNumber, 
                        Integer.parseInt(ctss.getFirstNumString()),
                        Integer.parseInt(ctss.getSecondNumString()));
            default: 
                return null;                
        }
    }
}
