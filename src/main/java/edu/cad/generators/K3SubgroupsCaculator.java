package edu.cad.generators;

import edu.cad.entities.AcademicGroup;
import edu.cad.entities.Subject;
import java.util.ArrayList;
import java.util.List;

public class K3SubgroupsCaculator {
    
    public static List<K3SubjectEntity> calculateList(List<Subject> subjects){
        List<K3SubjectEntity> entities = new ArrayList<>();
        
        for(Subject subject : subjects){
            entities.add(new K3SubjectEntity(subject));
        }
        
        for(TypeOfGroupWork type : TypeOfGroupWork.values()){
            calculateSubgroups(entities, type);
        }  
        
        return entities;
    }
    
    private static void calculateSubgroups(List <K3SubjectEntity> subjects,
            TypeOfGroupWork type){
        if(type.equals(TypeOfGroupWork.Lection)){
            calculateAcademicGroups(subjects);
            return;
        }

        K3SubjectEntity currentSubject = subjects.get(0);
        int total = 0;
        
        for(K3SubjectEntity subjectEntity : subjects){
            Subject subject = subjectEntity.getSubject();
            int subjectTotal = 0;
            
            for(AcademicGroup group : subject.getGroups()){
                if(canBeAdded(type, group, subjectTotal)){
                    subjectTotal += group.getTotalStudents();
                } else {
                    currentSubject.addSubgroups(type, calculateSubgroups(type, subjectTotal));
                    
                    currentSubject = subjectEntity;
                    subjectTotal = group.getTotalStudents();
                    total = subjectTotal;
                }
            }
            
            if(currentSubject != subjectEntity){
                resetHours(type, subject);
            }
            
            if(total + subjectTotal <= type.getMaxStudents()){
                total += subjectTotal;
            } else {
                total = subjectTotal;
            }
        } 
        
        currentSubject.addSubgroups(type, calculateSubgroups(type, total));
    }
    
    private static void calculateAcademicGroups(List<K3SubjectEntity> subjects){
        for(K3SubjectEntity subject : subjects){
            int groups = subject.getSubject().getGroups().size();
            subject.addSubgroups(TypeOfGroupWork.Lection, groups);
            
            if(subjects.indexOf(subject) != 0){
                resetHours(TypeOfGroupWork.Lection, subject.getSubject());
            }
        } 
    }
    
    private static boolean canBeAdded(TypeOfGroupWork type, AcademicGroup group, 
            int total){
        if(total == 0)
            return true;
        
        if(group.getTotalStudents() > type.getMinStudents())
            return false;
        
        return group.getTotalStudents() + total <= type.getMaxStudents();
    }
    
    private static void resetHours(TypeOfGroupWork type, Subject subject){
        switch(type){
            case Lection    :   subject.setLections(0);
                                break;
            case Practice   :   subject.setPractices(0);
                                break;
            case Lab        :   subject.setLabs(0);
                                break;
        }
    }
    
    private static int calculateSubgroups(TypeOfGroupWork type, int total){
        return (int) Math.ceil(total / (double) type.getMaxStudents());
    }
}
