package edu.cad.entities;

import com.google.gson.annotations.Expose;
import edu.cad.entities.interfaces.IDatabaseEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "section")
@Getter
@Setter
public class Section implements IDatabaseEntity, Serializable {
    @Expose
    @Id
    @GenericGenerator(
        name = "assigned-identity", 
        strategy = "edu.cad.utils.hibernateutils.AssignedIdentityGenerator"
    )
    @GeneratedValue(generator = "assigned-identity")
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Expose
    @Column(name = "denotation")
    private String denotation;
    
    @Expose
    @Column(name = "is_optional", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean optional;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cycle")
    private Cycle cycle;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curriculumSection")
    private Set<SubjectDictionary> curriculumSubjects = new HashSet<>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workplanSection")
    private Set<SubjectDictionary> workplanSubjects = new HashSet<>();

    public Section() {
    }

    public Section(int id, String denotation, boolean isOptional, Cycle cycle) {
        this.id = id;
        this.denotation = denotation;
        this.optional = isOptional;
        this.cycle = cycle;
    }

    public void setCurriculumSubjects(Set<SubjectDictionary> curriculumSubjects) {
        this.curriculumSubjects.clear();
        this.curriculumSubjects.addAll(curriculumSubjects);
    }

    public void setWorkplanSubjects(Set<SubjectDictionary> workplanSubjects) {
        this.workplanSubjects.clear();
        this.workplanSubjects.addAll(workplanSubjects);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
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
        if ( !(obj instanceof Section)) {
            return false;
        }
        final Section other = (Section) obj;
        if (this.id != other.getId()) {
            return false;
        }
        return true;
    }

}
