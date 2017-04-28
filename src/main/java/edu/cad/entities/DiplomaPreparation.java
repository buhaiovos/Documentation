package edu.cad.entities;

import edu.cad.entities.interfaces.IDatabaseEntity;
import javax.persistence.*;

@Entity
@Table(name = "diploma_preparation")
public class DiplomaPreparation implements IDatabaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "norm")
    private float norm;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_of_work")
    private WorkType workType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_department")
    private Department department;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curriculum")
    private Curriculum curriculum;

    public DiplomaPreparation() {
    }

    public DiplomaPreparation(int id, float norm, WorkType workType, 
            Department department, Curriculum curriculum) {
        this.id = id;
        this.norm = norm;
        this.workType = workType;
        this.department = department;
        this.curriculum = curriculum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNorm() {
        return norm;
    }

    public void setNorm(float norm) {
        this.norm = norm;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }
    
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
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
