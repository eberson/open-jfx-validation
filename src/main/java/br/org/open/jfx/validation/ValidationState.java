/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.open.jfx.validation;

/**
 *
 * @author Éberson
 */
public enum ValidationState {
    
    VALIDATED("validated"),
    VALID("validation_valid"),
    WARNING("validation_warning"),
    ERROR("validation_error");
    
    private final String style;
    
    private ValidationState(String style){
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}
