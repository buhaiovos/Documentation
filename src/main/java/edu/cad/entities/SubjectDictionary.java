package edu.cad.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "dict_subjects")
public class SubjectDictionary {
    
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
}

