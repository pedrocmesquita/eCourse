package eapli.ecourse.app.teacher.console.presentation.exam;// Generated from C:/Users/Joao Torres/Documents/sem4pi-22-23-68-master1.2/ecourse.core/LPROG\Grammar.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(GrammarParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle(GrammarParser.TitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#feedbackType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeedbackType(GrammarParser.FeedbackTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#feedbackOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeedbackOption(GrammarParser.FeedbackOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#gradeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGradeType(GrammarParser.GradeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#gradeOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGradeOption(GrammarParser.GradeOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(GrammarParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#sectionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSectionName(GrammarParser.SectionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription(GrammarParser.DescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(GrammarParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#matchingQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingQuestion(GrammarParser.MatchingQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#matchingPair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingPair(GrammarParser.MatchingPairContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswer(GrammarParser.AnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#multipleChoiceQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoiceQuestion(GrammarParser.MultipleChoiceQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(GrammarParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#weight}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeight(GrammarParser.WeightContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#shortAnswerQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortAnswerQuestion(GrammarParser.ShortAnswerQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#shortAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortAnswer(GrammarParser.ShortAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#grade}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrade(GrammarParser.GradeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#numericalQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalQuestion(GrammarParser.NumericalQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#numericalAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalAnswer(GrammarParser.NumericalAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#acceptedError}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAcceptedError(GrammarParser.AcceptedErrorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#selectMissingWordsQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectMissingWordsQuestion(GrammarParser.SelectMissingWordsQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#itemGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItemGroup(GrammarParser.ItemGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItem(GrammarParser.ItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#trueFalseQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueFalseQuestion(GrammarParser.TrueFalseQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#trueFalseAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueFalseAnswer(GrammarParser.TrueFalseAnswerContext ctx);
}
