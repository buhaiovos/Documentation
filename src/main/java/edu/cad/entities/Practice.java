package edu.cad.entities;

import edu.cad.entities.interfaces.IDatabaseEntity;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "practice")
public class Practice implements IDatabaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "semester")
    private int semester;
    
    @Column(name = "weeks")
    private int weeks;
    
    @Column(name = "denotation")
    private String denotation;
    
    @Column(name = "start")
    private Date start;
    
    @Column(name = "finish")
    private Date finish;

    public Practice() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Practice other = (Practice) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
