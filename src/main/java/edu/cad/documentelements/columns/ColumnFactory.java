package edu.cad.documentelements.columns;

import edu.cad.daos.HibernateDAO;
import edu.cad.entities.ControlDictionary;
import edu.cad.uils.documentutils.ColumnTokenStringSplitter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;

public class ColumnFactory {
    
    /*public static AbstractColumn createColumn(Cell cell) {
        if (cell != null) {
            int rowIndex = cell.getRowIndex();
            int columnIndex = cell.getColumnIndex();
            Sheet correspondingSheet = cell.getSheet();
            String cellContent = null;
            
            AbstractColumn column = null;
            
            // check cell type to understand if 
            // we can extract string
            if (cell.getCellTypeEnum() == CellType.STRING) {
                cellContent = cell.getStringCellValue();
            }
            // if exctracted and it's not null continue
            if ( (cellContent != null) && (cellContent.contains("#")) ) {
                ColumnTokenStringSplitter ctss = 
                        new ColumnTokenStringSplitter(cellContent);
                
                column = createColumn(ctss, columnIndex);
                return column;
            }
            else {
                return null;
            }        
        }  
        return null;
    }*/

    private static AbstractColumn createColumn(ColumnTokenStringSplitter ctss, 
                                                              int columnNumber) {
        switch (ctss.getType()) {
            case "cipher":
                return new CipherColumn(columnNumber);
            case "control":
                int id = Integer.parseInt(ctss.getFirstNumString());
                ControlDictionary cd = 
                        new HibernateDAO<>(ControlDictionary.class)
                                .get(id);
                return new ControlColumn(columnNumber, cd);
            case "department":
                return new DepartmentColumn(columnNumber);
            case "ects":
                return new EctsColumn(columnNumber);
        }
    }

}
