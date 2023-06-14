import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bootstrap implements Runnable{

    public void run() {
        addMatchingQuestion();
        addMultipleChoice();
        addShortAnswer();
        addNumerical();
        addTrueFalse();
        addSelectMissingWords();
    }

    public void addMatchingQuestion(){
        Matching matching = Matching.getInstance();
        List<String> countries = Arrays.asList("Canada", "Italy", "Japan");
        List<String> capitals = Arrays.asList("Ottawa", "Rome", "Tokyo");

        Matching matchingQuestion = new Matching("Match the Capital with the Country", countries, capitals);

        matching.addMatchingQuestion(matchingQuestion);
    }

    public void addMultipleChoice(){
        MultipleChoice multipleChoice = MultipleChoice.getInstance();
        List<String> answerOptions = Arrays.asList("A","B","C","D");
        List<String> capitals = Arrays.asList("Ottawa", "Rome", "Tokyo","Lisbon");

        MultipleChoice multipleChoiceQuestion = new MultipleChoice("Select the Capital of Portugal",answerOptions,capitals,4);

        multipleChoice.addMultipleChoice(multipleChoiceQuestion);
    }

    public void addShortAnswer(){
        ShortAnswer shortAnswer = ShortAnswer.getInstance();
        String answer = "Dynamic";

        ShortAnswer shortAnswerQuestion = new ShortAnswer("Moodle stands for 'Modular object oriented ___________________ environment",answer);

        shortAnswer.addShortAnswer(shortAnswerQuestion);
    }

    public void addNumerical(){
        Numerical numerical = Numerical.getInstance();
        int answer = 2000;
        String unit = "microamp";

        Numerical numericalQuestion = new Numerical("What current flow through a resistor of 500 ohms with an applied voltage of 1 V", answer, unit);

        numerical.addNumerical(numericalQuestion);
    }

    public void addSelectMissingWords(){
        SelectMissingWords selectMissingWords = SelectMissingWords.getInstance();
        List<String> answer = Arrays.asList("Country","Europe","Lisbon");

        SelectMissingWords selectMissingWordsQuestion = new SelectMissingWords("Portugal is a ______, in ________ and the capital is ______.", answer);

        selectMissingWords.addSelectMissingWords(selectMissingWordsQuestion);
    }
    public void addTrueFalse(){
        TrueFalse trueFalse = TrueFalse.getInstance();
        String answer = "True";

        TrueFalse trueFalseQuestion = new TrueFalse("Portugal is a country", answer);

        trueFalse.addTrueFalse(trueFalseQuestion);
    }

}
