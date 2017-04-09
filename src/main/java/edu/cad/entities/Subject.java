package edu.cad.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "academic_subject")
public class Subject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "semester")
    private int semester; 
    
    @Column(name = "semesters_duration")
    private int semestersDuration;
    
    @Column(name = "lections")
    private int lections;
    
    @Column(name = "labs")
    private int labs;
    
    @Column(name = "practices")
    private int practices;
    
    @Column(name = "ECST")
    private float ects;
    
    @Column(name = "cipher")
    private String cipher;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_subject")
    private SubjectDictionary subject;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private Set<Control> controls = new HashSet<>();
    
    /*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "curriculum_subject", joinColumns = {
        @JoinColumn(name = "id_subject")},
            inverseJoinColumns = { @JoinColumn(name = "id_curriculum")                 
    })
    private Set<Curriculum> curriculums = new HashSet<>();*/

    public Subject() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.id;
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
        final Subject other = (Subject) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
}
