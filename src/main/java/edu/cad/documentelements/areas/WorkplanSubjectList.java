package edu.cad.documentelements.areas;

import edu.cad.documentelements.columns.DepartmentColumn;
import edu.cad.daos.HibernateDAO;
import edu.cad.daos.IDAO;
import edu.cad.documentelements.columns.semestercolumns.*;
import edu.cad.entities.ControlDictionary;
import edu.cad.entities.Curriculum;
import edu.cad.entities.SubjectDictionary;
import java.util.HashSet;
import java.util.Set;
import org.apache.poi.ss.usermodel.Sheet;

public class WorkplanSubjectList extends AbstractSubjectList{

    public WorkplanSubjectList(Sheet sheet, int startRow) {
        super(sheet, startRow);
    }

    @Override
    protected Set<ControlDictionary> getControls() {
        IDAO<ControlDictionary> controlDAO = new HibernateDAO<>(ControlDictionary.class);
        Set<ControlDictionary> controls = new HashSet<>();
        
        for(int i = 1; i <= 8; i++){
            controls.add(controlDAO.get(i));
        }
        
        return controls;    
    }
    
    @Override
    public void fill(Curriculum curriculum) {
        super.fill(curriculum, SubjectDictionary::getWorkplanSection);
    }
    
    @Override
    protected void addColumns(){
        super.addColumns();
        columns.add(new DepartmentColumn(sheet.getRow(rowNumber)));
    }

    @Override
    protected void addSemesterColumns() {
        int currentColumn = 0;
        
        while(true){
            SemesterColumn column = new SemesterColumn(sheet, currentColumn);
            
            if(column.getColumnNumber() < 0)
                break;
            
            currentColumn = column.getColumnNumber();
            
            columns.add(new SemesterLectionsColumn(sheet, currentColumn, column.getSemester(), column.getWeeks()));
            columns.add(new SemesterLabsColumn(sheet, currentColumn, column.getSemester(), column.getWeeks()));
            columns.add(new SemesterPracticesColumn(sheet, currentColumn, column.getSemester(), column.getWeeks()));
            
            currentColumn++;
        }
    }
}
