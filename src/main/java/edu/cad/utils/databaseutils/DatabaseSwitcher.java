package edu.cad.utils.databaseutils;

import edu.cad.daos.HibernateDAO;
import edu.cad.entities.DatabaseYear;
import edu.cad.utils.hibernateutils.HibernateSession;
import java.util.List;
import org.hibernate.cfg.Configuration;

public class DatabaseSwitcher {
    
    public static void switchDatabase(int year){
        
        
        
        
    }
    
    private boolean exist(int year){
        List<DatabaseYear> years = new HibernateDAO<>(DatabaseYear.class).getAll();
        
        for(DatabaseYear element : years){
            if(element.getYear() == year){
                return true;
            }
        }
        
        return false;
    }
    
    private static void switchDatabaseAndSession(int year){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        
        String oldUrl = configuration.getProperty("hibernate.connection.url");
        int yearPosition = oldUrl.lastIndexOf("_");
        
        String newUrl = oldUrl.substring(0, yearPosition + 1) + year;
        configuration.setProperty("hibernate.connection.url", newUrl);
        
        HibernateSession.close();
        HibernateSession.openSession(configuration);
    }
    
    
}
