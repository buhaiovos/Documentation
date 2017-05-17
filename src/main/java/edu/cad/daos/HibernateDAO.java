package edu.cad.daos;

import edu.cad.entities.SubjectDictionary;
import edu.cad.utils.hibernateutils.HibernateSession;
import edu.cad.entities.interfaces.IDatabaseEntity;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public class HibernateDAO<T extends IDatabaseEntity> implements IDAO<T>{	
    private final Class<T> typeParameterClass;
    private final Session session;
    
    public HibernateDAO(Class<T> typeParameterClass){
        this.typeParameterClass = typeParameterClass;
        this.session = HibernateSession.getInstance();
    }

    public HibernateDAO(Class<T> typeParameterClass, Session session){
        this.typeParameterClass = typeParameterClass;
        this.session = session;
    }
    
    @Override
    public List<T> getAll() {
	//Session session = factory.openSession(); 
	Query query = session.createQuery("from " + 
                typeParameterClass.getSimpleName()); 
        List<T> list = query.list();
        //session.close();
        
        return list;
    }

    @Override
    public T get(int id) {
        //Session session = factory.openSession(); 
	T instance = (T) session.get(typeParameterClass, id);
	//session.close();
		
	return instance;
    }

    @Override
    public T update(T instance) {
        //Session session = factory.openSession();  
        Transaction transaction = session.beginTransaction();  
        
        try {
            session.merge(instance); 
            transaction.commit();
        } catch(RuntimeException e) {
            transaction.rollback();
        } finally {
            session.flush();
            //session.close();
        }
        
        return instance;
    }

    @Override
    public boolean create(T instance) {
	//Session session = factory.openSession();  
        Transaction transaction = session.beginTransaction();  
        ((SubjectDictionary)instance).setId(0);
        try {
            session.save(instance); 
            transaction.commit();
        } catch(RuntimeException e) {
            transaction.rollback();
            return false;
        } finally {
            session.flush();
            //session.close();
        }
        
        return true;
    }

    @Override
    public boolean delete(int id) {
	//Session session = factory.openSession(); 
        Transaction transaction = session.beginTransaction(); 
        
        try {
            T instance = (T) session.load(typeParameterClass, id);
            session.delete(instance);
            transaction.commit();
        } catch(RuntimeException e) {
            transaction.rollback();
            return false;
        } finally {
            session.flush();
            //session.close();
        }
			
	return true;
    }	
}
