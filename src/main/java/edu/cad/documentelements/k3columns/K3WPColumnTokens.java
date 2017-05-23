package edu.cad.documentelements.k3columns;

import edu.cad.utils.documentutils.ColumnTokenStringSplitter;

public interface K3WPColumnTokens {
    String TOKEN_BEGINNING = 
            ColumnTokenStringSplitter.K3_WP_TOKEN_BEGINNING;
    
    String FULL_TITLE   = TOKEN_BEGINNING + "fulltitle";
    String SEM_HOURS    = TOKEN_BEGINNING + "semhours";
    String LECTIONS     = TOKEN_BEGINNING + "lection";
    String PRACTICE     = TOKEN_BEGINNING + "practice";
    String LABS         = TOKEN_BEGINNING + "lab";
    String INDIVIDUALS  = TOKEN_BEGINNING + "individual";
    
    String EXAMS            = TOKEN_BEGINNING + "exam";
    String CREDITS          = TOKEN_BEGINNING + "credit";
    String CONTROL_WORKS    = TOKEN_BEGINNING + "contrwork";
    String COURSE_PROJS     = TOKEN_BEGINNING + "courseproj";
    String COURSE_WORKS     = TOKEN_BEGINNING + "coursework";
    String RGRS             = TOKEN_BEGINNING + "rgr";
    String DKR              = TOKEN_BEGINNING + "dkr";
    String REFERATS         = TOKEN_BEGINNING + "referat";
    
    String AC_BUDG_GROUPS   = TOKEN_BEGINNING + "acgroupb";
    String SUBGR_PRACT_BUDG = TOKEN_BEGINNING + "subgrpractb";
    String SUBGR_LABS_BUDG  = TOKEN_BEGINNING + "subgrlabsb";
    
    String AC_CONT_GROUPS   = TOKEN_BEGINNING + "acgroupc";
    String SUBGR_PRACT_CONT = TOKEN_BEGINNING + "subgrpractc";
    String SUBGR_LABS_CONT  = TOKEN_BEGINNING + "subgrlabsc";
    
    String BUDG_GR_BUDG_STUD = TOKEN_BEGINNING + "groupbstb";
    String BUDG_GR_CONT_STUD = TOKEN_BEGINNING + "groupbstc";
    String CONT_GR_BUDG_STUD = TOKEN_BEGINNING + "groupcstb";
    String CONT_GR_CONT_STUD = TOKEN_BEGINNING + "groupcstc";
    
    String BUDG_STREAM = TOKEN_BEGINNING + "streamb";
    String CONT_STREAM = TOKEN_BEGINNING + "streamc";
   
}
