package edu.cad.documentelements.areas;

import edu.cad.documentelements.DocumentSection;
import edu.cad.documentelements.columns.LabsColumn;
import edu.cad.documentelements.columns.ControlColumn;
import edu.cad.documentelements.columns.TitleColumn;
import edu.cad.documentelements.columns.PracticesColumn;
import edu.cad.documentelements.columns.EctsColumn;
import edu.cad.documentelements.columns.AbstractColumn;
import edu.cad.documentelements.columns.LectionsColumn;
import edu.cad.documentelements.columns.CipherColumn;
import edu.cad.entities.ControlDictionary;
import edu.cad.entities.Curriculum;
import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Section;
import edu.cad.entities.SubjectDictionary;
import edu.cad.uils.documentutils.RowInserter;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public abstract class AbstractSubjectList extends AbstractDocumentArea {
    protected Set<AbstractColumn> columns;
    
    public AbstractSubjectList(Sheet sheet, int startRow) {
        super(sheet, "#section", startRow);
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
        boolean first = true;

        for(CurriculumSubject record : records){    
            SubjectDictionary subject = record.getSubject().getSubject();

            if(!subjectSection.getSection(subject).equals(section))
                continue;

            if(!first){
                RowInserter.insertRow(sheet, rowNumber);
            } else {
                first = false;
            }
            
            for(AbstractColumn column : columns){
                column.fill(sheet.getRow(rowNumber), record);
            }
            //REMOVE
            HSSFFormulaEvaluator.evaluateAllFormulaCells(sheet.getWorkbook());
            rowNumber++;
        }
        
        if(first){
            rowNumber++;
        }
    }
    
    protected interface SubjectSection{
        public Section getSection(SubjectDictionary subject);
    }
}
