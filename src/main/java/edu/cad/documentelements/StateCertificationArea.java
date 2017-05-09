/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cad.documentelements;

import edu.cad.entities.Curriculum;
import edu.cad.entities.StateCertification;
import edu.cad.entities.Workplan;
import edu.cad.uils.documentutils.DateIntervalStringCreator;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Олександр
 */
public class StateCertificationArea extends AbstractDocumentArea {
    
    private final List<AbstractColumn> columns;
    
    public StateCertificationArea(Sheet sheet, int startRow) {
        super(sheet, "state_cert", startRow);
        
        this.columns = new ArrayList<>();
        addColumns();        
    }
    
    private void addColumns() {
        Row row = sheet.getRow(rowNumber);
        columns.add(new SimpleColumn(row, "sc_num"));
        columns.add(new SimpleColumn(row, "state_cert"));
        columns.add(new SimpleColumn(row, "sc_dates"));
    }

    @Override
    public void fill(Curriculum curriculum) {
        List<String> columnsValues = parseData(((Workplan)curriculum)
                .getStateCertification());
        
        Row row = sheet.getRow(rowNumber);
        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).fill(row, columnsValues.get(i));
        }
    }

    private List<String> parseData(StateCertification sc) {
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add(sc.getDenotation());
        data.add(DateIntervalStringCreator.getDatesString(sc.getStart(),
                sc.getFinish()));
        
        return data;
    }
}
