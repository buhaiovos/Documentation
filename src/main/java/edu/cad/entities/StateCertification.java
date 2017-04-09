package edu.cad.entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "state_certification")
public class StateCertification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "semester")
    private int semester;
    
    @Column(name = "denotation")
    private String denotation;
    
    @Column(name = "form")
    private String form;
    
    @Column(name = "start")
    private Date start;
    
    @Column(name = "finish")
    private Date finish;

    public StateCertification() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final StateCertification other = (StateCertification) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
