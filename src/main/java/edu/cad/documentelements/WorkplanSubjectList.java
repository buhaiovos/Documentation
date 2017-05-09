package edu.cad.documentelements;

import edu.cad.daos.HibernateDAO;
import edu.cad.daos.IDAO;
import edu.cad.entities.ControlDictionary;
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
    
}
