package edu.cad.generators;

import edu.cad.documentelements.k3columns.AbstractK3Column;
import edu.cad.documentelements.k3columns.AllK3ColumnsFactory;
import edu.cad.documentelements.k3columns.StudyLoadColumn;
import edu.cad.entities.Department;
import edu.cad.entities.EducationForm;
import edu.cad.utils.documentutils.K3SemesterStartRowFinder;
import edu.cad.utils.documentutils.RowInserter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class FormK3Generator implements IDocumentGenerator{
    private final Department department;
    private final Sheet sheet;
    private final EducationForm educationForm;
    private final SourceOfFinancing source;
    
    public FormK3Generator(Sheet sheet, Department department, 
            EducationForm educationForm, SourceOfFinancing source){
        this.department = department;
        this.sheet = sheet;
        this.educationForm = educationForm;
        this.source = source;
    }
    
    @Override
    public void generate() throws IOException {
        List<K3SubjectEntity> firstSemSubjects = 
                K3SubjectListCreator.createList(educationForm, source, department, 1);
        Map<Class, List<AbstractK3Column>> columnsMap = getInitializedMap();
        
        Row firstSemRow = K3SemesterStartRowFinder.findSemesterStartRow(sheet, 1);
        
        addColumnsToMap(columnsMap, firstSemRow);
        fill(firstSemSubjects, columnsMap.get(AbstractK3Column.class), firstSemRow.getRowNum());

        List<K3SubjectEntity> secondSemSubjects = 
                K3SubjectListCreator.createList(educationForm, source, department, 2); 
        Row secondSemRow = K3SemesterStartRowFinder.findSemesterStartRow(sheet, 2);
        fill(secondSemSubjects, columnsMap.get(AbstractK3Column.class), secondSemRow.getRowNum());

    }
    
    private Map<Class, List<AbstractK3Column>> getInitializedMap() {
        Map<Class, List<AbstractK3Column>> map = new HashMap<>();
        map.put(AbstractK3Column.class, new ArrayList<>());
        map.put(StudyLoadColumn.class, new ArrayList<>());
        return map;
    }

    private void addColumnsToMap(Map<Class, List<AbstractK3Column>> map, Row row) {
        for (int i = 0; i <= row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            AllK3ColumnsFactory.createAndAddColumn(map, cell, source);                    
        }
    }
    
    private void fill(List<K3SubjectEntity> subjects, List<AbstractK3Column> columns,
            int rowNumber){
        boolean first = true;

        for(K3SubjectEntity subject : subjects){    
            if(!first){
                RowInserter.insertRow(sheet, rowNumber);
            } else {
                first = false;
            }
            
            for(AbstractK3Column column : columns){
                if(column != null)
                    column.fill(sheet.getRow(rowNumber), subject, "");
            }
            HSSFFormulaEvaluator.evaluateAllFormulaCells(sheet.getWorkbook());
            rowNumber++;
        }
    }
    
}
