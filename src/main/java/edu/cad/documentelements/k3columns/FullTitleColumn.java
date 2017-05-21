package edu.cad.documentelements.k3columns;

import edu.cad.entities.AcademicGroup;
import edu.cad.entities.Subject;
import java.util.Set;
import org.apache.poi.ss.usermodel.Row;

public class FullTitleColumn extends AbstractK3Column{
    private final String faculty;
    
    public FullTitleColumn(int columnNumber, String faculty) {
        super(columnNumber);
        this.faculty = faculty;
    }

    @Override
    public void fill(Row row, Subject subject) {
        String mainString = getMainString(subject);
        String groupsString = getGroupsString(subject.getGroups());
        
        row.getCell(columnNumber).setCellValue(mainString + groupsString);
    }
    
    private String getMainString(Subject subject){
        final String DELIMITER = " - ";
        StringBuilder value = new StringBuilder();
        
        value.append(faculty);
        value.append(DELIMITER);
        value.append(subject.getSubject().getDenotation());
        value.append(DELIMITER);
        value.append(subject.getEctsHours());
        value.append(DELIMITER);
        
        return value.toString();
    }
    
    private String getGroupsString(Set<AcademicGroup> groups){
        StringBuilder value = new StringBuilder();
        
        for(AcademicGroup group : groups){
            value.append(group.getCipher());
            value.append("(");
            value.append(group.getBudgetaryStudents());
            value.append("+");
            value.append(group.getContractStudents());
            value.append("),");
        }
        
        value.deleteCharAt(value.length() - 1);
        
        return value.toString();
    }
}
