package edu.cad.entities;

import javax.persistence.*;

@Entity
@Table(name = "type_of_work")
public class WorkType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "denotation")
    private String denotation;

    public WorkType() {
    }

    public WorkType(int id, String denotation) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
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
        /*if (getClass() != obj.getClass()) {
            return false;
        }*/
        final WorkType other = (WorkType) obj;
        if (this.id != other.getId()) {
            return false;
        }
        return true;
    }
}
