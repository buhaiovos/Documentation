package edu.cad.entities;

import edu.cad.entities.interfaces.IDatabaseEntity;
import javax.persistence.*;

@Entity
@Table(name = "control")
public class Control implements IDatabaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "semester")
    private int semester;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    private ControlDictionary type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subject")
    private Subject subject;

    public Control() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
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
        final Control other = (Control) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
