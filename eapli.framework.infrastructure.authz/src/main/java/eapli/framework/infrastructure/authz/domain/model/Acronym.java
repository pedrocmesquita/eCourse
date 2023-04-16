package eapli.framework.infrastructure.authz.domain.model;

import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Acronym {

    @Column(name = "acronym")
    private final String value;

    private Acronym(String value) {
        this.value = value;
        Preconditions.ensure(value.length() == 3, "Acronym must be 3 characters long");
    }

    protected Acronym(){
        this.value = null;
    }

    public static Acronym valueOf(String value){
        return new Acronym(value);
    }

    String value(){
        return this.value;
    }

}
