package edu.cad.utils.databaseutils;

import edu.cad.daos.HibernateDAO;
import edu.cad.entities.*;
import edu.cad.entities.interfaces.IDatabaseEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import org.hibernate.Session;

public class DatabaseCloner {
    
    public static void cloneDatabase(Session oldSession){
        /*Session newSession = HibernateSession.getInstance();
        //get Class objects of all @Entity classes
        //List<Class<? extends IDatabaseEntity>> entityClasses = getEntityClasses();
        List<Class<? extends IDatabaseEntity>> entityClasses = getInSpecificOrder();
        //clone all data except AcademicGroup
        //entityClasses.remove(AcademicGroup.class);
        HibernateSession.getInstance().createSQLQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        entityClasses.stream().forEach((classObj) -> {
            cloneAllEntriesOfEntity(classObj, oldSession, newSession);
        });
        //cloneAllEntriesOfEntity(SubjectDictionary.class, oldSession, newSession);
        //handle groups
        //rewriteGroups(oldSession, newSession);*/
        System.out.println("-------- MySQL JDBC Connection Testing ------------");

	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		return;
	}

	System.out.println("MySQL JDBC Driver Registered!");
	Connection connection = null;

	try {
		connection = DriverManager
		.getConnection("jdbc:mysql://localhost:3306/cad_database_2017","root", "1111");

	} catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return;
	}

	if (connection != null) {
		System.out.println("You made it, take control your database now!");
	} else {
		System.out.println("Failed to make connection!");
	}
        Statement stmt = null;
        String query1 = "set FOREIGN_KEY_CHECKS = 0";
        String query2 = "insert into cad_database_2017.dict_subjects (select * from cad_database_2016.dict_subjects where id_supersubject is null)";
        String query3 = "insert into cad_database_2017.dict_subjects (select * from cad_database_2016.dict_subjects where id_supersubject is not null)";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query1);
            stmt.executeUpdate(query2);
            stmt.executeUpdate(query3);
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) { try {
                stmt.close();
                System.out.println("!!!!!!!!!!!!!!FINISHED");
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseCloner.class.getName()).log(Level.SEVERE, null, ex);
                }
}
        }
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
        if (classObj.equals(SubjectDictionary.class)) {
            for (IDatabaseEntity entry : allEntries) {
                if (((SubjectDictionary)entry).getSuperSubject() == null) {
                    destDAO.create(entry);
                }
            }
            for (IDatabaseEntity entry : allEntries) {
                if (((SubjectDictionary)entry).getSuperSubject() != null) {
                    destDAO.create(entry);
                }
            }
        }
        else {
            for (IDatabaseEntity entry : allEntries) {
                destDAO.update(entry);
            }
        }
    }

    private static void rewriteGroups(Session oldSession, Session newSession) {
        int newYear = findNewYear(oldSession);
    }

    private static int findNewYear(Session oldSession) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static List<Class<? extends IDatabaseEntity>> getInSpecificOrder() {
        List<Class<? extends IDatabaseEntity>> entityClasses = new ArrayList<>();
        entityClasses.add(Cycle.class);
        entityClasses.add(SubjectType.class);
        entityClasses.add(Section.class);
        entityClasses.add(Department.class);
        entityClasses.add(SubjectDictionary.class);
        
        return entityClasses;
    }

}
