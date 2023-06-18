package eapli.ecourse.app.teacher.console.presentation.exam;


import antlr.Grammar;
import eapli.ecourse.app.common.console.presentation.course.SelectCourseWidget;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.application.CreateExamController;
import eapli.ecourse.exammanagement.domain.SettingType;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Arrays;
import java.util.Calendar;
import java.util.InputMismatchException;

public class CreateExamUI extends AbstractUI {

    private static final CreateExamController controller = new CreateExamController();
    private final SelectCourseWidget courseWidget = new SelectCourseWidget(controller.allCoursesTeacherIsAssigned(controller.getUserAcronym()));
    private static int SECTION_COUNTER = 1;
    private static int QUESTION_COUNTER;

    @Override
    protected boolean doShow() {
        System.out.println("Select a Course to create an exam");
        final Course selectedCourse = courseWidget.selectCourse();
        if (selectedCourse == null)
            return false;

        String title;
        do {
            title = Console.readLine("Title");
            if (!verifyTitle(title)) {
                System.out.println("Invalid title");
                return false;
            }
        } while (false);


        String description;
        do {
            description = Console.readLine("Description");
            if (!verifydescription(description)) {
                System.out.println("Invalid Description");
                return false;
            }
        } while (false);


        final Calendar openDate = Console.readCalendar("Open date (dd-MM-yyyy hh:mm:ss)", "dd-MM-yyyy hh:mm:ss");
        final Calendar closeDate = Console.readCalendar("Close date (dd-MM-yyyy hh:mm:ss)", "dd-MM-yyyy hh:mm:ss");

        final Iterable<SettingType> iterableSetting = Arrays.asList(SettingType.values());
        final SelectWidget<SettingType> selectorSetting = new SelectWidget<>("", iterableSetting);

        System.out.println("\nSelect feedback setting");
        selectorSetting.show();
        final SettingType feedbackSetting = selectorSetting.selectedElement();
        System.out.println("\nSelect grade setting");
        selectorSetting.show();
        final SettingType gradeSetting = selectorSetting.selectedElement();

        controller.addOther(title, description, feedbackSetting, gradeSetting, openDate, closeDate);

        boolean addSection;
        boolean addQuestion;

        //add section loop
        do {
            QUESTION_COUNTER = 1;
            newSection();
            String descriptionSection;
            do {
                descriptionSection = Console.readLine("Description");
                if (!verifydescription(descriptionSection)) {
                    System.out.println("Invalid Description");
                    return false;
                }
            } while (false);
            //add question loop
            do {
                addQuestion();
                addQuestion = Console.readBoolean("\nAdd another question? (Y/N)");
            } while (addQuestion);
            controller.addSection(descriptionSection);
            addSection = Console.readBoolean("\nAdd another section? (Y/N)");
        } while (addSection);
        try {
            controller.createExam(selectedCourse);
            System.out.println("Exam created with success");
        } catch (final IntegrityViolationException e) {
            System.out.println("You tried to enter a exam with a title which already exists in the database.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return true;
    }

    private void newSection() {
        System.out.println("\nCreate Section " + SECTION_COUNTER);
        SECTION_COUNTER++;
        controller.newSection();
    }

    private void addQuestion() {
        System.out.println("\nWhat type of question you want to add?");
        System.out.println("\nQuestion Type:");
        getQuestionType();
        System.out.println("\nCreate Question " + QUESTION_COUNTER);
        QUESTION_COUNTER++;
        String question = Console.readLine("Question");
        controller.addQuestion(question);
    }

    private void getQuestionType() {
        int option=0;
        do {
            System.out.println("1. Matching\n");
            System.out.println("2. Multiple Choice\n");
            System.out.println("3. Short Answer\n");
            System.out.println("4. Numerical\n");
            System.out.println("5. Select Missing Words\n");
            System.out.println("6. True or False\n");

            try {
                option = Integer.parseInt(Console.readLine("Type your option:\n"));
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option (an integer).");
                option = -1;
            }

            switch (option) {
                case 1:
                    boolean addQuestion1;
                    do {
                        String addPairQuestion;
                        do {
                            addPairQuestion = Console.readLine("\nType the question");
                        } while (!verifyMatchingPairQuestion(addPairQuestion));

                        String addPairAnswer;
                        do {
                            addPairAnswer = Console.readLine("\nType the answers");
                        } while (verifyMatchingPairAnswer(addPairAnswer));

                        addQuestion1 = Console.readBoolean("\nAdd another Question? (Y/N)");

                    } while (addQuestion1);
                    break;
                case 2:
                    boolean addQuestion2;
                    do {
                        String addOption;
                        do {
                            addOption = Console.readLine("\nType the option");
                        } while (!verifyMultipleOption(addOption));

                        int addWeight;
                        do {
                            addWeight = Integer.parseInt(Console.readLine("\nType the weight"));
                        } while (!verifyMultipleWeight(addWeight));

                        addQuestion2 = Console.readBoolean("\nAdd another Question? (Y/N)");

                    } while (addQuestion2);
                    break;
                case 3:
                    boolean addQuestion3;
                    do {
                        String addShortAnswer;
                        do {
                            addShortAnswer = Console.readLine("\nType the Answer");
                        } while (!verifyShortAnswer(addShortAnswer));

                        int addGrade;
                        do {
                            addGrade = Integer.parseInt(Console.readLine("\nType the Grade"));
                        } while (!verifyGrade(addGrade));

                        addQuestion3 = Console.readBoolean("\nAdd another question? (Y/N)");

                    } while (addQuestion3);
                    break;
                case 4:
                    boolean addQuestion4;
                    do {
                        Float addNumericalAnswer;
                        do {
                            addNumericalAnswer = Float.parseFloat(Console.readLine("\nType the Answer"));
                        } while (!verifyNumericalAnswer(addNumericalAnswer));

                        Float addAcceptedError;
                        do {
                            addAcceptedError = Float.parseFloat(Console.readLine("\nType the Accepted Error"));
                        } while (!verifyAcceptedError(addAcceptedError));

                        addQuestion4 = Console.readBoolean("\nAdd another question? (Y/N)");

                    } while (addQuestion4);
                    break;
                case 5:
                    boolean addQuestion5;
                    do {
                        String addItemGroup;
                        do {
                            addItemGroup = Console.readLine("\nType the Group");
                        } while (!verifyItemGroup(addItemGroup));

                        String addItem;
                        do {
                            addItem = Console.readLine("\nType the Item");
                        } while (!verifyItem(addItem));

                        addQuestion5 = Console.readBoolean("\nAdd another question? (Y/N)");

                    } while (addQuestion5);
                    break;
                case 6:

                    break;
            }
        } while (option == -1);
    }

    private Boolean verifyTitle(String title){
        CharStream charStream = CharStreams.fromString("Title:" + title);
        GrammarLexer lexer = new GrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.TitleContext titleContext = parser.title();

        return titleContext != null && titleContext.STRING() != null;
    }

    private Boolean verifydescription(String description){
        CharStream charStream = CharStreams.fromString("Description:" + description);
        GrammarLexer lexer = new GrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.DescriptionContext descriptionContext = parser.description();

        return descriptionContext != null && descriptionContext.STRING() != null;
    }

    private Boolean verifyMatchingPairQuestion(String matchingPair){
        CharStream charStream = CharStreams.fromString("Question:" + matchingPair);
        GrammarLexer lexer = new GrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.MatchingPairContext matchingPairContext = parser.matchingPair();

        return matchingPairContext != null && matchingPairContext.STRING() != null;
    }

    private Boolean verifyMatchingPairAnswer(String answer){
        CharStream charStream = CharStreams.fromString("Answer:" + answer);
        GrammarLexer lexer = new GrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.AnswerContext answerContext = parser.answer();

        return answerContext != null && answerContext.STRING() != null;
    }

    private Boolean verifyMultipleOption(String option){
        CharStream charStream = CharStreams.fromString("Option:" + option);
        GrammarLexer lexer = new GrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.OptionContext optionContext = parser.option();

        return optionContext != null && optionContext.STRING() != null;
    }

    private Boolean verifyMultipleWeight(int weight){
        CharStream charStream = CharStreams.fromString("Weight:" + weight);
        GrammarLexer lexer = new GrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.WeightContext weightContext = parser.weight();

        return weightContext != null && weightContext.INT() != null;
    }

    private Boolean verifyShortAnswer(String shortAnswer){
        CharStream charStream = CharStreams.fromString("Answer:" + shortAnswer);
        GrammarLexer lexer = new GrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.ShortAnswerContext shortAnswerContext = parser.shortAnswer();

        return shortAnswerContext != null && shortAnswerContext.STRING() != null;
    }

    private Boolean verifyGrade(int grade){
        CharStream charStream = CharStreams.fromString("Grade:" + grade);
        GrammarLexer lexer = new GrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.GradeContext gradeContext = parser.grade();

        return gradeContext != null && gradeContext.INT() != null;
    }

    private Boolean verifyNumericalAnswer(Float numericalAnswer){
        CharStream charStream = CharStreams.fromString("Answer:" + numericalAnswer);
        GrammarLexer lexer = new GrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.NumericalAnswerContext numericalAnswerContext = parser.numericalAnswer();

        return numericalAnswerContext != null && numericalAnswerContext.FLOAT() != null;
    }

    private Boolean verifyAcceptedError(Float acceptedError){
        CharStream charStream = CharStreams.fromString("AcceptedError:" + acceptedError);
        GrammarLexer lexer = new GrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.AcceptedErrorContext acceptedErrorContext = parser.acceptedError();

        return acceptedErrorContext != null && acceptedErrorContext.FLOAT() != null;
    }

    private Boolean verifyItemGroup(String itemGroup){
        CharStream charStream = CharStreams.fromString("Group:" + itemGroup);
        GrammarLexer lexer = new GrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.ItemGroupContext itemGroupContext = parser.itemGroup();

        return itemGroupContext != null && itemGroupContext.STRING() != null;
    }

    private Boolean verifyItem(String item){
        CharStream charStream = CharStreams.fromString("Item:" + item);
        GrammarLexer lexer = new GrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.ItemContext itemContext = parser.item();

        return itemContext != null && itemContext.STRING() != null;
    }




    @Override
    public String headline() {
        return "Create Exam";
    }
}
