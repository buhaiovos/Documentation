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

