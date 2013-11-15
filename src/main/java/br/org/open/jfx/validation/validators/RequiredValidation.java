/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.open.jfx.validation.validators;

import br.org.open.jfx.validation.TypeValidator;
import br.org.open.jfx.validation.ValidationState;
import br.org.open.jfx.validation.Validator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;

/**
 *
 * Validação a ser utilizado em campos que tem seu conteúdo considerados
 * obrigatórios.
 *
 * @author Éberson
 */
public class RequiredValidation {
    
    public static Validator<String> build(TextInputControl field, final Label message){
        Validator<String> validator = new Validator<>(field.textProperty(), new TypeValidator<String>() {
            @Override
            public ValidationState validate(String typeToValidate) {
                return (typeToValidate == null || typeToValidate.isEmpty()) ?
                        ValidationState.ERROR : ValidationState.VALID;
            }
        }, ValidationState.VALID);
        
        validator.addStyleTarget(field);
        
        if(message == null){
            return validator;
        }
        
        validator.addStyleTargets(message);
        validator.stateProperty().addListener(new ChangeListener<ValidationState>() {
            @Override
            public void changed(ObservableValue<? extends ValidationState> observable, ValidationState oldValue, ValidationState newValue) {
                switch(newValue){
                    case ERROR: message.setText("Campo Obrigatório");
                    case VALID: message.setText("");
                }
            }
        });
        
        return validator;
    }
    
}
