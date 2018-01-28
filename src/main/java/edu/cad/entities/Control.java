package edu.cad.entities;

import com.google.gson.annotations.Expose;
import edu.cad.entities.interfaces.IDatabaseEntity;
import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "control")
@Getter
@Setter
public class Control implements IDatabaseEntity, Serializable, Comparable<Control> {
    @Expose
    @Id
    @GenericGenerator(
        name = "assigned-identity", 
        strategy = "edu.cad.utils.hibernateutils.AssignedIdentityGenerator"
    )
    @GeneratedValue(generator = "assigned-identity")
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Expose
    @Column(name = "semester")
    private int semester;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    private ControlDictionary type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subject")
    private Subject subject;

    public Control() {
    }

    public Control(int id, int semester, ControlDictionary type, 
            Subject subject) {
        this.id = id;
        this.semester = semester;
        this.type = type;
        this.subject = subject;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
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
        if ( !(obj instanceof Control)) {
            return false;
        }
        final Control other = (Control) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }   

    @Override
    public int compareTo(Control other) {
        return this.semester - other.semester;
    }

    @Override
    public String toString() {
        if(type.getId() == 9)
            return semester + "д";
        
        return Integer.toString(semester);
    }
}
