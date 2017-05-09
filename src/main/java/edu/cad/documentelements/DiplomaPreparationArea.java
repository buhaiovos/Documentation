package edu.cad.documentelements;

import edu.cad.daos.HibernateDAO;
import edu.cad.daos.IDAO;
import edu.cad.entities.Curriculum;
import edu.cad.entities.DiplomaPreparation;
import edu.cad.entities.Section;
import edu.cad.entities.WorkType;
import edu.cad.entities.Workplan;
import edu.cad.uils.Utils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static java.util.concurrent.ThreadLocalRandom.current;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class DiplomaPreparationArea extends AbstractDocumentArea{

    public DiplomaPreparationArea(Sheet sheet, String token, int startRow) {
        super(sheet, "#work", startRow);
    }

    @Override
    public void fill(Curriculum curriculum) {
        int startRow, currentRow = rowNumber;
        
        do{
            rowNumber = currentRow;
            
            
            
            startRow = rowNumber;
            do{
                currentRow = findInRow(sheet.getRow(startRow++), "#work");
            } while(currentRow < 0 && startRow < sheet.getLastRowNum());
            
        }while(currentRow != 1);
    }
    
    private void fillWorkArea(Curriculum curriculum){
        Row currentRow = sheet.getRow(rowNumber);
        AbstractColumn norm = new SimpleColumn(currentRow, "#work");
        AbstractColumn department = new SimpleColumn(currentRow, "#work_department");
        IDAO<WorkType> workTypeDAO = new HibernateDAO(WorkType.class);
        
        String workId = currentRow.getCell(norm.getColumnNumber()).getStringCellValue()
                .replaceAll("#work", "");
        
        if(!Utils.isParseable(workId)){
            norm.fill(currentRow, "");
            department.fill(currentRow, "");
            return;
        }
           
        WorkType workType = workTypeDAO.get(Integer.parseInt(workId));
        for(DiplomaPreparation preparation : ((Workplan)curriculum).getDiplomaPreparations()){
            if(!preparation.getWorkType().equals(workType))
                continue;
            
            norm.fill(currentRow, rowNumber);
        }
        
        
    }
}
