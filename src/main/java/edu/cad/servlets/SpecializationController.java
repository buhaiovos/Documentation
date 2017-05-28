package edu.cad.servlets;

import edu.cad.entities.Specialization;
import edu.cad.utils.gson.Option;
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

        setStringProperty(request, "denotation", specialization::setDenotation);
        
        return specialization;
    }

    @Override
    protected void getDropDownList(HttpServletResponse response) throws IOException {
        super.getDropDownList(Specialization::getDenotation, response);
    }
}
