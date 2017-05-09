package edu.cad.documentelements;

import edu.cad.daos.HibernateDAO;
import edu.cad.daos.IDAO;
import edu.cad.entities.Curriculum;
import edu.cad.entities.DiplomaPreparation;
import edu.cad.entities.Section;
import edu.cad.entities.Subject;
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
            fillWorkArea(curriculum);
            
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
        AbstractColumn budgetaryStudents = new SimpleColumn(currentRow, "#work_budget");
        AbstractColumn contractStudents = new SimpleColumn(currentRow, "#work_contract");
               
        norm.fill(currentRow, "");
        department.fill(currentRow, "");
        budgetaryStudents.fill(currentRow, 0);
        contractStudents.fill(currentRow, 0);
           
        WorkType workType = getWorkType(currentRow, norm);
        if(workType == null)
            return;
        
        for(DiplomaPreparation preparation : ((Workplan)curriculum).getDiplomaPreparations()){
            if(!preparation.getWorkType().equals(workType))
                continue;
            
            norm.fill(currentRow, preparation.getNorm());
            norm.fill(currentRow, preparation.getDepartment().getDenotation());
        }  
    }
    
    private WorkType getWorkType(Row row, int columnNumber){
        IDAO<WorkType> workTypeDAO = new HibernateDAO(WorkType.class);
        
        String cellContent = row.getCell(columnNumber).getStringCellValue();
        String workId = cellContent.replaceAll("#work", "");
        
        WorkType workType = workTypeDAO.get(Integer.parseInt(workId));
        
        return workType;
    }
    
    private void fillColumn(Row row, Curriculum curriculum){
        
    }
    
    protected interface CurriculumProperty{
        public double getValue(Curriculum curriculum);
    }
}
