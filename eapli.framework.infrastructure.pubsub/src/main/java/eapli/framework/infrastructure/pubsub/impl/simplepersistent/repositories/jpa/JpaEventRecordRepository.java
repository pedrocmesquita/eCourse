/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.jpa;

import org.springframework.stereotype.Component;

import eapli.framework.infrastructure.pubsub.impl.simplepersistent.model.EventRecord;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventRecordRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaBaseRepository;

/**
 * @author Paulo Gandra de Sousa 2022.05.09
 *
 */
@Component
public class JpaEventRecordRepository extends JpaBaseRepository<EventRecord, Long, Long>
		implements EventRecordRepository {

	public JpaEventRecordRepository() {
		super("pk");
	}

	@Override
	public Iterable<EventRecord> findNotConsumed(String instanceKey) {
		return match(
				"e NOT IN (SELECT e FROM EventRecord e, EventConsumption c WHERE c.event = e AND c.consumerName = :instance)",
				"instance", instanceKey);
	}
}
