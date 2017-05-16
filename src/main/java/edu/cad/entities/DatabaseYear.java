package edu.cad.entities;

import edu.cad.entities.interfaces.IDatabaseEntity;
import javax.persistence.*;

@Entity
@Table(name = "database_year")
public class DatabaseYear implements IDatabaseEntity {
    
    @Id
    @Column(name = "year", unique = true, nullable = false)
    private int year;

    public DatabaseYear() {
    }
    
    public DatabaseYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.year;
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
        /*if (getClass() != obj.getClass()) {
            return false;
        }*/
        final DatabaseYear other = (DatabaseYear) obj;
        if (this.year != other.getYear()) {
            return false;
        }
        return true;
    }
}
