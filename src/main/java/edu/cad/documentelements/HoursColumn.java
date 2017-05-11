package edu.cad.documentelements;

import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import edu.cad.entities.Workplan;
import edu.cad.utils.entityutils.SubjectUtils;
import java.util.Set;
import org.apache.poi.ss.usermodel.Row;

public abstract class HoursColumn extends AbstractColumn {
    
    public HoursColumn(Row row, String token) {
        super(row, token);
    }
    
    protected void setValue(Row row, CurriculumSubject record, SubjectProperty property){
        Set<Subject> children = SubjectUtils.getChildren(record.getSubject(), 
                record.getCurriculum());
        
        double value = property.getValue(record.getSubject());

        if(!(record.getCurriculum() instanceof Workplan) || !children.isEmpty()){
            for(Subject child : children){
                value += property.getValue(child);
            }
        }
        
        if (value > 0) {
            fill(row, value);
        } else {
            fill(row, "");
        }
    }
    
    protected interface SubjectProperty{
        public double getValue(Subject subject);
    }
}
