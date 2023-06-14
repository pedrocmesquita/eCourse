import java.util.List;

public interface Question {
    String getQuestionText();
    List<String> getAnswerOptions();
    boolean isCorrectAnswer(List<String> selectedOptions);


}
