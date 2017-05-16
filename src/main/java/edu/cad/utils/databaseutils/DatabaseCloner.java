package edu.cad.utils.databaseutils;

import edu.cad.daos.HibernateDAO;
import edu.cad.entities.AcademicGroup;
import edu.cad.entities.interfaces.IDatabaseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import org.hibernate.Session;

public class DatabaseCloner {
    
    public static void cloneDatabase(Session oldSession, 
                                     Session newSession){
        //get Class objects of all @Entity classes
        List<Class<? extends IDatabaseEntity>> entityClasses = getEntityClasses();
        
        //clone all data except AcademicGroup
        entityClasses.remove(AcademicGroup.class);
        entityClasses.stream().forEach((classObj) -> {
            cloneAllEntriesOfEntity(classObj, oldSession, newSession);
        });
        //handle groups
        rewriteGroups(oldSession, newSession);
    }

    private static List<Class<? extends IDatabaseEntity>> getEntityClasses() {
        EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("documentation");
        //get all entity types of @entity annotated classes
        Set<EntityType<?>> entities = emf.getMetamodel().getEntities();
        // get all 
        List<Class<? extends IDatabaseEntity>> entityClasses = new ArrayList<>();
        for (EntityType<?> e : entities) {
            entityClasses.add((Class<? extends IDatabaseEntity>)e.getJavaType());
        }
        return entityClasses;
    }

    private static void cloneAllEntriesOfEntity(Class<? extends IDatabaseEntity> classObj, 
                            Session oldSession, Session newSession) {
        HibernateDAO<IDatabaseEntity> sourceDAO = new HibernateDAO(classObj, oldSession);
        HibernateDAO destDAO = new HibernateDAO(classObj, newSession);

        List<IDatabaseEntity> allEntries = sourceDAO.getAll();
        for (IDatabaseEntity entry : allEntries) {
            destDAO.create(entry);
        }
    }

    private static void rewriteGroups(Session oldSession, Session newSession) {
        int newYear = findNewYear(oldSession);
    }

    private static int findNewYear(Session oldSession) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
