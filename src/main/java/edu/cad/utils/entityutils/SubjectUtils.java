package edu.cad.utils.entityutils;

import edu.cad.entities.Curriculum;
import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Qualification;
import edu.cad.entities.Subject;
import edu.cad.entities.SubjectDictionary;
import edu.cad.entities.Workplan;
import java.util.HashSet;
import java.util.Set;

public class SubjectUtils {
   
    public static Set<Subject> getChildren(Subject subject, Curriculum curriculum){
        Set<Subject> children = new HashSet<>();

        for(SubjectDictionary dictionary : subject.getSubject().getSubSubjects()){
            boolean contains = false;
            
            for(Subject element : dictionary.getAcademicSubjects()){  
                if(contains(element, curriculum)){
                    children.add(element);
                    children.addAll(getChildren(element, curriculum));
                    contains = true;
                    break;
                }  
            }
            
            if(!contains){
                Subject appropriate = findAppropriate(dictionary.getAcademicSubjects(),
                        curriculum);
                children.add(appropriate);
                children.addAll(getChildren(appropriate, curriculum));
            }
            
            
        }
        
        return children;
    }
    
    private static boolean contains(Subject subject, Curriculum curriculum){
        for(Workplan plan : curriculum.getWorkplans()){
            for(CurriculumSubject currSubject : plan.getCurriculumSubjects()){
                if(currSubject.getSubject().equals(subject)){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private static Subject findAppropriate(Set<Subject> subjects, Curriculum curriculum){
        curriculum.getQualification().getId();
        Qualification current = curriculum.getQualification();
        
        for(Subject subject : subjects){
            for(CurriculumSubject currSubject : subject.getCurriculumSubjects()){
                if(currSubject.getCurriculum().getQualification().equals(current)){
                    return subject;
                }
            } 
        }

        return new Subject();
    }
}
