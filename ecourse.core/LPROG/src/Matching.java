import java.util.List;

public class Matching implements Question {
    private String questionText;
    private List<String> answerOptions;
    private List<String> correctAnswer;
    private List<Matching> matchingList;

    private static final Matching instance = new Matching();

    public Matching(String questionText, List<String> answerOptions, List<String> correctAnswer) {
        this.questionText = questionText;
        this.answerOptions = answerOptions;
        this.correctAnswer = correctAnswer;
    }

    public Matching(){

    }

    public static Matching getInstance(){
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

    @Override
    public boolean isCorrectAnswer(List<String> selectedOptions) {
        // Compare the selected options with the correct answer
        return selectedOptions.equals(correctAnswer);
    }

    public void addMatchingQuestion(Matching matchingQuestion){
        matchingList.add(matchingQuestion);
    }

    public List<Matching> getMatchingList(){
        return List.copyOf(matchingList);
    }
}
