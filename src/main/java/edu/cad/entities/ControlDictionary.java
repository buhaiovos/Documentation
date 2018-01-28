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
@Table(name = "dict_control")
@Getter
@Setter
@ToString
public class ControlDictionary implements IDatabaseEntity, Serializable {
    @Expose
    @Id
    @GenericGenerator(
        name = "assigned-identity", 
        strategy = "edu.cad.utils.hibernateutils.AssignedIdentityGenerator"
    )
    @GeneratedValue(generator = "assigned-identity")
    @Column(name = "id",
            unique = true,
            nullable = false)
    private int id;
    
    @Expose
    @Column(name = "denotation")
    private String denotation;

    public ControlDictionary() {}

    public ControlDictionary(int id, String denotation) {
        this.id = id;
        this.denotation = denotation;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
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
        if ( !(obj instanceof ControlDictionary) ) {
            return false;
        }
        final ControlDictionary other = (ControlDictionary) obj;
        if (this.id != other.getId()) {
            return false;
        }
        return true;
    }
    
}
