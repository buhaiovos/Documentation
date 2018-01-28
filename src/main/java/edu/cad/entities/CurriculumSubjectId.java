package edu.cad.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class CurriculumSubjectId implements Serializable{
    
    @ManyToOne
    private Curriculum curriculum;
    
    @ManyToOne
    private Subject subject;

    public CurriculumSubjectId() {
    }

    public CurriculumSubjectId(Curriculum curriculum, Subject subject) {
        this.curriculum = curriculum;
        this.subject = subject;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.curriculum);
        hash = 97 * hash + Objects.hashCode(this.subject);
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
        if ( !(obj instanceof CurriculumSubjectId)) {
            return false;
        }
        final CurriculumSubjectId other = (CurriculumSubjectId) obj;
        if (!this.curriculum.equals(other.getCurriculum())) {
            return false;
        }
        if (!this.subject.equals(other.getSubject())) {
            return false;
        }
        return true;
    }   
}
