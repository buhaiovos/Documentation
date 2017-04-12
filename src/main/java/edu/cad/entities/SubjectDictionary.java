package edu.cad.entities;

import edu.cad.entities.interfaces.IDatabaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "dict_subjects")
public class SubjectDictionary implements IDatabaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "denotation")
    private String denotation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_supersubject")
    private SubjectDictionary superSubject;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subjectDictionary")
    private Set<SubjectDictionary> subSubjects = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_section_curriculum")
    private Section curriculumSection;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_section_workplan")
    private Section workplanSection;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    private SubjectType type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_department")
    private Department department;
    
    @OneToOne(mappedBy = "subjectDictionary")
    private Subject academicSubject;

    public SubjectDictionary() {
    }

    public SubjectDictionary(int id, String denotation, 
                    SubjectDictionary superSubject, Section curriculumSection, 
                    Section workplanSection, SubjectType type, 
                    Department department, Subject academicSubject) {
        this.id = id;
        this.denotation = denotation;
        this.superSubject = superSubject;
        this.curriculumSection = curriculumSection;
        this.workplanSection = workplanSection;
        this.type = type;
        this.department = department;
        this.academicSubject = academicSubject;
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

    public SubjectDictionary getSuperSubject() {
        return superSubject;
    }

    public void setSuperSubject(SubjectDictionary superSubject) {
        this.superSubject = superSubject;
    }

    public Set<SubjectDictionary> getSubSubjects() {
        return subSubjects;
    }

    public void setSubSubjects(Set<SubjectDictionary> subSubjects) {
        this.subSubjects = subSubjects;
    }

    public Section getCurriculumSection() {
        return curriculumSection;
    }

    public void setCurriculumSection(Section curriculumSection) {
        this.curriculumSection = curriculumSection;
    }

    public Section getWorkplanSection() {
        return workplanSection;
    }

    public void setWorkplanSection(Section workplanSection) {
        this.workplanSection = workplanSection;
    }

    public SubjectType getType() {
        return type;
    }

    public void setType(SubjectType type) {
        this.type = type;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Subject getAcademicSubject() {
        return academicSubject;
    }

    public void setAcademicSubject(Subject academicSubject) {
        this.academicSubject = academicSubject;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
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
        final SubjectDictionary other = (SubjectDictionary) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}

