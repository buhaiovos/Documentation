package edu.cad.servlets;

import edu.cad.entities.Specialization;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            
            Specialization found = dao.get(id);
            if(found != null) {
                specialization = found;
            } else {
                specialization.setId(id);
            }
        }

        if (request.getParameter("denotation") != null) {
            specialization.setDenotation(request.getParameter("denotation"));
        }
        
        return specialization;
    }

    @Override
    protected void getDropDownList(HttpServletResponse response) throws IOException {
        //Map<String, Object> content = new HashMap<>();
        List<Option> list = new ArrayList();

        for(Specialization specialization : dao.getAll()){
            list.add(new Option(specialization.getDenotation(), specialization.getId()));
        }
        putOk();
        //content.put("Result", "OK");
        content.put("Options", list);
        writeResponse(response);
        /*String jsonArray = new Gson().toJson(content);
        
        response.getWriter().print(jsonArray);*/
    }
}
