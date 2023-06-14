import java.util.List;

public class MultipleChoice implements Question{
    private String questionText;
    private List<String> answerOptions;
    private List<String> answerOptionsString;
    private int correctAnswer;
    private List<MultipleChoice> multipleChoiceList;

    private static final MultipleChoice instance = new MultipleChoice();

    public MultipleChoice(String questionText, List<String> answerOptions, List<String> answerOptionsString, int correctAnswer) {
        this.questionText = questionText;
        this.answerOptions = answerOptions;
        this.answerOptionsString = answerOptionsString;
        this.correctAnswer = correctAnswer;
    }

    public MultipleChoice(){

    }

    public static MultipleChoice getInstance(){
        return instance;
    }

    @Override
    public String getQuestionText() {
        return questionText;
    }

    @Override
    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public List<String> getAnswerOptionsString() {
        return answerOptionsString;
    }

    @Override
    public boolean isCorrectAnswer(List<String> selectedOptions) {
        // Compare the selected options with the correct answer
        return selectedOptions.equals(correctAnswer);
    }

    public void addMultipleChoice(MultipleChoice multipleChoice){
        multipleChoiceList.add(multipleChoice);
    }

    public List<MultipleChoice> getMultipleChoiceList(){
        return List.copyOf(multipleChoiceList);
    }
}
