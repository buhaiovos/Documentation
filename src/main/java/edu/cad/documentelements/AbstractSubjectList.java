package edu.cad.documentelements;

import edu.cad.entities.ControlDictionary;
import edu.cad.entities.Curriculum;
import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Section;
import edu.cad.entities.Subject;
import edu.cad.entities.SubjectDictionary;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import org.apache.poi.ss.usermodel.Row;
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
    
    protected void fill(Curriculum curriculum, SubjectSection subjectSection) {
        Set<CurriculumSubject> records = new TreeSet<>();
        records.addAll(curriculum.getCurriculumSubjects());
        
        while(true){
            DocumentSection documentSection = new DocumentSection(sheet, rowNumber);
            
            if(documentSection.getRowNumber() < 0)
                return;
            
            rowNumber = documentSection.getRowNumber();
            Section section = documentSection.getSection();
            
            fillSection(section, records, subjectSection);
        }
    }
    
    protected void addColumns(){
        Row currentRow = sheet.getRow(rowNumber);
        
        columns.add(new CipherColumn(currentRow));
        columns.add(new TitleColumn(currentRow));
        columns.add(new EctsColumn(currentRow));
        columns.add(new LectionsColumn(currentRow));
        columns.add(new LabsColumn(currentRow));
        columns.add(new PracticesColumn(currentRow));
    }
    
    protected abstract Set<ControlDictionary> getControls();
    
    private void fillSection(Section section, Set<CurriculumSubject> records,
            SubjectSection subjectSection){
        for(CurriculumSubject record : records){
            Row currentRow = sheet.getRow(rowNumber);
            
            SubjectDictionary subject = record.getSubject().getSubject();
            if(!subjectSection.getSection(subject).equals(section))
                continue;
            
            for(AbstractColumn column : columns){
                column.fill(currentRow, record);
            }
            
            rowNumber++;
        }
    }
    
    protected interface SubjectSection{
        public Section getSection(SubjectDictionary subject);
    }
}
