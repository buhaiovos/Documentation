package edu.cad.servlets;

import com.google.gson.GsonBuilder;
import edu.cad.daos.HibernateDAO;
import edu.cad.entities.AcademicGroup;
import edu.cad.entities.Specialization;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            group.setCipher(request.getParameter("cipher").trim());
        }
        /*
        students, year
        */
        if (request.getParameter("specialization") != null) {
            int id = Integer.parseInt(request.getParameter("specialization"));
            group.setSpecialization(new HibernateDAO<>(Specialization.class).get(id));
        }
        
        return group;
    }
    
    @Override
    protected void createGson() {
        gson = new GsonBuilder().registerTypeAdapter(AcademicGroup.class, 
                new AcademicGroupSerializer()).setPrettyPrinting().create();
    }

    @Override
    protected void getDropDownList(HttpServletResponse response) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
