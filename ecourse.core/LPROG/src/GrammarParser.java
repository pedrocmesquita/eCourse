// Generated from C:/Users/Joao Torres/Documents/Lprog/Lprog\Grammar.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, STRING=27, FLOAT=28, INT=29, DIGIT=30, WS=31;
	public static final int
		RULE_prog = 0, RULE_title = 1, RULE_header = 2, RULE_feedbackType = 3, 
		RULE_feedbackOption = 4, RULE_gradeType = 5, RULE_gradeOption = 6, RULE_section = 7, 
		RULE_sectionName = 8, RULE_description = 9, RULE_question = 10, RULE_matchingQuestion = 11, 
		RULE_matchingPair = 12, RULE_multipleChoiceQuestion = 13, RULE_option = 14, 
		RULE_shortAnswerQuestion = 15, RULE_shortAnswer = 16, RULE_numericalQuestion = 17, 
		RULE_numericalAnswer = 18, RULE_selectMissingWordsQuestion = 19, RULE_itemGroup = 20, 
		RULE_item = 21, RULE_trueFalseQuestion = 22, RULE_trueFalseAnswer = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "title", "header", "feedbackType", "feedbackOption", "gradeType", 
			"gradeOption", "section", "sectionName", "description", "question", "matchingQuestion", 
			"matchingPair", "multipleChoiceQuestion", "option", "shortAnswerQuestion", 
			"shortAnswer", "numericalQuestion", "numericalAnswer", "selectMissingWordsQuestion", 
			"itemGroup", "item", "trueFalseQuestion", "trueFalseAnswer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Title:'", "'Header:'", "'Feedback: '", "'None'", "'On-Submission'", 
			"'After-Closing'", "'Grade: '", "'Section:'", "'Description:'", "'Matching:'", 
			"'Question:'", "';Answer:'", "'MultipleChoice:'", "'Option:'", "';Weight: '", 
			"'ShortAnswer:'", "'Answer:'", "';Grade: '", "'Numerical:'", "';AcceptedError: '", 
			"'SelectMissingWords:'", "'Group:'", "'Item:'", "'TrueFalse'", "'True'", 
			"'False'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "STRING", "FLOAT", "INT", "DIGIT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			title();
			setState(49);
			header();
			setState(51); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(50);
				section();
				}
				}
				setState(53); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__7 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TitleContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(T__0);
			setState(56);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HeaderContext extends ParserRuleContext {
		public FeedbackTypeContext feedbackType() {
			return getRuleContext(FeedbackTypeContext.class,0);
		}
		public GradeTypeContext gradeType() {
			return getRuleContext(GradeTypeContext.class,0);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(T__1);
			setState(59);
			feedbackType();
			setState(60);
			gradeType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeedbackTypeContext extends ParserRuleContext {
		public FeedbackOptionContext feedbackOption() {
			return getRuleContext(FeedbackOptionContext.class,0);
		}
		public FeedbackTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feedbackType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFeedbackType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFeedbackType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitFeedbackType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeedbackTypeContext feedbackType() throws RecognitionException {
		FeedbackTypeContext _localctx = new FeedbackTypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_feedbackType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(T__2);
			setState(63);
			feedbackOption();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeedbackOptionContext extends ParserRuleContext {
		public FeedbackOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feedbackOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFeedbackOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFeedbackOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitFeedbackOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeedbackOptionContext feedbackOption() throws RecognitionException {
		FeedbackOptionContext _localctx = new FeedbackOptionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_feedbackOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 112L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GradeTypeContext extends ParserRuleContext {
		public GradeOptionContext gradeOption() {
			return getRuleContext(GradeOptionContext.class,0);
		}
		public GradeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gradeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterGradeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitGradeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitGradeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GradeTypeContext gradeType() throws RecognitionException {
		GradeTypeContext _localctx = new GradeTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_gradeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(T__6);
			setState(68);
			gradeOption();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GradeOptionContext extends ParserRuleContext {
		public GradeOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gradeOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterGradeOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitGradeOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitGradeOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GradeOptionContext gradeOption() throws RecognitionException {
		GradeOptionContext _localctx = new GradeOptionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_gradeOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 112L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SectionContext extends ParserRuleContext {
		public SectionNameContext sectionName() {
			return getRuleContext(SectionNameContext.class,0);
		}
		public DescriptionContext description() {
			return getRuleContext(DescriptionContext.class,0);
		}
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__7);
			setState(73);
			sectionName();
			setState(74);
			description();
			setState(76); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(75);
				question();
				}
				}
				setState(78); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 19473408L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SectionNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public SectionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sectionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterSectionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitSectionName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitSectionName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionNameContext sectionName() throws RecognitionException {
		SectionNameContext _localctx = new SectionNameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_sectionName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DescriptionContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public DescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionContext description() throws RecognitionException {
		DescriptionContext _localctx = new DescriptionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__8);
			setState(83);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionContext extends ParserRuleContext {
		public MatchingQuestionContext matchingQuestion() {
			return getRuleContext(MatchingQuestionContext.class,0);
		}
		public MultipleChoiceQuestionContext multipleChoiceQuestion() {
			return getRuleContext(MultipleChoiceQuestionContext.class,0);
		}
		public ShortAnswerQuestionContext shortAnswerQuestion() {
			return getRuleContext(ShortAnswerQuestionContext.class,0);
		}
		public NumericalQuestionContext numericalQuestion() {
			return getRuleContext(NumericalQuestionContext.class,0);
		}
		public SelectMissingWordsQuestionContext selectMissingWordsQuestion() {
			return getRuleContext(SelectMissingWordsQuestionContext.class,0);
		}
		public TrueFalseQuestionContext trueFalseQuestion() {
			return getRuleContext(TrueFalseQuestionContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_question);
		try {
			setState(91);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				matchingQuestion();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				multipleChoiceQuestion();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 3);
				{
				setState(87);
				shortAnswerQuestion();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 4);
				{
				setState(88);
				numericalQuestion();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 5);
				{
				setState(89);
				selectMissingWordsQuestion();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 6);
				{
				setState(90);
				trueFalseQuestion();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MatchingQuestionContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public List<MatchingPairContext> matchingPair() {
			return getRuleContexts(MatchingPairContext.class);
		}
		public MatchingPairContext matchingPair(int i) {
			return getRuleContext(MatchingPairContext.class,i);
		}
		public MatchingQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMatchingQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMatchingQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMatchingQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingQuestionContext matchingQuestion() throws RecognitionException {
		MatchingQuestionContext _localctx = new MatchingQuestionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_matchingQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(T__9);
			setState(94);
			match(STRING);
			setState(96); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(95);
				matchingPair();
				}
				}
				setState(98); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__10 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MatchingPairContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(GrammarParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(GrammarParser.STRING, i);
		}
		public MatchingPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingPair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMatchingPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMatchingPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMatchingPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingPairContext matchingPair() throws RecognitionException {
		MatchingPairContext _localctx = new MatchingPairContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_matchingPair);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(T__10);
			setState(101);
			match(STRING);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(102);
				match(T__11);
				setState(103);
				match(STRING);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultipleChoiceQuestionContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public MultipleChoiceQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoiceQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMultipleChoiceQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMultipleChoiceQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMultipleChoiceQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceQuestionContext multipleChoiceQuestion() throws RecognitionException {
		MultipleChoiceQuestionContext _localctx = new MultipleChoiceQuestionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_multipleChoiceQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(T__12);
			setState(107);
			match(STRING);
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(108);
				option();
				}
				}
				setState(111); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__13 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OptionContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public TerminalNode INT() { return getToken(GrammarParser.INT, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(T__13);
			setState(114);
			match(STRING);
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(115);
				match(T__14);
				setState(116);
				match(INT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ShortAnswerQuestionContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public List<ShortAnswerContext> shortAnswer() {
			return getRuleContexts(ShortAnswerContext.class);
		}
		public ShortAnswerContext shortAnswer(int i) {
			return getRuleContext(ShortAnswerContext.class,i);
		}
		public ShortAnswerQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortAnswerQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterShortAnswerQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitShortAnswerQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitShortAnswerQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortAnswerQuestionContext shortAnswerQuestion() throws RecognitionException {
		ShortAnswerQuestionContext _localctx = new ShortAnswerQuestionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_shortAnswerQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(T__15);
			setState(120);
			match(STRING);
			setState(122); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(121);
				shortAnswer();
				}
				}
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__16 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ShortAnswerContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public TerminalNode INT() { return getToken(GrammarParser.INT, 0); }
		public ShortAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterShortAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitShortAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitShortAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortAnswerContext shortAnswer() throws RecognitionException {
		ShortAnswerContext _localctx = new ShortAnswerContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_shortAnswer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(T__16);
			setState(127);
			match(STRING);
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__17) {
				{
				setState(128);
				match(T__17);
				setState(129);
				match(INT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumericalQuestionContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public List<NumericalAnswerContext> numericalAnswer() {
			return getRuleContexts(NumericalAnswerContext.class);
		}
		public NumericalAnswerContext numericalAnswer(int i) {
			return getRuleContext(NumericalAnswerContext.class,i);
		}
		public NumericalQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericalQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterNumericalQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitNumericalQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitNumericalQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericalQuestionContext numericalQuestion() throws RecognitionException {
		NumericalQuestionContext _localctx = new NumericalQuestionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_numericalQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(T__18);
			setState(133);
			match(STRING);
			setState(135); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(134);
				numericalAnswer();
				}
				}
				setState(137); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__16 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumericalAnswerContext extends ParserRuleContext {
		public List<TerminalNode> FLOAT() { return getTokens(GrammarParser.FLOAT); }
		public TerminalNode FLOAT(int i) {
			return getToken(GrammarParser.FLOAT, i);
		}
		public NumericalAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericalAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterNumericalAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitNumericalAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitNumericalAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericalAnswerContext numericalAnswer() throws RecognitionException {
		NumericalAnswerContext _localctx = new NumericalAnswerContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_numericalAnswer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(T__16);
			setState(140);
			match(FLOAT);
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(141);
				match(T__19);
				setState(142);
				match(FLOAT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectMissingWordsQuestionContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public List<ItemGroupContext> itemGroup() {
			return getRuleContexts(ItemGroupContext.class);
		}
		public ItemGroupContext itemGroup(int i) {
			return getRuleContext(ItemGroupContext.class,i);
		}
		public SelectMissingWordsQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectMissingWordsQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterSelectMissingWordsQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitSelectMissingWordsQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitSelectMissingWordsQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectMissingWordsQuestionContext selectMissingWordsQuestion() throws RecognitionException {
		SelectMissingWordsQuestionContext _localctx = new SelectMissingWordsQuestionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_selectMissingWordsQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(T__20);
			setState(146);
			match(STRING);
			setState(148); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(147);
				itemGroup();
				}
				}
				setState(150); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__21 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ItemGroupContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public List<ItemContext> item() {
			return getRuleContexts(ItemContext.class);
		}
		public ItemContext item(int i) {
			return getRuleContext(ItemContext.class,i);
		}
		public ItemGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_itemGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterItemGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitItemGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitItemGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ItemGroupContext itemGroup() throws RecognitionException {
		ItemGroupContext _localctx = new ItemGroupContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_itemGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(T__21);
			setState(153);
			match(STRING);
			setState(155); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(154);
				item();
				}
				}
				setState(157); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__22 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ItemContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public ItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ItemContext item() throws RecognitionException {
		ItemContext _localctx = new ItemContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(T__22);
			setState(160);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TrueFalseQuestionContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public TrueFalseAnswerContext trueFalseAnswer() {
			return getRuleContext(TrueFalseAnswerContext.class,0);
		}
		public TrueFalseQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueFalseQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterTrueFalseQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitTrueFalseQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitTrueFalseQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueFalseQuestionContext trueFalseQuestion() throws RecognitionException {
		TrueFalseQuestionContext _localctx = new TrueFalseQuestionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_trueFalseQuestion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(T__23);
			setState(163);
			match(STRING);
			setState(164);
			trueFalseAnswer();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TrueFalseAnswerContext extends ParserRuleContext {
		public TrueFalseAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueFalseAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterTrueFalseAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitTrueFalseAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitTrueFalseAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueFalseAnswerContext trueFalseAnswer() throws RecognitionException {
		TrueFalseAnswerContext _localctx = new TrueFalseAnswerContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_trueFalseAnswer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(T__11);
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__24 || _la==T__25) {
				{
				setState(167);
				_la = _input.LA(1);
				if ( !(_la==T__24 || _la==T__25) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001f\u00ab\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0004\u00004\b\u0000\u000b\u0000\f\u00005\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0004\u0007M\b\u0007\u000b\u0007\f\u0007N\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0003\n\\\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0004\u000b"+
		"a\b\u000b\u000b\u000b\f\u000bb\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f"+
		"i\b\f\u0001\r\u0001\r\u0001\r\u0004\rn\b\r\u000b\r\f\ro\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0003\u000ev\b\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0004\u000f{\b\u000f\u000b\u000f\f\u000f|\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0083\b\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0004\u0011\u0088\b\u0011\u000b\u0011\f\u0011"+
		"\u0089\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0090"+
		"\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0004\u0013\u0095\b\u0013"+
		"\u000b\u0013\f\u0013\u0096\u0001\u0014\u0001\u0014\u0001\u0014\u0004\u0014"+
		"\u009c\b\u0014\u000b\u0014\f\u0014\u009d\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001"+
		"\u0017\u0003\u0017\u00a9\b\u0017\u0001\u0017\u0000\u0000\u0018\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.\u0000\u0002\u0001\u0000\u0004\u0006\u0001\u0000\u0019\u001a"+
		"\u00a4\u00000\u0001\u0000\u0000\u0000\u00027\u0001\u0000\u0000\u0000\u0004"+
		":\u0001\u0000\u0000\u0000\u0006>\u0001\u0000\u0000\u0000\bA\u0001\u0000"+
		"\u0000\u0000\nC\u0001\u0000\u0000\u0000\fF\u0001\u0000\u0000\u0000\u000e"+
		"H\u0001\u0000\u0000\u0000\u0010P\u0001\u0000\u0000\u0000\u0012R\u0001"+
		"\u0000\u0000\u0000\u0014[\u0001\u0000\u0000\u0000\u0016]\u0001\u0000\u0000"+
		"\u0000\u0018d\u0001\u0000\u0000\u0000\u001aj\u0001\u0000\u0000\u0000\u001c"+
		"q\u0001\u0000\u0000\u0000\u001ew\u0001\u0000\u0000\u0000 ~\u0001\u0000"+
		"\u0000\u0000\"\u0084\u0001\u0000\u0000\u0000$\u008b\u0001\u0000\u0000"+
		"\u0000&\u0091\u0001\u0000\u0000\u0000(\u0098\u0001\u0000\u0000\u0000*"+
		"\u009f\u0001\u0000\u0000\u0000,\u00a2\u0001\u0000\u0000\u0000.\u00a6\u0001"+
		"\u0000\u0000\u000001\u0003\u0002\u0001\u000013\u0003\u0004\u0002\u0000"+
		"24\u0003\u000e\u0007\u000032\u0001\u0000\u0000\u000045\u0001\u0000\u0000"+
		"\u000053\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u00006\u0001\u0001"+
		"\u0000\u0000\u000078\u0005\u0001\u0000\u000089\u0005\u001b\u0000\u0000"+
		"9\u0003\u0001\u0000\u0000\u0000:;\u0005\u0002\u0000\u0000;<\u0003\u0006"+
		"\u0003\u0000<=\u0003\n\u0005\u0000=\u0005\u0001\u0000\u0000\u0000>?\u0005"+
		"\u0003\u0000\u0000?@\u0003\b\u0004\u0000@\u0007\u0001\u0000\u0000\u0000"+
		"AB\u0007\u0000\u0000\u0000B\t\u0001\u0000\u0000\u0000CD\u0005\u0007\u0000"+
		"\u0000DE\u0003\f\u0006\u0000E\u000b\u0001\u0000\u0000\u0000FG\u0007\u0000"+
		"\u0000\u0000G\r\u0001\u0000\u0000\u0000HI\u0005\b\u0000\u0000IJ\u0003"+
		"\u0010\b\u0000JL\u0003\u0012\t\u0000KM\u0003\u0014\n\u0000LK\u0001\u0000"+
		"\u0000\u0000MN\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000NO\u0001"+
		"\u0000\u0000\u0000O\u000f\u0001\u0000\u0000\u0000PQ\u0005\u001b\u0000"+
		"\u0000Q\u0011\u0001\u0000\u0000\u0000RS\u0005\t\u0000\u0000ST\u0005\u001b"+
		"\u0000\u0000T\u0013\u0001\u0000\u0000\u0000U\\\u0003\u0016\u000b\u0000"+
		"V\\\u0003\u001a\r\u0000W\\\u0003\u001e\u000f\u0000X\\\u0003\"\u0011\u0000"+
		"Y\\\u0003&\u0013\u0000Z\\\u0003,\u0016\u0000[U\u0001\u0000\u0000\u0000"+
		"[V\u0001\u0000\u0000\u0000[W\u0001\u0000\u0000\u0000[X\u0001\u0000\u0000"+
		"\u0000[Y\u0001\u0000\u0000\u0000[Z\u0001\u0000\u0000\u0000\\\u0015\u0001"+
		"\u0000\u0000\u0000]^\u0005\n\u0000\u0000^`\u0005\u001b\u0000\u0000_a\u0003"+
		"\u0018\f\u0000`_\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000b`\u0001"+
		"\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000c\u0017\u0001\u0000\u0000"+
		"\u0000de\u0005\u000b\u0000\u0000eh\u0005\u001b\u0000\u0000fg\u0005\f\u0000"+
		"\u0000gi\u0005\u001b\u0000\u0000hf\u0001\u0000\u0000\u0000hi\u0001\u0000"+
		"\u0000\u0000i\u0019\u0001\u0000\u0000\u0000jk\u0005\r\u0000\u0000km\u0005"+
		"\u001b\u0000\u0000ln\u0003\u001c\u000e\u0000ml\u0001\u0000\u0000\u0000"+
		"no\u0001\u0000\u0000\u0000om\u0001\u0000\u0000\u0000op\u0001\u0000\u0000"+
		"\u0000p\u001b\u0001\u0000\u0000\u0000qr\u0005\u000e\u0000\u0000ru\u0005"+
		"\u001b\u0000\u0000st\u0005\u000f\u0000\u0000tv\u0005\u001d\u0000\u0000"+
		"us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000v\u001d\u0001\u0000"+
		"\u0000\u0000wx\u0005\u0010\u0000\u0000xz\u0005\u001b\u0000\u0000y{\u0003"+
		" \u0010\u0000zy\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|z\u0001"+
		"\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}\u001f\u0001\u0000\u0000"+
		"\u0000~\u007f\u0005\u0011\u0000\u0000\u007f\u0082\u0005\u001b\u0000\u0000"+
		"\u0080\u0081\u0005\u0012\u0000\u0000\u0081\u0083\u0005\u001d\u0000\u0000"+
		"\u0082\u0080\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000"+
		"\u0083!\u0001\u0000\u0000\u0000\u0084\u0085\u0005\u0013\u0000\u0000\u0085"+
		"\u0087\u0005\u001b\u0000\u0000\u0086\u0088\u0003$\u0012\u0000\u0087\u0086"+
		"\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u0087"+
		"\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a#\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0005\u0011\u0000\u0000\u008c\u008f\u0005"+
		"\u001c\u0000\u0000\u008d\u008e\u0005\u0014\u0000\u0000\u008e\u0090\u0005"+
		"\u001c\u0000\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f\u0090\u0001"+
		"\u0000\u0000\u0000\u0090%\u0001\u0000\u0000\u0000\u0091\u0092\u0005\u0015"+
		"\u0000\u0000\u0092\u0094\u0005\u001b\u0000\u0000\u0093\u0095\u0003(\u0014"+
		"\u0000\u0094\u0093\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000"+
		"\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000"+
		"\u0000\u0097\'\u0001\u0000\u0000\u0000\u0098\u0099\u0005\u0016\u0000\u0000"+
		"\u0099\u009b\u0005\u001b\u0000\u0000\u009a\u009c\u0003*\u0015\u0000\u009b"+
		"\u009a\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d"+
		"\u009b\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e"+
		")\u0001\u0000\u0000\u0000\u009f\u00a0\u0005\u0017\u0000\u0000\u00a0\u00a1"+
		"\u0005\u001b\u0000\u0000\u00a1+\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005"+
		"\u0018\u0000\u0000\u00a3\u00a4\u0005\u001b\u0000\u0000\u00a4\u00a5\u0003"+
		".\u0017\u0000\u00a5-\u0001\u0000\u0000\u0000\u00a6\u00a8\u0005\f\u0000"+
		"\u0000\u00a7\u00a9\u0007\u0001\u0000\u0000\u00a8\u00a7\u0001\u0000\u0000"+
		"\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9/\u0001\u0000\u0000\u0000"+
		"\u000e5N[bhou|\u0082\u0089\u008f\u0096\u009d\u00a8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}