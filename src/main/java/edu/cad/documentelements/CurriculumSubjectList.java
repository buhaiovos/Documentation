package edu.cad.documentelements;

import edu.cad.daos.HibernateDAO;
import edu.cad.daos.IDAO;
import edu.cad.entities.ControlDictionary;
import java.util.HashSet;
import java.util.Set;
import org.apache.poi.ss.usermodel.Sheet;

public class CurriculumSubjectList extends AbstractSubjectList {
    
    public CurriculumSubjectList(Sheet sheet, int startRow) {
        super(sheet, startRow);
    }

    @Override
    protected Set<ControlDictionary> getControls() {
        IDAO<ControlDictionary> controlDAO = new HibernateDAO<>(ControlDictionary.class);
        Set<ControlDictionary> controls = new HashSet<>();
        
        controls.add(controlDAO.get(1));
        controls.add(controlDAO.get(2));
        controls.add(controlDAO.get(4));
        controls.add(controlDAO.get(5));
        
        return controls;
    }
}
