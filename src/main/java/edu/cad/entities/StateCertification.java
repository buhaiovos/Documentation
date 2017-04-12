package edu.cad.entities;

import edu.cad.entities.interfaces.IDatabaseEntity;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "state_certification")
public class StateCertification implements IDatabaseEntity{
    
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getDenotation() {
        return denotation;
    }

    public void setDenotation(String denotation) {
        this.denotation = denotation;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
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
