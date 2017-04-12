package edu.cad.entities;

import edu.cad.entities.interfaces.IDatabaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Олександр
 */
@Entity
@Table(name = "qualification")
public class Qualification implements IDatabaseEntity{
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "denotation")
    String denotation;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "qualification")
    private Set<AcademicGroup> academicGroups = new HashSet<>(0);

    public Qualification() {
    }

    public Qualification(int id, String denotation) {
        this.id = id;
        this.denotation = denotation;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenotation() {
        return denotation;
    }

    public void setDenotation(String denotation) {
        this.denotation = denotation;
    }

    public Set<AcademicGroup> getAcademicGroups() {
        return academicGroups;
    }

    public void setAcademicGroups(Set<AcademicGroup> academicGroups) {
        this.academicGroups = academicGroups;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
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
        final Qualification other = (Qualification) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
       
}
