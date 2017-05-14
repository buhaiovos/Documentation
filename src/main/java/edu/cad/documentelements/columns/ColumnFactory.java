package edu.cad.documentelements.columns;

import edu.cad.uils.documentutils.ColumnTokenStringSplitter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;

/*
semestercolumn takes colNum, semester, weeks
semlabs1_18
*/

public class ColumnFactory {
    
    public static AbstractColumn createColumn(Cell cell) {
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
                
                column = createColumn();
            }
            else {
                return null;
            }        
        }  
        return null;
    }

}
