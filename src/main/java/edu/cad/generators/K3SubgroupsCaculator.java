package edu.cad.generators;

import edu.cad.entities.AcademicGroup;
import edu.cad.entities.Subject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class K3SubgroupsCaculator {
    
    public static List<K3SubjectEntity> calculateList(List<Subject> subjects,
            SourceOfFinancing source){
        removeEmptySubjects(subjects, source);
        if(subjects.isEmpty())
            return new ArrayList<>();
        
        List<K3SubjectEntity> entities = createList(subjects);
        
        for(TypeOfGroupWork type : TypeOfGroupWork.values()){
            calculateSubgroups(entities, type, source);
        }  

        return entities;
    }
    
    private static List<K3SubjectEntity> createList(List<Subject> subjects){
        List<K3SubjectEntity> entities = new ArrayList<>();
        
        for(Subject subject : subjects){
            entities.add(new K3SubjectEntity(subject));
        }
        
        return entities;
    }
    
    private static void removeEmptySubjects(List<Subject> subjects, 
            SourceOfFinancing source){
        Iterator<Subject> iterator = subjects.iterator();
        
        while (iterator.hasNext()) {
            int total = 0;
            
            for(AcademicGroup group : iterator.next().getGroups()){
                total += source.getStudents(group);
            }
            
            if(total == 0){
                iterator.remove();
            }
        }
    }
    
    private static void calculateSubgroups(List <K3SubjectEntity> subjects,
            TypeOfGroupWork type, SourceOfFinancing source){
        if(type.equals(TypeOfGroupWork.Academic)){
            calculateAcademicGroups(subjects, source);
            return;
        }
        
        if(type.equals(TypeOfGroupWork.OtherSource)){
            return;
        }
        
        K3SubjectEntity currentSubject = subjects.get(0);
        int total = 0;
        
        for(K3SubjectEntity subjectEntity : subjects){
            Subject subject = subjectEntity.getSubject();
            int subjectTotal = 0;
            
            for(AcademicGroup group : subject.getGroups()){
                if(!source.sourceEquals(group))
                    continue;
                
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
    
    private static void calculateAcademicGroups(List<K3SubjectEntity> subjects,
            SourceOfFinancing source){
        for(K3SubjectEntity subject : subjects){
            for(AcademicGroup group : subject.getSubject().getGroups()){
                if(source.sourceEquals(group)){
                    subject.addSubgroups(TypeOfGroupWork.Academic, 1);
                } else {
                    subject.addSubgroups(TypeOfGroupWork.OtherSource, 1);
                }
            }
            
            if(subjects.indexOf(subject) != 0){
                resetHours(TypeOfGroupWork.Academic, subject.getSubject());
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
            case Academic   :   subject.setLections(0);
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
