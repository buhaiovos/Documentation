package edu.cad.generators;

import edu.cad.documentelements.areas.AbstractDocumentArea;
import edu.cad.documentelements.areas.DiplomaPreparationArea;
import edu.cad.documentelements.areas.PracticeArea;
import edu.cad.documentelements.areas.StateCertificationArea;
import edu.cad.entities.Curriculum;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Олександр
 */
public class WorkplanGenerator extends CurriculumGenerator {
    
    private List<AbstractDocumentArea> workplanSpecificAreas = new ArrayList<>();
    
    public WorkplanGenerator(Curriculum curriculum, Workbook template,  
            String title) {
        super(curriculum, template, title);
    }
    
    @Override
    public void generate() throws IOException {
        subjectList.fill(curriculum);
        addWorkplanSpecificAreas();
        fillWorkplanSpecificAreas();        
        saveFile();
    }

    private void addWorkplanSpecificAreas() {
        int startPos = subjectList.getRowNumer();
        workplanSpecificAreas.add(new PracticeArea(sheet, startPos));
        workplanSpecificAreas.add(new StateCertificationArea(sheet, startPos));
        workplanSpecificAreas.add(new DiplomaPreparationArea(sheet, null, 
                                                             startPos));
        
    }

    private void fillWorkplanSpecificAreas() {
        for (AbstractDocumentArea area : workplanSpecificAreas) {
            area.fill(curriculum);
        }
    }

    private void saveFile() throws IOException {
        template.write(new FileOutputStream(outputTitle));
    }
    
}
