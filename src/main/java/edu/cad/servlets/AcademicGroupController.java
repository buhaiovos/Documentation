package edu.cad.servlets;

import edu.cad.entities.AcademicGroup;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/AcademicGroupController")
public class AcademicGroupController extends AbstractEntityController<AcademicGroup>{
    
    public AcademicGroupController() {
        super(AcademicGroup.class);
    }

    @Override
    protected AcademicGroup getInstance(HttpServletRequest request) {
        AcademicGroup group = new AcademicGroup();
        
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            
            AcademicGroup found = dao.get(id);
            if(found != null) {
                group = found;
            } else {
                group.setId(id);
            }
        }

        if (request.getParameter("cipher") != null) {
            String cipher = request.getParameter("cipher");
            group.setCipher(cipher);
        }
        
        return group;
    }
    
}
