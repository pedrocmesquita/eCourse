import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.ArrayList;
import java.util.List;

public class CustomGrammarListener extends GrammarBaseListener {
    private String title;
    private String feedback;
    private String grade;
    private String section;
    //private String matchingQuestionName;
    //private String matchingPairQuestion;
    private String multipleQuestion;
    private String option;
    //private String weight;
    private String description;
    //private String questionName;
    //private List<String> matchingPairs;
    private List<String> weights;
    private List<String> options;
    private List<String> multipleQuestions;
    //private List<String> matchingQuestions;
    private List<String> descriptions;
    private List<String> sections;
    private List<String> questions;

    public CustomGrammarListener() {
        sections = new ArrayList<>();
        questions = new ArrayList<>();
        descriptions = new ArrayList<>();
        options = new ArrayList<>();
        multipleQuestions = new ArrayList<>();
        weights = new ArrayList<>();
    }

    @Override
    public void enterTitle(GrammarParser.TitleContext ctx) {
        title = ctx.STRING().getText();
    }

    @Override
    public void enterFeedbackType(GrammarParser.FeedbackTypeContext ctx) {
        feedback = ctx.feedbackOption().getText();
    }

    @Override
    public void enterGradeType(GrammarParser.GradeTypeContext ctx) {
        grade = ctx.gradeOption().getText();
    }

    @Override
    public void enterSectionName(GrammarParser.SectionNameContext ctx) {
        section = ctx.STRING().getText();
        sections.add(section);
    }

    @Override
    public void enterDescription(GrammarParser.DescriptionContext ctx) {
        description = ctx.STRING().getText();
        descriptions.add(description);
    }

    @Override
    public void enterOption(GrammarParser.OptionContext ctx) {
        option = ctx.STRING().getText();
        options.add(option);
    }

    @Override
    public void enterMultipleChoiceQuestion(GrammarParser.MultipleChoiceQuestionContext ctx) {
        multipleQuestion = ctx.STRING().getText();
        multipleQuestions.add(multipleQuestion);
    }

//    @Override
//    public void enterMatchingQuestion(GrammarParser.MatchingQuestionContext ctx){
//        questionName = ctx.STRING().getText();
//        matchingQuestions.add(questionName);
//    }
//
//    public void enterMatchingPair(GrammarParser.MatchingPairContext ctx){
//        matchingPairQuestion = ctx.STRING().getText();
//        matchingPairs.add()
//    }

    public String getMultipleQuestion(){
        return multipleQuestion;
    }

    public List<String> getMultipleQuestions(){
        return List.copyOf(multipleQuestions);
    }
    public List<String> getWeights(){
        return List.copyOf(weights);
    }

//    public String getMatchingPair(){
//        return matchingPairQuestion;
//    }

    public String getOption(){
        return option;
    }

//    public String getWeight(){
//        return weight;
//    }

    public String getTitle() {
        return title;
    }

    public String getFeedbackType() {
        return feedback;
    }

    public String getGradeType() {
        return grade;
    }

    public List<String> getOptions(){
        return List.copyOf(options);
    }

    public List<String> getSections() {
        return List.copyOf(sections);
    }

    public List<String> getQuestions() {
        return List.copyOf(questions);
    }

    public List<String> getDescriptions() {
        return List.copyOf(descriptions);
    }
}
