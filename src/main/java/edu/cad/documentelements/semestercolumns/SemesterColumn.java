package edu.cad.documentelements.semestercolumns;

import edu.cad.documentelements.columns.AbstractColumn;
import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import edu.cad.uils.Utils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class SemesterColumn extends AbstractColumn{
    protected int semester;
    protected int weeks;
    
    public SemesterColumn(Sheet sheet, int startColumnIndex) {
        super();
        
        Row row = sheet.getRow(findRowNumber(sheet, "#semester", startColumnIndex));
        
        if(columnNumber > 0) {
            extractSemester(row);
            extractWeeks(row);
            fill(row, weeks + getToken(row));
        }
    }

    public SemesterColumn(Sheet sheet, String token, int startColumnIndex) {
        findRowNumber(sheet, token, startColumnIndex);
    }
    
    @Override
    public void fill(Row row, CurriculumSubject record) {
        Subject subject = record.getSubject();
        double hours = subject.getSemesterHours(semester, record.getCurriculum(),
                Subject::getTotalHours);
        hours /= (double) weeks;
        
        if(hours > 0){
            fill(row, hours);
        } else {
            clear(row);
        }
    }

    public int getSemester() {
        return semester;
    }

    public int getWeeks() {
        return weeks;
    }

    private int findRowNumber(Sheet sheet, String token, int startColumnIndex){
        int currentRow = 0;
        
        do{
            currentRow++;
            columnNumber = findInRow(sheet.getRow(currentRow), token, startColumnIndex); 
        }while(columnNumber < 0 && currentRow < sheet.getLastRowNum());
        
        return currentRow;
    }
    
    private void extractSemester(Row row){
        String value = row.getCell(columnNumber).getStringCellValue().trim();
        int delimiter = value.indexOf("_");
        
        value = value.substring(0, delimiter);
        value = value.replaceAll("#semester", "");
        
        if(Utils.isParseable(value))
            semester = Integer.parseInt(value);
    }
    
    private void extractWeeks(Row row){
        String value = row.getCell(columnNumber).getStringCellValue().trim();
        int delimiter = value.indexOf("_");
        int lastDelimiter = value.lastIndexOf("_");
        
        if(delimiter == lastDelimiter)
            value = value.substring(delimiter + 1, value.length());
        else
            value = value.substring(delimiter + 1, lastDelimiter);
        
        if(Utils.isParseable(value))
            weeks = Integer.parseInt(value);
    }
    
    private String getToken(Row row){
        String value = row.getCell(columnNumber).getStringCellValue().trim();
        int delimiter = value.indexOf("_");
        int lastDelimiter = value.lastIndexOf("_");

        if(delimiter == lastDelimiter)
            return "";
        
        return value.substring(lastDelimiter + 1, value.length());
    }
}
