import java.util.List;

public class SelectMissingWords implements Question {
    private String questionText;
    private List<String> correctAnswer;
    private List<SelectMissingWords> selectMissingWordsList;

    private static final SelectMissingWords instance = new SelectMissingWords();

    public SelectMissingWords(String questionText, List<String> correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public SelectMissingWords() {

    }

    public static SelectMissingWords getInstance() {
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

    public void addSelectMissingWords(SelectMissingWords selectMissingWords) {
        selectMissingWordsList.add(selectMissingWords);
    }

    public List<SelectMissingWords> getSelectMissingWordsList(){
        return List.copyOf(selectMissingWordsList);
    }
}
