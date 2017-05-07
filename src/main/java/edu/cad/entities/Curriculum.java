package edu.cad.entities;

import edu.cad.entities.interfaces.IDatabaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "curriculum")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "curriculum")
public class Curriculum implements IDatabaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.curriculum", cascade = CascadeType.ALL)
    private Set<CurriculumSubject> curriculumSubjects = new HashSet<>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curriculum")
    private Set<Workplan> workplans = new HashSet<>();

    public Curriculum() {
    }

    public Curriculum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<CurriculumSubject> getCurriculumSubjects() {
        return curriculumSubjects;
    }

    public void setCurriculumSubjects(Set<CurriculumSubject> curriculumSubjects) {
        this.curriculumSubjects = curriculumSubjects;
    }

    public Set<Workplan> getWorkplans() {
        return workplans;
    }

    public void setWorkplans(Set<Workplan> workplans) {
        this.workplans = workplans;
    }
    
    public Qualification getQualification() {
        return workplans.iterator().next().getQualification();
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
        /*if (getClass() != obj.getClass()) {
            return false;
        }*/
        final Curriculum other = (Curriculum) obj;
        if (this.id != other.getId()) {
            return false;
        }
        return true;
    }
}
