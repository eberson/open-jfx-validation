/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.open.jfx.validation;

/**
 *
 * @author Éberson
 */
public interface TypeValidator<T> {

    /**
     *
     * Realiza a validação sobre um dado elemento.
     *
     * @param typeToValidate Elemento a ser validado
     * @return ${@link ValidationState#VALID} caso o elemento seja considerado
     * validado;<br />
     * ${@link ValidationState#WARNING} caso o elemento seja considerado como
     * alerta (não está necessariamente válido, mas não está inválido);<br />
     * ${@link ValidationState#ERROR} caso o elemento seja considerado inválido.
     */
    ValidationState validate(T typeToValidate);
}
