# US 2001 - Create/Update an Exam

*As Teacher, I want to create/update an exam*

## 1. Context

*This US aims to create/update an exam of a course. Originally scheduled for implementation during sprint B, the plan 
was not executed and consequently, it was rescheduled for sprint C.*

## 2. Requirements

* FRE01 - A Teacher creates a new exam. This includes the specification
of the exam (i.e., its structure, in accordance with a grammar for exams that is used to
validate the specification of the exam)
* is related to a specific course
* it has a unique title, header, open and close date and sections.
* header must include global settings:
  * type of feedback (none, on-submission, after-closing)
  * type of grade (none, on-submission, after-closing)
* header, optionally, has a textual description
* open date is the time when students can start to take the exam
* close date is the deadline for students to submit the exam
* must have at least one section
* each section must have at least one question
* section, optionally, has a textual description

## 3. Analysis

...

## 4. Design

*In this sections, the team should present the solution design that was adopted to solve the requirement. This should
include, at least, a diagram of the realization of the functionality (e.g., sequence diagram), a class diagram (
presenting the classes that support the functionality), the identification and rational behind the applied design
patterns and the specification of the main tests used to validade the functionality.*

### 4.1. Realization

### 4.2. Class Diagram

![a class diagram](class-diagram-01.svg "A Class Diagram")

### 4.3. Applied Patterns

### 4.4. Tests

**Test 1:** *Verifies that it is not possible to create an instance of the Example class with null values.*

```
@Test(expected = IllegalArgumentException.class)
public void ensureNullIsNotAllowed() {
	Example instance = new Example(null, null);
}
````

## 5. Implementation

*In this section the team should present, if necessary, some evidencies that the implementation is according to the
design. It should also describe and explain other important artifacts necessary to fully understand the implementation
like, for instance, configuration files.*

*It is also a best practice to include a listing (with a brief summary) of the major commits regarding this
requirement.*

## 6. Integration/Demonstration

*In this section the team should describe the efforts realized in order to integrate this functionality with the other
parts/components of the system*

*It is also important to explain any scripts or instructions required to execute an demonstrate this functionality*

## 7. Observations

*This section should be used to include any content that does not fit any of the previous sections.*

*The team should present here, for instance, a critical prespective on the developed work including the analysis of
alternative solutioons or related works*

*The team should include in this section statements/references regarding third party works that were used in the
development this work.*