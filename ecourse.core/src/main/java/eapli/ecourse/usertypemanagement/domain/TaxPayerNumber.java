package eapli.ecourse.usertypemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class TaxPayerNumber implements ValueObject {

    @Column(unique=true)
    private String taxPayerNumber;

    protected TaxPayerNumber() {
        //ORM only
    }

    /**
     * Constructor
     *
     * @param taxPayerNumber
     */
    public TaxPayerNumber(String taxPayerNumber) {
        setTaxPayerNumber(taxPayerNumber);
    }

    public void setTaxPayerNumber(String taxPayerNumber) {
        if (StringPredicates.isNullOrEmpty(taxPayerNumber)) {
            throw new IllegalArgumentException("Tax Payer Number cannot be null or empty");
        }
        if (taxPayerNumber.length() != 9)
            throw new IllegalArgumentException("Tax Payer Number must be 9 digits");
        this.taxPayerNumber = taxPayerNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxPayerNumber that = (TaxPayerNumber) o;
        return Objects.equals(taxPayerNumber, that.taxPayerNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxPayerNumber);
    }

    @Override
    public String toString() {
        return this.taxPayerNumber;
    }
}
