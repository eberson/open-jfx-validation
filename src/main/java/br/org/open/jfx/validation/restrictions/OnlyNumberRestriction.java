/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.open.jfx.validation.restrictions;

import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;

/**
 *
 * Constroi uma regra que permite que apenas números sejam digitados em um
 * campo de texto do tipo ${@link TextInputControl}
 * 
 * @author Éberson
 */
public class OnlyNumberRestriction {
    
    public static void build(TextInputControl input){
        input.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                char[] ar = t.getCharacter().toCharArray();
                //pega o último caracter digitado
                char ch = ar[ar.length - 1]; 
                
                //caso o caracter informado não esteja entre 0 e 9, ignora
                if(!(ch >= '0' && ch <= '9')){
                    t.consume();
                }
            }
        });
    }
    
}
