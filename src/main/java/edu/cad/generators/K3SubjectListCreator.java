package edu.cad.generators;

import edu.cad.daos.HibernateDAO;
import edu.cad.entities.AcademicGroup;
import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Department;
import edu.cad.entities.EducationForm;
import edu.cad.entities.Subject;
import edu.cad.entities.Workplan;
import edu.cad.utils.hibernateutils.EntityCloner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class K3SubjectListCreator {
    
    public static List<Subject> createList(EducationForm educationForm, 
            Department department){
        Set<Subject> subjectSet = createSubjectSet(createWorkplanSet(educationForm), department);
        List<Subject> subjectList = new ArrayList<>();
        
        for(Subject subject : subjectSet){
            Map<Department, List<AcademicGroup>> map = new HashMap<>();
            
            for(AcademicGroup group : subject.getGroups()){
                if(!group.getDepartment().equals(department)){
                    if(!map.containsKey(group.getDepartment())){
                        map.put(group.getDepartment(), new ArrayList<>());
                    }
                    
                    map.get(group.getDepartment()).add(group);
                    subject.getGroups().remove(group);
                }
            }
            
            subjectList.add(subject);
            
            /*if(!map.isEmpty()){
                for(Department key : map.keySet()){
                    Subject clone = EntityCloner.
                }
            }*/
            
            
        }
        
        return subjectList;
    }
    
    private static Set<Workplan> createWorkplanSet(EducationForm educationForm){
        List<Workplan> workplans = new HibernateDAO<>(Workplan.class).getAll();
        Iterator<Workplan> iterator = workplans.iterator();
        
        while(iterator.hasNext()){
            Workplan workplan = iterator.next();
            
            if(!workplan.getEducationForm().equals(educationForm)){
                iterator.remove();
            }
        }
        
        return new TreeSet<>(workplans);
    }
    
    private static Set<Subject> createSubjectSet(Set<Workplan> workplans, 
            Department department){
        Set<Subject> subjects = new LinkedHashSet<>();
        
        for(Workplan workplan : workplans){
            Set<CurriculumSubject> workplanSubjects = new TreeSet<>();
            workplanSubjects.addAll(workplan.getCurriculumSubjects());
            
            for(CurriculumSubject curriculumSubject : workplanSubjects){
                Subject subject = curriculumSubject.getSubject();
                
                if(subject.getSubject().getDepartment().equals(department)){
                    subjects.add(subject);
                }
            }
        }
        
        return subjects;
    }
}
