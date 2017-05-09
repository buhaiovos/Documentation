package edu.cad.documentelements;

import edu.cad.entities.Control;
import edu.cad.entities.ControlDictionary;
import edu.cad.entities.Curriculum;
import edu.cad.entities.CurriculumSubject;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import org.apache.poi.ss.usermodel.Sheet;

public abstract class AbstractSubjectList extends AbstractDocumentArea {
    protected Set<AbstractColumn> columns;
    
    public AbstractSubjectList(Sheet sheet, int startRow) {
        super(sheet, "section", startRow);
        columns = new HashSet<>();
        
        for(ControlDictionary control : getControls()){
            columns.add(new ControlColumn(sheet.getRow(rowNumber), control));
        }
        
        addColumns();
    }
    
    @Override
    public void fill(Curriculum curriculum) {
        Set<CurriculumSubject> records = new TreeSet<>();
    }
    
    protected void addColumns(){
        
    }
    
    protected abstract Set<ControlDictionary> getControls();
}
