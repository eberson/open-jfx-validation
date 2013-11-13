/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.open.jfx.validation.validators;

import br.org.open.jfx.validation.TypeValidator;
import br.org.open.jfx.validation.ValidationState;
import static br.org.open.jfx.validation.ValidationState.ERROR;
import static br.org.open.jfx.validation.ValidationState.VALID;
import br.org.open.jfx.validation.Validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;

/**
 *
 * Verifica se o conteúdo informado corresponde a um formato de e-mail válido.
 * 
 * @author Éberson
 */
public class EmailValidation {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static Validator<String> build(TextInputControl input, final Label message){
        Validator<String> validator = new Validator<>(input.textProperty(), new TypeValidator<String>() {
            @Override
            public ValidationState validate(String typeToValidate) {
                Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                Matcher matcher = pattern.matcher(typeToValidate);
                
                if(matcher.matches()){
                    return ValidationState.VALID;
                }
                
                return ValidationState.ERROR;
            }
        }, ValidationState.VALID);
        
        validator.addStyleTarget(input);
        
        if(message == null){
            return validator;
        }
        
        validator.addStyleTargets(message);
        validator.stateProperty().addListener(new ChangeListener<ValidationState>() {
            @Override
            public void changed(ObservableValue<? extends ValidationState> observable, ValidationState oldValue, ValidationState newValue) {
                switch(newValue){
                    case ERROR: message.setText("E-mail inválido!");
                    case VALID: message.setText("");
                }
            }
        });
        
        return validator;
    }
    
}
