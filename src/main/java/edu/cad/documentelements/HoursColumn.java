package edu.cad.documentelements;

import edu.cad.entities.Curriculum;
import edu.cad.entities.Subject;
import edu.cad.entities.Workplan;
import edu.cad.utils.entityutils.SubjectUtils;
import java.util.Set;
import org.apache.poi.ss.usermodel.Row;

public class HoursColumn extends AbstractColumn {
    private final String token;
    
    public HoursColumn(Row row, String token, Curriculum curriculum) {
        super(row, token, curriculum);
        this.token = token;
    }

    @Override
    public void fill(Row row, Subject subject) {
        switch(token){
            case "#ects":       setValue(row, subject, Subject::getEcts);
                                break;
            case "#lections":   setValue(row, subject, Subject::getLections);
                                break;
            case "#practices":  setValue(row, subject, Subject::getPractices);
                                break;
            case "#labs":       setValue(row, subject, Subject::getLabs);
                                break;
        }   
    }
    
    private void setValue(Row row, Subject subject, SubjectProperty property){
        Set<Subject> children = SubjectUtils.getChildren(subject, curriculum);
        
        double value = property.getValue(subject);

        if(!(curriculum instanceof Workplan) || !children.isEmpty()){
            for(Subject child : children){
                value += property.getValue(child);
            }
        }
        
        row.getCell(columnNumber).setCellValue(value);
    }
    
    private interface SubjectProperty{
        public double getValue(Subject subject);
    }
}
