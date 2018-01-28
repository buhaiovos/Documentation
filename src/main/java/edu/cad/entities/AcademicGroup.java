package edu.cad.entities;

import com.google.gson.annotations.Expose;
import edu.cad.entities.interfaces.IDatabaseEntity;
import java.io.Serializable;
import javax.persistence.*;

import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "academic_group")
@Getter
@Setter
@ToString
public class AcademicGroup implements IDatabaseEntity, Serializable,
        Comparable<AcademicGroup> {

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
    @Column(name = "cipher")
    private String cipher;

    @Expose
    @Column(name = "budgetary_students")
    private int budgetaryStudents;

    @Expose
    @Column(name = "contract_students")
    private int contractStudents;

    @Expose
    @Column(name = "start_year")
    private int startYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_specialization")
    private Specialization specialization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_qualification")
    private Qualification qualification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_education_form")
    private EducationForm educationForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_workplan")
    private Workplan workplan;

    public AcademicGroup() {
    }

    public AcademicGroup(int id, String cipher, int budgetaryStudents,
                         int contractStudents, int startYear, Specialization specialization,
                         Qualification qualification, EducationForm educationForm,
                         Workplan workplan) {
        this.id = id;
        this.cipher = cipher;
        this.budgetaryStudents = budgetaryStudents;
        this.contractStudents = contractStudents;
        this.startYear = startYear;
        this.specialization = specialization;
        this.qualification = qualification;
        this.educationForm = educationForm;
        this.workplan = workplan;
    }

    public Department getDepartment() {
        return specialization.getDepartment();
    }

    public int getTotalStudents() {
        return budgetaryStudents + contractStudents;
    }

    public boolean isBudgetary() {
        if (budgetaryStudents > 0)
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        if ( !(obj instanceof AcademicGroup)) {
            return false;
        }
        final AcademicGroup other = (AcademicGroup) obj;
        if (this.id != other.getId()) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(AcademicGroup other) {
        return other.startYear - this.startYear;
    }
}
