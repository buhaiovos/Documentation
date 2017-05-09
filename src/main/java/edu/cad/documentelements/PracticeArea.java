package edu.cad.documentelements;

import edu.cad.entities.Curriculum;
import edu.cad.entities.Practice;
import edu.cad.entities.Workplan;
import edu.cad.uils.documentutils.DateIntervalStringCreator;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class PracticeArea extends AbstractDocumentArea {
    
    private final List<AbstractColumn> columns;

    public PracticeArea(Sheet sheet, int startRow) {
        super(sheet, "practice", startRow);
        this.columns = new ArrayList<>();
        
        addColumns();
    }
    
    private void addColumns() {
        Row row = sheet.getRow(rowNumber);
        columns.add(new SimpleColumn(row, "p_num"));
        columns.add(new SimpleColumn(row, "practice"));
        columns.add(new SimpleColumn(row, "p_dates"));
        columns.add(new SimpleColumn(row, "p_weeks"));
        columns.add(new SimpleColumn(row, "p_semester"));
    }

    @Override
    public void fill(Curriculum curriculum) {
        List<String> columnsValues = parseData(((Workplan)curriculum).getPractice());
        System.out.println("!!!!!!!!" + rowNumber);
        Row row = sheet.getRow(rowNumber);
        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).fill(row, columnsValues.get(i));
        }
    }

    private List<String> parseData(Practice practice) {
        List<String> data = new ArrayList<>();
        // order the same as in constructor
        data.add("1");
        data.add(practice.getDenotation());
        data.add(DateIntervalStringCreator.getDatesString(practice.getStart(), 
                                                         practice.getFinish()));
        data.add(Integer.toString(practice.getWeeks()));
        data.add(Integer.toString(practice.getSemester()));
        
        return data;
    }    
}
