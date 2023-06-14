import java.util.List;

public class TrueFalse  implements Question{
    private String questionText;
    private String correctAnswer;
    private String unit;
    private List<TrueFalse> trueFalseList;

    private static final TrueFalse instance = new TrueFalse();

    public TrueFalse(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public TrueFalse(){

    }

    public static TrueFalse getInstance(){
        return instance;
    }

    @Override
    public String getQuestionText() {
        return questionText;
    }

    @Override
    public List<String> getAnswerOptions() {
        return null;
    }

    @Override
    public boolean isCorrectAnswer(List<String> selectedOptions) {
        // Compare the selected options with the correct answer
        return selectedOptions.equals(correctAnswer);
    }

    public void addTrueFalse(TrueFalse trueFalse){
        trueFalseList.add(trueFalse);
    }

    public List<TrueFalse> getTrueFalseList(){
        return List.copyOf(trueFalseList);
    }
}
