package edu.cad.entities;

import javax.persistence.*;

@Entity
@Table(name = "dict_control")
public class ControlDictionary {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "denotation")
    private String denotation;

    public ControlDictionary() {
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ControlDictionary other = (ControlDictionary) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
