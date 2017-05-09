package edu.cad.documentelements;

import edu.cad.entities.Control;
import edu.cad.entities.Curriculum;
import edu.cad.entities.CurriculumSubject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.poi.ss.usermodel.Sheet;

public abstract class AbstractSubjectList extends AbstractDocumentArea {
    protected Set<AbstractColumn> columns;
    
    public AbstractSubjectList(Sheet sheet, int startRow) {
        super(sheet, "section", startRow);
        columns = new HashSet<>();
    }

    @Override
    public void fill(Curriculum curriculum) {
        Set<CurriculumSubject> records = new TreeSet<>();
    }
    
    protected abstract Set<Control> getControls();
}
