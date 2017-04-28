package edu.cad.entities;

import edu.cad.entities.interfaces.IDatabaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "curriculum")
public class Curriculum implements IDatabaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "year")
    private int year;
    
    @Column(name = "is_workplan", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isWorkplan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_specialization")
    private Specialization specialization;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_practice")
    private Practice practice;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_state_certification")
    private StateCertification stateCertification;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_education_form")
    private EducationForm educationForm;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_qualification")
    private Qualification qualification;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.curriculum", cascade = CascadeType.ALL)
    private Set<CurriculumSubject> curriculumSubjects = new HashSet<>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curriculum")
    private Set<DiplomaPreparation> diplomaPreparations = new HashSet<>();

    public Curriculum() {
    }

    public Curriculum(int id, int year, boolean isWorkplan, 
            Specialization specialization, Practice practice, 
            StateCertification stateCertification, EducationForm educationForm, 
            Qualification qualification) {
        this.id = id;
        this.year = year;
        this.isWorkplan = isWorkplan;
        this.specialization = specialization;
        this.practice = practice;
        this.stateCertification = stateCertification;
        this.educationForm = educationForm;
        this.qualification = qualification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isIsWorkplan() {
        return isWorkplan;
    }

    public void setIsWorkplan(boolean isWorkplan) {
        this.isWorkplan = isWorkplan;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Practice getPractice() {
        return practice;
    }

    public void setPractice(Practice practice) {
        this.practice = practice;
    }

    public StateCertification getStateCertification() {
        return stateCertification;
    }

    public void setStateCertification(StateCertification stateCertification) {
        this.stateCertification = stateCertification;
    }

    public EducationForm getEducationForm() {
        return educationForm;
    }

    public void setEducationForm(EducationForm educationForm) {
        this.educationForm = educationForm;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Set<CurriculumSubject> getCurriculumSubjects() {
        return curriculumSubjects;
    }

    public void setCurriculumSubjects(Set<CurriculumSubject> curriculumSubjects) {
        this.curriculumSubjects = curriculumSubjects;
    }

    public Set<DiplomaPreparation> getDiplomaPreparations() {
        return diplomaPreparations;
    }

    public void setDiplomaPreparations(Set<DiplomaPreparation> diplomaPreparations) {
        this.diplomaPreparations = diplomaPreparations;
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
        final Curriculum other = (Curriculum) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
