package edu.cad.databaseutils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Олена
 */
public class HibernateSession{
    private static final Session session = openSession();
    
    private static Session openSession(){
        Configuration configuration = new Configuration();  
        configuration.configure("hibernate.cfg.xml");  
        
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry); 
        
        return factory.openSession();
    }
    
    public static synchronized Session getInstance(){
        return session;
    }

    public static void close(){
        session.close();
    }
}
