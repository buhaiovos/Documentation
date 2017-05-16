package edu.cad.utils.databaseutils;

import edu.cad.utils.hibernateutils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class DatabaseSwitcher {
    
    public static void switchDatabase(int year){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        
        String oldUrl = configuration.getProperty("hibernate.connection.url");
        System.out.println("!!!" + oldUrl);
        int slashPosition = oldUrl.lastIndexOf("/");
        String newUrl = oldUrl.substring(0, slashPosition + 1) 
                + "cad_database_" + year;
        System.out.println("!!!" + newUrl);
        configuration.setProperty("hibernate.connection.url", newUrl);
        System.out.println("!!!" + configuration.getProperty("hibernate.connection.url"));
        
        System.out.println(HibernateSession.getInstance().hashCode());
        HibernateSession.openSession(configuration);
        System.out.println(HibernateSession.getInstance().hashCode());
    }
}
