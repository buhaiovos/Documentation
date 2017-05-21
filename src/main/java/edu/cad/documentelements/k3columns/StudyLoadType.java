/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cad.documentelements.k3columns;

/**
 *
 * @author Олександр
 */
public enum StudyLoadType {
    
    LECTIONS("k3lection"), 
    PRACTICES("k3practice"),
    LABS("k3lab"),
    INDIVIDUALS("k3individual"),
    EXAMS("k3exam"),
    CREDITS("k3credit"),
    CONTROL_WORKS("k3contrwork"),
    COURSE_PROJECTS("k3courseproj"),
    COURSEWORKS("k3coursework"),
    RGRS("k3rgr"),
    DKR("k3dkr"),
    REFERATS("k3referat"),
    CONSULTATIONS("k3consult");
    
    private final String token;
    
    private StudyLoadType(String token) {
        this.token = token;
    }
    
    public static StudyLoadType getByToken(String token) {
        StudyLoadType[] values = StudyLoadType.values();
        for (StudyLoadType value : values) {
            if (value.token.equals(token))
                return value;
        }
        return null;
    }
    
    public String getToken() {
        return token;
    }
    
}
