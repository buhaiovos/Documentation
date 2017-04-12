package edu.cad.entities;

import edu.cad.entities.interfaces.IDatabaseEntity;
import javax.persistence.*;

@Entity
@Table(name = "diploma_preparation")
public class DiplomaPreparation implements IDatabaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "course")
    private int course;
    
    @Column(name = "norm")
    private float norm;
    
    @Column(name = "denotation")
    private String denotation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_department")
    private Department department;

    public DiplomaPreparation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public float getNorm() {
        return norm;
    }

    public void setNorm(float norm) {
        this.norm = norm;
    }

    public String getDenotation() {
        return denotation;
    }

    public void setDenotation(String denotation) {
        this.denotation = denotation;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DiplomaPreparation other = (DiplomaPreparation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
