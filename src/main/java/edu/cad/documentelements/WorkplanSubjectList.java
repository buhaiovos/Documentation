package edu.cad.documentelements;

import edu.cad.daos.HibernateDAO;
import edu.cad.daos.IDAO;
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
}
