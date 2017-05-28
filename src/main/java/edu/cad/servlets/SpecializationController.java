package edu.cad.servlets;

import edu.cad.entities.Specialization;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SpecializationController")
public class SpecializationController extends AbstractEntityController<Specialization>{

    public SpecializationController() {
        super(Specialization.class);
    }

    @Override
    protected Specialization getInstance(HttpServletRequest request) {
        Specialization specialization = new Specialization();
        specialization = initializeInstance(specialization, request);

        if (request.getParameter("denotation") != null) {
            specialization.setDenotation(request.getParameter("denotation"));
        }
        
        return specialization;
    }

    @Override
    protected void getDropDownList(HttpServletResponse response) throws IOException {
        super.getDropDownList(Specialization::getDenotation, response);
    }
}
