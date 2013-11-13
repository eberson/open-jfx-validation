/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.open.jfx.validation;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

/**
 * Classe para fornecer uma resposta visual sobre validação de estados em {@link ObservableValue}s.
 * 
 * @author Éberson
 * 
 * @param <T> Tipo que será validado
 */
public class Validator<T> {
    
    private final List<Node> targets = new ArrayList<>();
    private ObjectProperty<ValidationState> state = new SimpleObjectProperty<>(ValidationState.VALID);
    
     /**
     * Inicializa este {@link Validator} com o estado que pode ser diferente
     * do resultado da validação atual.
     *
     * @param property elemento a ser observado na validação
     * @param validator contém a regra da validação em questão
     * @param initState estado inicial da validação, independente do resultado
     * da validação do elemento propriamente dita.
     */
    public Validator(final ObservableValue<? extends T> property, final TypeValidator<? super T> validator, ValidationState initState) {
        Preconditions.checkNotNull(initState, "O estado não pode ser nulo!");
        state.set(initState);
 
        property.addListener(new ChangeListener<T>() {
            @Override
            public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
                setState(validator.validate(newValue));
            }
        });
    }
    
    /**
     * Inicializa o {@link Validator} com um estado dependenndo do resultado
     * da validação atual.
     *
     * @param property elemento a ser observado na validação
     * @param validator contém a regra da validação em questão
     */
    public Validator(final ObservableValue<? extends T> property, final TypeValidator<? super T> validator) {
        this(property, validator, validator.validate(property.getValue()));
    }

    private void setState(ValidationState newState) {
        if (state.get() == newState) {
            return;
        }

        Preconditions.checkNotNull(newState, "O estado não pode ser nulo!");

        for (Node node : targets) {
            node.getStyleClass().remove(state.get().getStyle());
            node.getStyleClass().add(newState.getStyle());
        }

        this.state.set(newState);
    }
    
    /**
     * Adiciona o novo {@link Node} que deve receber estilos dependendo do
     * resultado da validação deste validador.
     *
     * @param node elemento que recebe o estilo baseado no resultado da validação
     */
    public void addStyleTarget(Node node) {
        targets.add(node);
        node.getStyleClass().add(state.get().getStyle());
        node.getStyleClass().add(ValidationState.VALIDATED.getStyle());
    }

    /**
     * Adiciona novos {@link Node}s que devem receber estilo dependendo do
     * resultado da validação deste validador.
     *
     * @param nodes elementos que recebem o estilo baseado no resultado 
     * da validação
     */
    public void addStyleTargets(Node... nodes) {
        for (Node node : nodes) {
            addStyleTarget(node);
        }
    }

    public ReadOnlyObjectProperty<ValidationState> stateProperty() {
        return state;
    }
}
