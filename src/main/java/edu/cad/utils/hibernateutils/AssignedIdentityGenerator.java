package edu.cad.utils.hibernateutils;

import edu.cad.entities.interfaces.IDatabaseEntity;
import java.io.Serializable;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

public class AssignedIdentityGenerator extends IdentityGenerator {
 
    @Override
    public Serializable generate(SessionImplementor session, Object obj) {
        if(obj instanceof IDatabaseEntity) {
            IDatabaseEntity entity = (IDatabaseEntity) obj;
            int id = entity.getId();
            if(id > 0) {
                return id;
            }
        }
        int ig = (int) super.generate(session, obj);
        
        System.out.println("\n\n\n" + ig + "\n\n\n");
        return ig;
    }
}
