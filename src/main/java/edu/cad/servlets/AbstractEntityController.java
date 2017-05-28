package edu.cad.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cad.daos.HibernateDAO;
import edu.cad.daos.IDAO;
import edu.cad.entities.interfaces.IDatabaseEntity;
import edu.cad.utils.gson.HibernateProxyTypeAdapter;
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
        //JSONROOT = new HashMap<>();
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
    
    protected void setStringProperty(HttpServletRequest request, 
            String requestParamString, StringPropertySetter propSetter) {
        
        String param = request.getParameter(requestParamString);
        if (param != null) {
            propSetter.setProperty(param);
        }
    }
    
    protected void setIntProperty(HttpServletRequest request,
            String requestParamString, IntPropertySetter propSetter) {
        
        String numString = request.getParameter(requestParamString);
        if (numString != null) {
            int value = Integer.parseInt(numString.trim());
            propSetter.setProperty(value);
        }
    }
    
    protected <E extends IDatabaseEntity> 
        void setObjectProperty(HttpServletRequest request,String requestParamString, 
            ObjectPropertySetter<E> propSetter, Class<E> objClassType) {
        
        String entityIdStr = request.getParameter(requestParamString);
        if (entityIdStr != null) {
            int id = Integer.parseInt(entityIdStr.trim());
            propSetter.setProperty(new HibernateDAO<>(objClassType).get(id));
        }
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
        /*
        if (action.equals("create")) {
            dao.create(instance);
        } else if (action.equals("update")) {
            dao.update(instance);
        }
        */
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
        
    protected interface StringPropertySetter {
        void setProperty(String property);
    }
    
    protected interface IntPropertySetter {
        void setProperty(int propertyValue);
    }
    
    protected interface ObjectPropertySetter<T> {
        void setProperty(T object);
    }
    
}
