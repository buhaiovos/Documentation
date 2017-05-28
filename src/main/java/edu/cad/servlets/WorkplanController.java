/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cad.servlets;

import edu.cad.entities.Workplan;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WorkplanController")
public class WorkplanController extends AbstractEntityController<Workplan>{

    public WorkplanController() {
        super(Workplan.class);
    }

    @Override
    protected Workplan getInstance(HttpServletRequest request) {
        Workplan workplan = new Workplan();
        workplan = initializeInstance(workplan, request);

        if (request.getParameter("denotation") != null) {
            workplan.setDenotation(request.getParameter("denotation"));
        }
        
        return workplan;
    }
    
    @Override
    protected void getDropDownList(HttpServletResponse response) throws IOException {
        super.getDropDownList(Workplan::getDenotation, response);
    }
}
