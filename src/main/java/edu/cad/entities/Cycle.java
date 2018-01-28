package edu.cad.entities;

import com.google.gson.annotations.Expose;
import edu.cad.entities.interfaces.IDatabaseEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cycle")
@Getter
@Setter
public class Cycle implements IDatabaseEntity, Serializable{
    
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
    @Column(name = "denotation")
    private String denotation;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cycle")
    private Set<Section> sections = new HashSet<>();

    public Cycle() {
    }

    public Cycle(int id, String denotation) {
        this.id = id;
        this.denotation = denotation;
    }

    public void setSections(Set<Section> sections) {
        this.sections.clear();
        this.sections.addAll(sections);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id;
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
        if ( !(obj instanceof Cycle)) {
            return false;
        }
        final Cycle other = (Cycle) obj;
        if (this.id != other.getId()) {
            return false;
        }
        return true;
    }   
}
