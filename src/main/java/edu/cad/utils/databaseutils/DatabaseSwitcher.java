package edu.cad.utils.databaseutils;

import edu.cad.daos.HibernateDAO;
import edu.cad.entities.DatabaseYear;
import edu.cad.utils.Utils;
import edu.cad.utils.hibernateutils.HibernateSession;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class DatabaseSwitcher {
    
    public static void switchDatabase(int year){
        boolean exist = exist(year);
        
        if(!exist){
            createDatabase(year);
        } 
        
        Session oldSession = switchDatabaseAndSession(year);
        
        if(!exist){
            //DatabaseCloner.cloneDatabase(oldSession);
        }
       
        oldSession.close();
    }
    
    public static void dropDatabase(int year){
        if(exist(year)){
            if(isCurrent(year)){
                HibernateSession.close();
            }
            
            String sql = "DROP DATABASE cad_database_" + year;
            executeSQLQuery(sql);
            
            new HibernateDAO<>(DatabaseYear.class).delete(year);
        }
    }
    
    private static boolean exist(int year){
        List<DatabaseYear> years = new HibernateDAO<>(DatabaseYear.class).getAll();
        
        for(DatabaseYear element : years){
            if(element.getYear() == year){
                return true;
            }
        }
        
        return false;
    }
    
    private static boolean isCurrent(int year){
        Configuration configuration = HibernateSession.getConfiguration();
        
        String url = configuration.getProperty("hibernate.connection.url");
        int yearPosition = url.lastIndexOf("_");
        
        String yearString = url.substring(yearPosition + 1, url.length());
        System.out.println("\n\n\n" + yearString + "\n\n\n");
        if(Utils.isParseable(yearString)){
            if(Integer.parseInt(yearString) == year){
                return true;
            }
        }
        
        return false;
    }
    
    private static Session switchDatabaseAndSession(int year){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        
        String oldUrl = configuration.getProperty("hibernate.connection.url");
        int yearPosition = oldUrl.lastIndexOf("_");
        
        String newUrl = oldUrl.substring(0, yearPosition + 1) + year;
        configuration.setProperty("hibernate.connection.url", newUrl);
        
        new HibernateDAO<>(DatabaseYear.class).create(new DatabaseYear(year));
        
        Session oldSession = HibernateSession.getInstance();
        HibernateSession.openSession(configuration);
        
        return oldSession;
    }
    
    private static void createDatabase(int year){
        String sql = "CREATE SCHEMA cad_database_" + year;
        executeSQLQuery(sql);
    }
    
    private static void executeSQLQuery(String sql){
        SQLQuery query = HibernateSession.getInstance().createSQLQuery(sql);
        query.executeUpdate();
    }
    
    
    
}
