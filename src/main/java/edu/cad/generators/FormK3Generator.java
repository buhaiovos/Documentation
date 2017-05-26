package edu.cad.generators;

import edu.cad.documentelements.columns.AbstractColumn;
import edu.cad.documentelements.k3columns.AbstractK3Column;
import edu.cad.documentelements.k3columns.AllK3ColumnsFactory;
import edu.cad.documentelements.k3columns.StudyLoadColumn;
import edu.cad.entities.Department;
import edu.cad.entities.EducationForm;
import edu.cad.utils.documentutils.K3SemesterStartRowFinder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<K3SubjectEntity> subjects = 
                K3SubjectListCreator.createList(educationForm, source, department, 1);
        // first semester
        Map<Class, List<AbstractColumn>> firstSemMap = getInitializedMap();
        addColumnsToMap(firstSemMap,
                K3SemesterStartRowFinder.findSemesterStartRow(sheet, 1));
        /*
        DOCUMENT FILL CODE 
        */
        // second semester
        Map<Class, List<AbstractColumn>> secondSemMap = getInitializedMap();    
        addColumnsToMap(secondSemMap, 
                K3SemesterStartRowFinder.findSemesterStartRow(sheet, 2));
        /*
        DOCUMENT FILL CODE
        */
        
    }
    
    private Map<Class, List<AbstractColumn>> getInitializedMap() {
        Map<Class, List<AbstractColumn>> map = new HashMap<>();
        map.put(AbstractK3Column.class, new ArrayList<>());
        map.put(StudyLoadColumn.class, new ArrayList<>());
        return map;
    }

    private void addColumnsToMap(Map<Class, List<AbstractColumn>> map, Row row) {
        for (int i = 0; i <= row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            AllK3ColumnsFactory.createAndAddColumn(map, cell, source);
                     
        }
    }
    
}
