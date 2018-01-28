package edu.cad.entities;

import com.google.gson.annotations.Expose;
import edu.cad.entities.interfaces.IDatabaseEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "state_certification")
@Getter
@Setter
public class StateCertification implements IDatabaseEntity, Serializable {
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
    
    @Expose
    @Column(name = "form")
    private String form;
    
    @Expose
    @Column(name = "start")
    private Date start;
    
    @Expose
    @Column(name = "finish")
    private Date finish;

    public StateCertification() {
    }

    public StateCertification(int id, int semester, String form, Date start, Date finish) {
        this.id = id;
        this.semester = semester;
        this.form = form;
        this.start = start;
        this.finish = finish;
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
        if ( !(obj instanceof StateCertification)) {
            return false;
        }
        final StateCertification other = (StateCertification) obj;
        if (this.id != other.getId()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return form + " (" + semester + " семестр)";
    }
    
    
}
