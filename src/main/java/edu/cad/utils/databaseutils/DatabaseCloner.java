package edu.cad.utils.databaseutils;

import edu.cad.daos.HibernateDAO;
import edu.cad.entities.AcademicGroup;
import edu.cad.entities.interfaces.IDatabaseEntity;
import edu.cad.utils.hibernateutils.HibernateSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import org.hibernate.Session;

public class DatabaseCloner {
    
    public static void cloneDatabase(Session oldSession){
        //get Class objects of all @Entity classes
        List<Class<? extends IDatabaseEntity>> entityClasses = getEntityClasses();
        //clone all data except AcademicGroup

        HibernateSession.getInstance().createSQLQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

        Map<Class<? extends IDatabaseEntity>, List<? extends IDatabaseEntity>> entityMap = new HashMap<>();
        for(Class<? extends IDatabaseEntity> classObj : entityClasses){
            entityMap.put(classObj, fill(classObj, oldSession));
            
        }
        
        List<AcademicGroup> groups = (List<AcademicGroup>) entityMap.get(AcademicGroup.class);
        entityMap.remove(AcademicGroup.class);
        
        oldSession.close();

        for(Class<? extends IDatabaseEntity> classObj : entityClasses){
              cloneAllEntriesOfEntity(classObj, entityMap.get(classObj));
        }

        //handle groups
        rewriteGroups(groups);
        HibernateSession.getInstance().createSQLQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }

    private static List<Class<? extends IDatabaseEntity>> getEntityClasses() {
        EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("documentation");
        //get all entity types of @entity annotated classes
        Set<EntityType<?>> entities = emf.getMetamodel().getEntities();
        //get all 
        List<Class<? extends IDatabaseEntity>> entityClasses = new ArrayList<>();
        for (EntityType<?> e : entities) {
            entityClasses.add((Class<? extends IDatabaseEntity>)e.getJavaType());
        }

        return entityClasses;
    }
    
    private static  List<IDatabaseEntity> fill(Class<? extends IDatabaseEntity> classObj, Session oldSession){
        HibernateDAO<IDatabaseEntity> sourceDAO = new HibernateDAO(classObj, oldSession);
        List<IDatabaseEntity> allEntries = sourceDAO.getAll();
        
        return allEntries;
    }

    private static void cloneAllEntriesOfEntity(Class<? extends IDatabaseEntity> classObj, 
                            List<? extends IDatabaseEntity> list) {
        Session currentSession = HibernateSession.getInstance();
        HibernateDAO<IDatabaseEntity> destDAO = new HibernateDAO(classObj, 
                currentSession);

        int i = 0;
        for (IDatabaseEntity entry : list) {
            System.out.println(classObj + " " + entry.getClass() + " " + entry.getId());
            destDAO.create(entry);
            i++;
            if(i == 10)
                break;
        }
    }

    private static void rewriteGroups(List<AcademicGroup> groups) {
        Session currentSession = HibernateSession.getInstance();
        int newYear = findNewYear(groups);
    }

    private static int findNewYear(List<AcademicGroup> groups) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}