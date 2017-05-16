package edu.cad.utils.documentutils;

import edu.cad.utils.Utils;

/**
 *
 * @author Олександр
 */
public class ColumnTokenStringSplitter {
    
    private String token;
    private String type;
    private String firstNumString;
    private String secondNumString;
    
    public ColumnTokenStringSplitter(String token) {
        this.token = token.trim();
        split();
    }

    private void split() {
        setType();
        setNumStrings();
    }

    private void setType() {
        int typeStartIndex = token.indexOf('#') + 1;
        int typeEndIndex = token.indexOf('_');
        type = token.substring(typeStartIndex, typeEndIndex).trim();
    }

    private void setNumStrings() {
        String[] parts = token.split("_");
        if (parts != null && parts.length > 0) {
            switch (parts.length) {
                case 1:
                    firstNumString = null;
                    secondNumString = null;
                    break;
                case 2:
                    if (Utils.isParseable(parts[1].trim())) {
                        firstNumString = parts[1].trim();
                    }
                    secondNumString = null;
                    break;
                default:
                    if (Utils.isParseable(parts[1].trim())) {
                        firstNumString = parts[1].trim();
                    }
                    if (Utils.isParseable(parts[2].trim())) {
                        secondNumString = parts[2].trim();
                    }
                    break;
            }
        }
        else {
            firstNumString = null;
            secondNumString = null;
        }
                    
    }

    public String getType() {
        return type;
    }

    public String getFirstNumString() {
        return firstNumString;
    }

    public String getSecondNumString() {
        return secondNumString;
    }
    
}
