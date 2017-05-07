package edu.cad.documentelements;

import edu.cad.daos.HibernateDAO;
import edu.cad.entities.Control;
import edu.cad.entities.ControlDictionary;
import edu.cad.entities.Curriculum;
import edu.cad.entities.Subject;
import edu.cad.entities.Workplan;
import edu.cad.utils.entityutils.SubjectUtils;
import edu.cad.uils.Utils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.poi.ss.usermodel.Row;

public class ControlColumn extends AbstractColumn{
    protected ControlDictionary control;
    
    public ControlColumn(Row row, String token, Curriculum curriculum) {
        super(row, token, curriculum);
        
        String controlType = token.replace("#control", "");
        
        if(Utils.isParseable(controlType)){
            int controlId = Integer.parseInt(controlType);   
            control = new HibernateDAO<>(ControlDictionary.class).get(controlId);  
        } else {
            control = new ControlDictionary();
        }
        
    }

    @Override
    public void fill(Row row, Subject subject) {
        Set<Subject> children = SubjectUtils.getChildren(subject, curriculum);
        Set<Control> controls = new TreeSet<>();

        controls.addAll(getSubjectControls(subject, children, control));

        if(control.getId() == 2){
            ControlDictionary diff = new HibernateDAO<>(ControlDictionary.class).get(9);
            controls.addAll(getSubjectControls(subject, children, diff));
        }

        writeControls(row, controls);
    }
    
    public Set<Control> getSubjectControls(Subject subject, 
            Set<Subject> children, ControlDictionary type){
        Set<Control> result = new HashSet<>();
        
        result.addAll(subject.getControlsByType(type));
        
        if(!(curriculum instanceof Workplan) || !children.isEmpty()){
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
