package edu.cad.entities;

import edu.cad.entities.interfaces.IDatabaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "cycle")
public class Cycle implements IDatabaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "denotation")
    private String denotation;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cycle")
    private Set<Section> sections = new HashSet<>();

    public Cycle() {
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cycle other = (Cycle) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
