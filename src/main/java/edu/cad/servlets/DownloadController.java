package edu.cad.servlets;

import edu.cad.generators.CurriculumGenerator;
import edu.cad.generators.IDocumentGenerator;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

@WebServlet("/DownloadController")
public class DownloadController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        if(request.getParameter("downloadWorkplan") != null){
            Workbook workbook = new HSSFWorkbook(new FileInputStream("D:/Downloads/CurriculumTemplate.xls"));

            CurriculumGenerator generator = new CurriculumGenerator(workbook);
            generator.generate();
            response.setContentType("application/ms-excel; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode("Форма К-3.xls", "UTF-8"));
            Enumeration<String> e = request.getHeaderNames();
            while(e.hasMoreElements()){
                System.out.println(e.nextElement());
            }
            workbook.write(response.getOutputStream());
        }
    }
    
    private void downloadGeneratedDocument(IDocumentGenerator generator, String filename, 
            HttpServletResponse response) throws IOException{
        
        generator.generate();
        response.setContentType("application/vnd.ms-excel");
        
        response.setHeader("Content-disposition", "attachment; filename=" + filename);
        //generator.getWorkbook().write(response.getOutputStream());
    }
    
    private void downloadTemplate(String filename, HttpServletResponse response){
        
    }
    
    private void uploadTemplate(String filename, HttpServletResponse response){
        
    }
}
