package edu.cad.utils.hibernateutils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateSession{
    private static Session session;
    
    private HibernateSession(){ }
    
    private static void openSession(){
        Configuration configuration = new Configuration();  
        configuration.configure("hibernate.cfg.xml");  
        
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry); 
        
        session = factory.openSession();
    }
    
    public static synchronized Session getInstance(){
        if(!session.isOpen()){
            openSession();
        }
        
        return session;
    }

    public static void close(){
        session.close();
    }
}
