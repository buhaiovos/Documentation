package edu.cad.utils.databaseutils;

import edu.cad.utils.Utils;
import edu.cad.utils.hibernateutils.HibernateSession;
import java.util.Set;
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
            
            DatabaseYears.deleteYear(year);
        }
    }
    
    private static boolean exist(int year){
        Set<Integer> years = DatabaseYears.getAllYears();
        
        for(int element : years){
            if(element == year){
                return true;
            }
        }
        
        return false;
    }
    
    private static boolean isCurrent(int year){
        Configuration configuration = HibernateSession.getConfiguration();
        
        if(configuration == null)
            return false; 
        
        String url = configuration.getProperty("hibernate.connection.url");
        int yearPosition = url.lastIndexOf("_");
        
        String yearString = url.substring(yearPosition + 1, url.length());
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
        
        DatabaseYears.addYear(year);
        
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
