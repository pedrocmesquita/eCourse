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

//todo

## 4. Design

### 4.1. Realization

![](CreateExam_SD.svg)
*Sequence Diagram*

### 4.2. Class Diagram

![](CreateExam_CD.svg)
*Class Diagram*

### 4.3. Applied Patterns

### 4.4. Tests

**Test 1:** *Verifies that it is not possible to create an instance of Setting with null values*

    @Test(expected = IllegalArgumentException.class)
    public void ensureSettingNotNull() {
        final Setting setting = new Setting(null, null);
    }

**Test 2:** *Verifies that it is not possible to create an instance of Date with null values*

    @Test(expected = IllegalArgumentException.class)
    public void ensureDateNotNull() {
        final Date date = new Date(null, null);
    }

**Test 3:** *Verifies that it is not possible to create an instance of Date with openDate after closeDate*

    @Test(expected = IllegalArgumentException.class)
    public void ensureOpenDateAfterCloseDate() {
        final Date date = new Date(dayAfterTomorrow, tomorrow);
    }

**Test 4:** *Verifies that it is not possible to create an instance of Date with dates in the past*

    @Test(expected = IllegalArgumentException.class)
    public void ensureDatesAreInFuture() {
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        Calendar dayBeforeYesterday = Calendar.getInstance();
        dayBeforeYesterday.add(Calendar.DATE, -2);
        final Date date = new Date(dayBeforeYesterday, yesterday);
    }

**Test 5:** *Verifies that it is not possible to create an instance of Exam with null sections*


    @Test(expected = IllegalArgumentException.class)
    public void ensureSectionNotNull() {
        final Exam exam = new Exam(TITLE, DESCRIPTION, SETTING, DATE, null);
    }

**Test 6:** *Verifies that it is not possible to create an instance of Exam with 0 sections*

    @Test(expected = IllegalArgumentException.class)
    public void ensureMinimumOneSection() {
        final Exam exam = new Exam(TITLE, DESCRIPTION, SETTING, DATE, new ArrayList<>());
    }

**Test 7:** *Verifies that it is not possible to create an instance of Section with null description*

    @Test(expected = IllegalArgumentException.class)
    public void ensureSectionDescriptionNotNull() {
        final Section section = new Section(null, QUESTIONS);
    }

**Test 8:** *Verifies that it is not possible to create an instance of Section with null description*

    @Test(expected = IllegalArgumentException.class)
    public void ensureSectionQuestionNotNull() {
        final Section section = new Section(DESCRIPTION, null);
    }

**Test 9:** *Verifies that it is not possible to create an instance of Section with 0 questions*

    @Test(expected = IllegalArgumentException.class)
    public void ensureSectionMinimumOneQuestion() {
        final Section section = new Section(DESCRIPTION, new ArrayList<>());
    }

**Test 10:** *Verifies that it is not possible to create an instance of Question with null question*

    @Test(expected = IllegalArgumentException.class)
    public void ensureQuestionNotNull() {
        final Question question = new Question(null);
    }

**Test 11:** *Verifies that it is not possible to create an instance of Question with empty question*

    @Test(expected = IllegalArgumentException.class)
    public void ensureQuestionNotEmpty() {
        final Question question = new Question("");
    }

## 5. Implementation

//todo

## 6. Integration/Demonstration

![](DEMO.png)

*Exam Creation*

![](EXAM_DB.png)

*Exam*

![](COURSES_DB.png)

*Course*

![](COURSE_EXAM_DB.png)

*Exams of a Course*

![](EXAM_SECTION_DB.png)

*Sections of a Exam*

![](SECTION_DB.png)

*Sections*

![](SECTION_QUESTION_DB.png)

*Questions of a section*

![](QUESTION_DB.png)

*Questions*

## 7. Observations

No observations