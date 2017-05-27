package edu.cad.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cad.daos.HibernateDAO;
import edu.cad.daos.IDAO;
import edu.cad.entities.interfaces.IDatabaseEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractEntityController<T extends IDatabaseEntity> 
        extends HttpServlet {
    
    //private static final long serialVersionUID = 1L;
    
    //private final Map<String, Object> JSONROOT = new HashMap<>();
    
    protected final IDAO<T> dao;
    protected Gson gson;   
    protected Map<String, Object> content;
    
    private List<T> list;
    private String action;

    public AbstractEntityController(Class<T> typeParameterClass) {
        dao = new HibernateDAO<>(typeParameterClass);
        list = new ArrayList<>();
    }

    protected abstract void getDropDownList(HttpServletResponse response) throws IOException;
    
    protected abstract T getInstance(HttpServletRequest request);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        createGson();
        action = request.getParameter("action");
        content = new HashMap<>();
        setResponseSettings(response);
        processAction(request, response);
    }

    protected void createGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        gson = builder.excludeFieldsWithoutExposeAnnotation().create();
    }

    protected void writeResponse(HttpServletResponse response) throws IOException {
        //JSONROOT.putAll(content);
        String jsonArray = gson.toJson(content);
        
        System.out.println(jsonArray);
        
        response.getWriter().print(jsonArray);
    }
    
    protected void putOk() {
        content.put("Result", "OK");
    }
    
    private void setResponseSettings(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
    }

    private void processAction(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        if (action != null) {
            try {
                switch(action) {
                    case "list":
                        processListAction(response);
                        break;
                    case "create":
                    case "update":
                        processCreateUpdateAction(request, response);
                        break;
                    case "delete":
                        processDeleteAction(request, response);
                        break;
                    case "dropdownlist":
                        getDropDownList(response);
                        break;
                }
            }
            catch (IOException | NumberFormatException ex) {
                content.put("Result", "ERROR");
                content.put("Message", ex.getMessage());
                writeResponse(response);
            }
        }
    }

    private void processListAction(HttpServletResponse response) 
            throws IOException {
        list = dao.getAll();

        putOk();
        content.put("Records", list);
        writeResponse(response);
    }

    private void processCreateUpdateAction(HttpServletRequest request, 
            HttpServletResponse response) throws IOException {
        
        T instance = getInstance(request);

        if (action.equals("create")) {
            dao.create(instance);
        } else if (action.equals("update")) {
            dao.update(instance);
        }

        putOk();
        content.put("Record", instance);
        writeResponse(response);
    }
    private void processDeleteAction(HttpServletRequest request, 
            HttpServletResponse response) throws IOException {
        
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.delete(id);

            putOk();
            writeResponse(response);
        }
    }
}
