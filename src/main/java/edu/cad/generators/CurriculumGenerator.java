package edu.cad.generators;

import edu.cad.documentelements.areas.CurriculumSubjectList;
import edu.cad.entities.Curriculum;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class CurriculumGenerator implements IDocumentGenerator {
    
    protected Curriculum curriculum;
    protected CurriculumSubjectList subjectList;
    protected String outputTitle;
    protected Workbook template;
    protected Sheet sheet;
    
    public CurriculumGenerator(Curriculum curriculum, Workbook template, 
                String title) {
        this.curriculum = curriculum;
        this.template = template;
        this.outputTitle = title;
        this.sheet = template.getSheetAt(0);
        this.subjectList = new CurriculumSubjectList(this.sheet, 0);
    }

    @Override
    public void generate() throws IOException {
        subjectList.fill(curriculum);
        
        template.write(new FileOutputStream(outputTitle + ".xls"));
    }
}
