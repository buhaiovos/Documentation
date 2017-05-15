package edu.cad.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@DiscriminatorValue("workplan")
public class Workplan extends Curriculum {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_practice")
    private Practice practice;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_state_certification")
    private StateCertification stateCertification;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curriculum")
    private Curriculum curriculum;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.curriculum", cascade = CascadeType.ALL)
    private Set<CurriculumSubject> curriculumSubjects = new HashSet<>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curriculum")
    private Set<DiplomaPreparation> diplomaPreparations = new HashSet<>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workplan")
    private Set<AcademicGroup> groups = new HashSet<>();
    
    @Override
    public Qualification getQualification() {
        return groups.iterator().next().getQualification();
    }

    public Workplan() {
    }

    public Workplan(int id, Practice practice, 
            StateCertification stateCertification, Curriculum curriculum) {
        super(id);
        this.practice = practice;
        this.stateCertification = stateCertification;
        this.curriculum = curriculum;
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

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    @Override
    public Set<CurriculumSubject> getCurriculumSubjects() {
        return curriculumSubjects;
    }

    @Override
    public void setCurriculumSubjects(Set<CurriculumSubject> curriculumSubjects) {
        this.curriculumSubjects = curriculumSubjects;
    }

    public Set<DiplomaPreparation> getDiplomaPreparations() {
        return diplomaPreparations;
    }

    public void setDiplomaPreparations(Set<DiplomaPreparation> diplomaPreparations) {
        this.diplomaPreparations = diplomaPreparations;
    }

    public Set<AcademicGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<AcademicGroup> groups) {
        this.groups = groups;
    }
    
    public int countBudgetaryStudents(){
        int total = 0; 
        
        for(AcademicGroup group : groups){
            total += group.getBudgetaryStudents();
        }
        
        return total;
    }
    
    public int countContractStudents(){
        int total = 0; 
        
        for(AcademicGroup group : groups){
            total += group.getContractStudents();
        }
        
        return total;
    }
    
    public int countTotalStudents(){
        int total = 0; 
        
        for(AcademicGroup group : groups){
            total += group.getTotalStudents();
        }
        
        return total;
    }
}

