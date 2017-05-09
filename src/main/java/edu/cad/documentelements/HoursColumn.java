package edu.cad.documentelements;

import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import edu.cad.entities.Workplan;
import edu.cad.utils.entityutils.SubjectUtils;
import java.util.Set;
import org.apache.poi.ss.usermodel.Row;

public class HoursColumn extends AbstractColumn {
    private final String type;
    
    public HoursColumn(Row row, String token) {
        super(row, token);
        this.type = token;
    }

    @Override
    public void fill(Row row, CurriculumSubject record) {
        switch(type){
            case "#ects":       setValue(row, record, Subject::getEcts);
                                break;
            case "#lections":   setValue(row, record, Subject::getLections);
                                break;
            case "#practices":  setValue(row, record, Subject::getPractices);
                                break;
            case "#labs":       setValue(row, record, Subject::getLabs);
                                break;
        }   
    }
    
    private void setValue(Row row, CurriculumSubject record, SubjectProperty property){
        Set<Subject> children = SubjectUtils.getChildren(record.getSubject(), 
                record.getCurriculum());
        
        double value = property.getValue(record.getSubject());

        if(!(record.getCurriculum() instanceof Workplan) || !children.isEmpty()){
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
