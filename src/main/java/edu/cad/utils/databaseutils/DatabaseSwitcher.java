package edu.cad.utils.databaseutils;

import org.hibernate.cfg.AnnotationConfiguration;

public class DatabaseSwitcher {
    
    public static void switchDatabase(int year){
        AnnotationConfiguration configuration = new AnnotationConfiguration().configure();
        
        String oldUrl = configuration.getProperty("hibernate.connection.url");
        int slashPosition = oldUrl.lastIndexOf("/");
        String newUrl = oldUrl.substring(0, slashPosition + 1) 
                + "cad_database_" + year;
        
        configuration.setProperty("hibernate.connection.url", newUrl);
    }
}
