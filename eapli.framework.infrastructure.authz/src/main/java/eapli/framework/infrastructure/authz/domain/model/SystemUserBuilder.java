/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.framework.infrastructure.authz.domain.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.security.crypto.password.PasswordEncoder;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.identities.impl.UUIDGenerator;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.representations.dto.GeneralDTO;
import eapli.framework.time.util.CurrentTimeCalendars;
import eapli.framework.validations.Invariants;
import eapli.framework.validations.Preconditions;
import eapli.framework.visitor.Visitable;
import eapli.framework.visitor.Visitor;

/**
 * An application user. Objects are constructed thru {@link SystemUserBuilder}
 * to enforce password policy and encoding.
 * <p>
 * This class follows a DDD approach where User is the root entity of the User
 * Aggregate and all of its properties are instances of value objects. This
 * approach may seem a little more complex than just having String or native
 * type attributes but provides for real semantic of the domain and follows the
 * Single Responsibility Pattern (SRP).
 *
 * @author Paulo Gandra Sousa
 */
@Entity
@Table(name = "T_SYSTEMUSER")
public class SystemUser implements AggregateRoot<Username>, DTOable<GeneralDTO>, Visitable<GeneralDTO>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    private Username username;

    private Password password;

    private Name name;

    @EmbeddedId
    private EmailAddress email;

    private Role role;

    @Temporal(TemporalType.DATE)
    private Calendar createdOn;

    private boolean active;

    @Temporal(TemporalType.DATE)
    private Calendar deactivatedOn;

    // the password reset token
    private String resetToken;

    private Acronym acronym;
    private BirthDate birthDate;
    private MecNumber mecNumber;
    private Nif nif;

    /**
     * @param username
     * @param password
     * @param name
     * @param email
     * @param role
     */
    /* package */ SystemUser(final Username username, final Password password, final Name name,
                             final EmailAddress email, final Role role, final Acronym acronym,
                             final BirthDate birthDate, final MecNumber mecNumber, final Nif nif) {
        this(username, password, name, email, role, CurrentTimeCalendars.now(), acronym, birthDate, mecNumber, nif);
    }

    /**
     * @param username
     * @param password
     * @param name
     * @param email
     * @param role
     * @param createdOn
     */
    /* package */ SystemUser(final Username username, final Password password, final Name name,
                             final EmailAddress email, final Role role, final Calendar createdOn, final Acronym acronym,
                             final BirthDate birthDate, final MecNumber mecNumber, final Nif nif) {
        init(username, password, name, email, role, createdOn, acronym, birthDate, mecNumber, nif);
    }

    private void init(final Username username, final Password password, final Name name, final EmailAddress email,
                      final Role role, final Calendar createdOn, final Acronym acronym,
                      final BirthDate birthDate, final MecNumber mecNumber, final Nif nif) {
        Preconditions.noneNull(role, username, password, name, email, createdOn);

        this.createdOn = createdOn;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.acronym = acronym;
        this.birthDate = birthDate;
        this.mecNumber = mecNumber;
        this.nif = nif;

        // accounts are already active upon creation
        active = true;

        // the user has not requested to reset the password of a new accounts
        invalidateResetToken();
    }

    protected SystemUser() {
        // for ORM
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof SystemUser)) {
            return false;
        }

        final SystemUser that = (SystemUser) other;
        if (this == that) {
            return true;
        }
        if (!username.equals(that.username) || !password.equals(that.password) || !name.equals(that.name)
                || !email.equals(that.email) || role.equals(that.role)) {
            return false;
        }
        return resetToken == null ? that.resetToken == null : resetToken.equals(that.resetToken);
    }

    @Override
    public Username identity() {
        return username;
    }

    /**
     * @return the user's email address
     */
    public EmailAddress email() {
        return email;
    }

    /**
     * Add role to user.
     *
     * @param role
     *            Role to assign to SystemUser.
     */
    public void assignToRole(final Role role) {
        Preconditions.nonNull(role);
        this.role = role;
    }

    @Override
    public GeneralDTO toDTO() {
        final GeneralDTO ret = new GeneralDTO("user");
        ret.put("username", username.toString());
        // the password should not leave this object...
        // so we never have something like ret.put("password", password.toString())
        ret.put("name", name.toString());
        ret.put("email", email.toString());
        ret.put("roles", role.toString());

        return ret;
    }

    /**
     * Checks if this user's password matches a password. We don't want the password
     * to move outside of the object, so instead of returning it we pass the
     * responsibility of performing the validation to inside the object.
     *
     * @param rawPassword
     * @param encoder
     *
     * @return true if the password matches
     */
    public boolean passwordMatches(final String rawPassword, final PasswordEncoder encoder) {
        // FIXME since we are receiving the encoder and passing to that encoder the password, we are
        // in fact exposing it!!!!
        return encoder.matches(rawPassword, password.value());
    }

    /**
     * For supporting Spring Security UserDetails.
     *
     * @return the password
     */
    /* package */String encodedPassword() {
        return password.value();
    }

    @Override
    public void accept(final Visitor<GeneralDTO> visitor) {
        visitor.visit(toDTO());
    }

    /**
     * @return the username
     */
    public Username username() {
        return username;
    }

    /**
     * @return the name
     */
    public Name name() {
        return name;
    }

    /**
     * @return <code>true> if the user is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param deactivatedOn
     */
    public void deactivate(final Calendar deactivatedOn) {
        // cannot deactivate a user before it was registered in the system
        if (deactivatedOn == null || deactivatedOn.before(createdOn)) {
            throw new IllegalArgumentException();
        }
        // cannot deactivate an inactive user
        if (!active) {
            // we could simply do nothing instead of taking a harsh approach of
            // throwing an exception
            throw new IllegalStateException("Cannot deactivate an inactive user");
        }
        active = false;
        this.deactivatedOn = deactivatedOn;
        invalidateResetToken();
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean equals(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    /**
     * Sets a new password for this user.
     *
     * @param newPassword
     */
    public void changePassword(final Password newPassword) {
        password = newPassword;
        invalidateResetToken();
    }

    private void invalidateResetToken() {
        resetToken = null;
    }

    /**
     * initiates the process to reset a password by generating a validation token.
     *
     * @return the validation token to use to complete the password reset
     */
    public String resetPassword() {
        resetToken = new UUIDGenerator().newId().toString();
        return resetToken;
    }

    /**
     * resets the password of the user.
     *
     * @param token
     * @param newPass
     *
     * @return <code>true</code> if the token was valid and the password was reset.
     *         <code>false</code> otherwise
     */
    public boolean confirmResetPassword(final String token, final Password newPass) {
        Invariants.nonNull(resetToken);
        Preconditions.nonEmpty(token);
        Preconditions.nonNull(newPass);

        invalidateResetToken();
        if (token.equals(resetToken)) {
            password = newPass;
            return true;
        }
        return false;
    }

    /**
     * Checks if this user has any of a set of roles.
     *
     * @param roles
     *
     * @return true if the user has at least one of the roles
     */
    public boolean hasAny(final Role... roles) {
        return Arrays.asList(roles)
                .contains(this.role);
    }

    /**
     * Returns the date this user account was created.
     *
     * @return the date this user account was created
     */
    public Calendar createdOn() {
        // returning a copy as the Calendar class is mutable and we don't want
        // to allow external access to chnage this value without us knowing it
        return (Calendar) createdOn.clone();
    }
}
