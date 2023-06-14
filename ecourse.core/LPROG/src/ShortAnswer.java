import java.util.List;

public class ShortAnswer  implements Question{
    private String questionText;
    private String correctAnswer;
    private List<ShortAnswer> shortAnswerList;

    private static final ShortAnswer instance = new ShortAnswer();

    public ShortAnswer(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public ShortAnswer(){

    }

    public static ShortAnswer getInstance(){
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

    public void addShortAnswer(ShortAnswer shortAnswer){
        shortAnswerList.add(shortAnswer);
    }

    public List<ShortAnswer> getShortAnswer(){
        return List.copyOf(shortAnswerList);
    }
}
