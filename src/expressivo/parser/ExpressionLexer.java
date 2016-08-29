// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in IntegerExpression.g4 and re-run Antlr.

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionLexer extends Lexer {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, NUMBER=3, VAR=4, WS=5, ADD=6, MULT=7;
  public static String[] modeNames = {
    "DEFAULT_MODE"
  };

  public static final String[] ruleNames = {
    "T__0", "T__1", "NUMBER", "VAR", "WS", "ADD", "MULT"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'('", "')'", null, null, null, "'+'", "'*'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, "NUMBER", "VAR", "WS", "ADD", "MULT"
  };
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


      // This method makes the lexer or parser stop running if it encounters
      // invalid input and throw a ParseCancellationException.
      public void reportErrorsAsExceptions() {
          // To prevent any reports to standard error, add this line:
          //removeErrorListeners();
          
          addErrorListener(new BaseErrorListener() {
              public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol, 
                                      int line, int charPositionInLine,
                                      String msg, RecognitionException e) {
                  throw new ParseCancellationException(msg, e);
              }
          });
      }


  public ExpressionLexer(CharStream input) {
    super(input);
    _interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }

  @Override
  public String getGrammarFileName() { return "Expression.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public String[] getModeNames() { return modeNames; }

  @Override
  public ATN getATN() { return _ATN; }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\tD\b\1\4\2\t\2"+
      "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\3\3\3\3"+
      "\4\6\4\27\n\4\r\4\16\4\30\3\4\3\4\7\4\35\n\4\f\4\16\4 \13\4\5\4\""+
      "\n\4\3\4\3\4\6\4&\n\4\r\4\16\4\'\5\4*\n\4\3\4\3\4\5\4.\n\4\3\4\6\4"+
      "\61\n\4\r\4\16\4\62\5\4\65\n\4\3\5\6\58\n\5\r\5\16\59\3\6\6\6=\n\6"+
      "\r\6\16\6>\3\7\3\7\3\b\3\b\2\2\t\3\3\5\4\7\5\t\6\13\7\r\b\17\t\3\2"+
      "\7\3\2\62;\4\2GGgg\4\2--//\4\2C\\c|\5\2\13\f\17\17\"\"M\2\3\3\2\2"+
      "\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
      "\17\3\2\2\2\3\21\3\2\2\2\5\23\3\2\2\2\7)\3\2\2\2\t\67\3\2\2\2\13<"+
      "\3\2\2\2\r@\3\2\2\2\17B\3\2\2\2\21\22\7*\2\2\22\4\3\2\2\2\23\24\7"+
      "+\2\2\24\6\3\2\2\2\25\27\t\2\2\2\26\25\3\2\2\2\27\30\3\2\2\2\30\26"+
      "\3\2\2\2\30\31\3\2\2\2\31!\3\2\2\2\32\36\7\60\2\2\33\35\t\2\2\2\34"+
      "\33\3\2\2\2\35 \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37\"\3\2\2\2 "+
      "\36\3\2\2\2!\32\3\2\2\2!\"\3\2\2\2\"*\3\2\2\2#%\7\60\2\2$&\t\2\2\2"+
      "%$\3\2\2\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(*\3\2\2\2)\26\3\2\2\2"+
      ")#\3\2\2\2*\64\3\2\2\2+-\t\3\2\2,.\t\4\2\2-,\3\2\2\2-.\3\2\2\2.\60"+
      "\3\2\2\2/\61\t\2\2\2\60/\3\2\2\2\61\62\3\2\2\2\62\60\3\2\2\2\62\63"+
      "\3\2\2\2\63\65\3\2\2\2\64+\3\2\2\2\64\65\3\2\2\2\65\b\3\2\2\2\668"+
      "\t\5\2\2\67\66\3\2\2\289\3\2\2\29\67\3\2\2\29:\3\2\2\2:\n\3\2\2\2"+
      ";=\t\6\2\2<;\3\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2\2?\f\3\2\2\2@A\7"+
      "-\2\2A\16\3\2\2\2BC\7,\2\2C\20\3\2\2\2\r\2\30\36!\')-\62\649>\2";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}