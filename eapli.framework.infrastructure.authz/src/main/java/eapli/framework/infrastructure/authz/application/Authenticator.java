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
package eapli.framework.infrastructure.authz.application;

import java.util.Optional;

import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 *
 * @author Paulo Gandra de Sousa 2022.11.24
 *
 */
@FunctionalInterface
public interface Authenticator {

	/**
	 * Allows a user to perform login. Checks if a user can be authenticated with
	 * the username/password pair and if so creates a user session for it.
	 *
	 * @param username
	 * @param rawPassword
	 * @param requiredRoles
	 * @return the authenticated user session or an empty optional otherwise
	 * @throws IllegalArgumentException if the username or password cannot be
	 *                                  constructed from the passed string arguments
	 */
	Optional<UserSession> authenticate(String username, String rawPassword, Role... requiredRoles);
}