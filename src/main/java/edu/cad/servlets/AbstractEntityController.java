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

public abstract class AbstractEntityController<T extends IDatabaseEntity> extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Map<String, Object> JSONROOT = new HashMap<>();
    protected final IDAO<T> dao;

    public AbstractEntityController(Class<T> typeParameterClass) {
            dao = new HibernateDAO<>(typeParameterClass);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        List<T> list = new ArrayList<>();

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = builder.excludeFieldsWithoutExposeAnnotation().create();

        response.setContentType("application/json");
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        if (action != null) {
            try {
                if (action.equals("list")) {
                    list = dao.getAll();

                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Records", list);

                    String jsonArray = gson.toJson(JSONROOT);
                    System.out.println(jsonArray);
                    response.getWriter().print(jsonArray);
                } else if (action.equals("create") || action.equals("update")) {
                    T instance = getInstance(request);

                    if (action.equals("create")) {
                        dao.create(instance);
                    } else if (action.equals("update")) {
                        dao.update(instance);
                    }

                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Record", instance);

                    String jsonArray = gson.toJson(JSONROOT);
                    response.getWriter().print(jsonArray);
                } else if (action.equals("delete")) {
                    if (request.getParameter("id") != null) {
                        int id = Integer.parseInt(request.getParameter("id"));
                        dao.delete(id);

                        JSONROOT.put("Result", "OK");

                        String jsonArray = gson.toJson(JSONROOT);
                        System.out.println(jsonArray);
                        response.getWriter().print(jsonArray);
                    }
                }
            } catch (IOException | NumberFormatException ex) {
                        JSONROOT.put("Result", "ERROR");
                        JSONROOT.put("Message", ex.getMessage());
                        String error = gson.toJson(JSONROOT);
                        response.getWriter().print(error);
            }
        }
    }
    
    protected abstract T getInstance(HttpServletRequest request);
}
