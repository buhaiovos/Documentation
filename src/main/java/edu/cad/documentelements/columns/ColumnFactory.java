package edu.cad.documentelements.columns;

import edu.cad.daos.HibernateDAO;
import edu.cad.entities.ControlDictionary;
import edu.cad.entities.Subject;
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
                return new HoursColumn(columnNumber, Subject::getEcts);
            case "labs":
                return new HoursColumn(columnNumber, Subject::getLabs);
            case "lections":
                return new HoursColumn(columnNumber, Subject::getLections);
            case "practices":
                return new HoursColumn(columnNumber, Subject::getPractices);
            case "section":
                return new TitleColumn(columnNumber);
            case "semester":
                return new SemesterColumn(columnNumber, 
                        Integer.parseInt(ctss.getFirstNumString()), // semester
                        Integer.parseInt(ctss.getSecondNumString()), // weeks
                        Subject::getTotalHours); 
            case "semlabs":
                return new SemesterColumn(columnNumber, 
                        Integer.parseInt(ctss.getFirstNumString()), 
                        Integer.parseInt(ctss.getSecondNumString()), 
                        Subject::getLabs); 
            case "semlections":
                return new SemesterColumn(columnNumber, 
                        Integer.parseInt(ctss.getFirstNumString()), 
                        Integer.parseInt(ctss.getSecondNumString()), 
                        Subject::getLections); 
            case "sempractices":
                return new SemesterColumn(columnNumber, 
                        Integer.parseInt(ctss.getFirstNumString()), 
                        Integer.parseInt(ctss.getSecondNumString()), 
                        Subject::getPractices); 
            default: 
                return null;                
        }
    }
}
