package edu.cad.documentelements;

import edu.cad.daos.HibernateDAO;
import edu.cad.entities.Control;
import edu.cad.entities.ControlDictionary;
import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import edu.cad.entities.Workplan;
import edu.cad.utils.entityutils.SubjectUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.poi.ss.usermodel.Row;

public class ControlColumn extends AbstractColumn{
    protected ControlDictionary control;
    
    public ControlColumn(Row row, String token, ControlDictionary control) {
        super(row, "#control" + control.getId());
        this.control = control;
    }

    @Override
    public void fill(Row row, CurriculumSubject record) {
        Set<Subject> children = SubjectUtils.getChildren(record.getSubject(), 
                record.getCurriculum());
        Set<Control> controls = new TreeSet<>();

        controls.addAll(getSubjectControls(record, children, control));

        if(control.getId() == 2){
            ControlDictionary diff = new HibernateDAO<>(ControlDictionary.class).get(9);
            controls.addAll(getSubjectControls(record, children, diff));
        }

        writeControls(row, controls);
    }
    
    public Set<Control> getSubjectControls(CurriculumSubject record, 
            Set<Subject> children, ControlDictionary type){
        Set<Control> result = new HashSet<>();
        
        result.addAll(record.getSubject().getControlsByType(type));
        
        if(!(record.getCurriculum() instanceof Workplan) || !children.isEmpty()){
            for(Subject element : children){
                result.addAll(element.getControlsByType(type));
            }
        }
        
        return result;
    }
    
    private void writeControls(Row row, Set<Control> controls){
        Iterator<Control> iterator = controls.iterator();
        
        if(!controls.isEmpty()){
            StringBuilder value = new StringBuilder();
            value.append(iterator.next().toString());
            
            while(iterator.hasNext()){
                value.append(",");
                value.append(iterator.next().toString());
            }

            row.getCell(columnNumber).setCellValue(value.toString());
        }
    }
}
