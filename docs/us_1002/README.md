# US 1002 - Create Courses

As Manager, I want to create courses.

## 1. Context

The goal of this US is to generate a Course and store it within the database.

## 2. Requirements

* FRC01 - Create Course Different editions of a course are to be considered different courses (e.g., Intro-Math-Sem01,
  Intro-Math Sem02).
* FRC01 - Only managers are able to execute this functionality.
* Automatically generated identifier
* Unique Name
* Description has characters limit, defined in configuration file
* Optionally, has enrollment limits
* Created in "close" state

## 3. Analysis

## 4. Design

### 4.1. Realization

### 4.2. Class Diagram

![](CreateCourseUI_CD.svg)
*Class Diagram*
![](Course_CD.svg "Course Class Diagram")
*Course Class Diagram*

### 4.3. Applied Patterns

* Builder
* DDD

### 4.4. Tests

**Test 1-2:** *Verifies that it is not possible to create an instance of the EnrollmentLimits with null values*

    @Test(expected = IllegalArgumentException.class)
    public void ensureEnrollmentLimitsMinimumNonNegative() {
        EnrollLimit enrollLimit = new EnrollLimit(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEnrollmentLimitsMaximumNonNegative() {
        EnrollLimit enrollLimit = new EnrollLimit(1, -1);
    }

**Test 3:** *Verifies that it is not possible to create an instance of the EnrollmentLimits with maximum lower than minimum*


    @Test(expected = IllegalArgumentException.class)
    public void ensureEnrollmentLimitsMaximumHigherThanMinimum() {
        EnrollLimit enrollLimit = new EnrollLimit(1, -1);
    }

**Test 3:** *Verifies that it is not possible to create an instance of the Description that excesses character limit defined in config file*

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionLimit() {
        final Integer LIMIT = Application.settings().getCourseDescriptionCharacterLimit();
        Description description = new Description("a".repeat(Math.max(0, LIMIT + 1)));
    }

**Test 4:** *Verifies that it is not possible to create an instance of the Description null or empty*

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionNotNullOrEmpty() {
        final Integer LIMIT = Application.settings().getCourseDescriptionCharacterLimit();
        Description description = new Description("a".repeat(Math.max(0, LIMIT + 1)));
    }

**Test 5:** *Verifies that it is not possible to create an instance of the Name null or empty*

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameNotNullOrEmpty() {
        final Integer LIMIT = Application.settings().getCourseDescriptionCharacterLimit();
        Description description = new Description("a".repeat(Math.max(0, LIMIT + 1)));
    }

**Test 6:** *Verifies that 2 different courses with the same attributes are equal*

    @Test
    public void ensureCourseEqualsSameAttributes() throws Exception {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        assertEquals(course1, course2);
    }

**Test 7:** *Verifies that 2 different courses with a different Name are not equal*

    @Test
    public void ensureCourseNotEqualsDifferentName() throws Exception {
        Course course1 = new CourseBuilder().withName("Java-2").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        assertNotEquals(course1, course2);
    }

**Test 7:** *Verifies that 2 different courses with a different Description are not equal*

    @Test
    public void ensureCourseNotEqualsDifferentDescription() throws Exception {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 23").build();
        assertNotEquals(course1, course2);
    }

**Test 7:** *Verifies that 2 different courses with a different EnrollLimits are not equal*

    @Test
    public void ensureCourseNotEqualsDifferentLimits() throws Exception {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 140).build();
        assertNotEquals(course1, course2);
    }


## 5. Implementation

*In this section the team should present, if necessary, some evidencies that the implementation is according to the
design. It should also describe and explain other important artifacts necessary to fully understand the implementation
like, for instance, configuration files.*

*It is also a best practice to include a listing (with a brief summary) of the major commits regarding this
requirement.*

## 6. Integration/Demonstration

![DEMO](DEMO_interaction.png)
![DEMO](DEMO_db.png)

## 7. Observations

*This section should be used to include any content that does not fit any of the previous sections.*

*The team should present here, for instance, a critical prespective on the developed work including the analysis of
alternative solutioons or related works*

*The team should include in this section statements/references regarding third party works that were used in the
development this work.*