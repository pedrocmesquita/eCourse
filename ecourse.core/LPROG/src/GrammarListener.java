package eapli.ecourse.app.teacher.console.presentation.exam;// Generated from C:/Users/Joao Torres/Documents/sem4pi-22-23-68-master1.2/ecourse.core/LPROG\Grammar.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(GrammarParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(GrammarParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(GrammarParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(GrammarParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#feedbackType}.
	 * @param ctx the parse tree
	 */
	void enterFeedbackType(GrammarParser.FeedbackTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#feedbackType}.
	 * @param ctx the parse tree
	 */
	void exitFeedbackType(GrammarParser.FeedbackTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#feedbackOption}.
	 * @param ctx the parse tree
	 */
	void enterFeedbackOption(GrammarParser.FeedbackOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#feedbackOption}.
	 * @param ctx the parse tree
	 */
	void exitFeedbackOption(GrammarParser.FeedbackOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#gradeType}.
	 * @param ctx the parse tree
	 */
	void enterGradeType(GrammarParser.GradeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#gradeType}.
	 * @param ctx the parse tree
	 */
	void exitGradeType(GrammarParser.GradeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#gradeOption}.
	 * @param ctx the parse tree
	 */
	void enterGradeOption(GrammarParser.GradeOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#gradeOption}.
	 * @param ctx the parse tree
	 */
	void exitGradeOption(GrammarParser.GradeOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(GrammarParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(GrammarParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#sectionName}.
	 * @param ctx the parse tree
	 */
	void enterSectionName(GrammarParser.SectionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#sectionName}.
	 * @param ctx the parse tree
	 */
	void exitSectionName(GrammarParser.SectionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(GrammarParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(GrammarParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(GrammarParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(GrammarParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#matchingQuestion}.
	 * @param ctx the parse tree
	 */
	void enterMatchingQuestion(GrammarParser.MatchingQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#matchingQuestion}.
	 * @param ctx the parse tree
	 */
	void exitMatchingQuestion(GrammarParser.MatchingQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#matchingPair}.
	 * @param ctx the parse tree
	 */
	void enterMatchingPair(GrammarParser.MatchingPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#matchingPair}.
	 * @param ctx the parse tree
	 */
	void exitMatchingPair(GrammarParser.MatchingPairContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#answer}.
	 * @param ctx the parse tree
	 */
	void enterAnswer(GrammarParser.AnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#answer}.
	 * @param ctx the parse tree
	 */
	void exitAnswer(GrammarParser.AnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#multipleChoiceQuestion}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoiceQuestion(GrammarParser.MultipleChoiceQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#multipleChoiceQuestion}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoiceQuestion(GrammarParser.MultipleChoiceQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(GrammarParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(GrammarParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#weight}.
	 * @param ctx the parse tree
	 */
	void enterWeight(GrammarParser.WeightContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#weight}.
	 * @param ctx the parse tree
	 */
	void exitWeight(GrammarParser.WeightContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#shortAnswerQuestion}.
	 * @param ctx the parse tree
	 */
	void enterShortAnswerQuestion(GrammarParser.ShortAnswerQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#shortAnswerQuestion}.
	 * @param ctx the parse tree
	 */
	void exitShortAnswerQuestion(GrammarParser.ShortAnswerQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#shortAnswer}.
	 * @param ctx the parse tree
	 */
	void enterShortAnswer(GrammarParser.ShortAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#shortAnswer}.
	 * @param ctx the parse tree
	 */
	void exitShortAnswer(GrammarParser.ShortAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#grade}.
	 * @param ctx the parse tree
	 */
	void enterGrade(GrammarParser.GradeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#grade}.
	 * @param ctx the parse tree
	 */
	void exitGrade(GrammarParser.GradeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#numericalQuestion}.
	 * @param ctx the parse tree
	 */
	void enterNumericalQuestion(GrammarParser.NumericalQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#numericalQuestion}.
	 * @param ctx the parse tree
	 */
	void exitNumericalQuestion(GrammarParser.NumericalQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#numericalAnswer}.
	 * @param ctx the parse tree
	 */
	void enterNumericalAnswer(GrammarParser.NumericalAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#numericalAnswer}.
	 * @param ctx the parse tree
	 */
	void exitNumericalAnswer(GrammarParser.NumericalAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#acceptedError}.
	 * @param ctx the parse tree
	 */
	void enterAcceptedError(GrammarParser.AcceptedErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#acceptedError}.
	 * @param ctx the parse tree
	 */
	void exitAcceptedError(GrammarParser.AcceptedErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#selectMissingWordsQuestion}.
	 * @param ctx the parse tree
	 */
	void enterSelectMissingWordsQuestion(GrammarParser.SelectMissingWordsQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#selectMissingWordsQuestion}.
	 * @param ctx the parse tree
	 */
	void exitSelectMissingWordsQuestion(GrammarParser.SelectMissingWordsQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#itemGroup}.
	 * @param ctx the parse tree
	 */
	void enterItemGroup(GrammarParser.ItemGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#itemGroup}.
	 * @param ctx the parse tree
	 */
	void exitItemGroup(GrammarParser.ItemGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#item}.
	 * @param ctx the parse tree
	 */
	void enterItem(GrammarParser.ItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#item}.
	 * @param ctx the parse tree
	 */
	void exitItem(GrammarParser.ItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#trueFalseQuestion}.
	 * @param ctx the parse tree
	 */
	void enterTrueFalseQuestion(GrammarParser.TrueFalseQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#trueFalseQuestion}.
	 * @param ctx the parse tree
	 */
	void exitTrueFalseQuestion(GrammarParser.TrueFalseQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#trueFalseAnswer}.
	 * @param ctx the parse tree
	 */
	void enterTrueFalseAnswer(GrammarParser.TrueFalseAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#trueFalseAnswer}.
	 * @param ctx the parse tree
	 */
	void exitTrueFalseAnswer(GrammarParser.TrueFalseAnswerContext ctx);
}
