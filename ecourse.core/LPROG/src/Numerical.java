import java.util.List;

public class Numerical  implements Question{
    private String questionText;
    private int correctAnswer;
    private String unit;
    private List<Numerical> numericalList;

    private static final Numerical instance = new Numerical();

    public Numerical(String questionText, int correctAnswer, String unit) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.unit = unit;
    }

    public Numerical(){

    }

    public static Numerical getInstance(){
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

    public void addNumerical(Numerical numerical){
        numericalList.add(numerical);
    }

    public List<Numerical> getNumericalList(){
        return List.copyOf(numericalList);
    }
}
