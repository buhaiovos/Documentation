/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cad.uils.documentutils;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author Олександр
 */
public class DateIntervalStringCreator {
    public static String getDatesString(Date st, Date fin) {
        String start = getProperlyFormattedDate(st);
        String finish = getProperlyFormattedDate(fin);
        
        return "з " + start + " по " + finish;
    }

    private static String getProperlyFormattedDate(Date date) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        return df.format(date).substring(0, 5);
    }
}
