/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cad.uils.documentutils;

import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.RefPtgBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Олександр
 */
public class FormulaCopier {
    
    public static void copyFormula(Sheet sheet, Cell src, Cell dest) {
        if (!isInputValid(sheet, src, dest)) {
            return;
        }
        
        String formula = src.getCellFormula();
        int shiftRows = dest.getRowIndex() - src.getRowIndex();
        int shiftCols = dest.getColumnIndex() - src.getColumnIndex();
        
        XSSFEvaluationWorkbook workbookWrapper = 
                XSSFEvaluationWorkbook.create((XSSFWorkbook) sheet.getWorkbook());
        
        Ptg[] ptgs = FormulaParser.parse(formula, workbookWrapper, FormulaType.CELL
                , sheet.getWorkbook().getSheetIndex(sheet));
        
        for (Ptg ptg : ptgs) {
            if (ptg instanceof RefPtgBase) { // base class for cell references
                shiftCellReferences(ptg, shiftCols, shiftRows);
            } 
            else if (ptg instanceof AreaPtg) { // base class for range references
                shiftAreaReferences(ptg, shiftCols, shiftRows);
            }
        }
        formula = FormulaRenderer.toFormulaString(workbookWrapper, ptgs);
        dest.setCellFormula(formula);
    }
    
    private static boolean isInputValid(Sheet sheet, Cell src, Cell dest) {
        if (src == null || dest == null || sheet == null 
                || src.getCellType() != Cell.CELL_TYPE_FORMULA) {
            return false;
        }
        if (src.isPartOfArrayFormulaGroup()) {
            return false;
        }
        
        return true;
    }
    
    private static void shiftCellReferences(Ptg ptg, int shiftCols, 
                                                        int shiftRows) {
        RefPtgBase ref = (RefPtgBase) ptg;
        if (ref.isColRelative()) {
            ref.setColumn(ref.getColumn() + shiftCols);
        }
        if (ref.isRowRelative()) {
            ref.setRow(ref.getRow() + shiftRows);
        }
    }
    
    private static void shiftAreaReferences(Ptg ptg, int shiftCols, 
                                                        int shiftRows) {
        AreaPtg ref = (AreaPtg) ptg;
        if (ref.isFirstColRelative())
             ref.setFirstColumn(ref.getFirstColumn() + shiftCols);
        if (ref.isLastColRelative())
            ref.setLastColumn(ref.getLastColumn() + shiftCols);
        if (ref.isFirstRowRelative())
            ref.setFirstRow(ref.getFirstRow() + shiftRows);
        if (ref.isLastRowRelative())
            ref.setLastRow(ref.getLastRow() + shiftRows);
    }
}