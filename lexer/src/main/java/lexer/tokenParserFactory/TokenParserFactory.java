package lexer.tokenParserFactory;

import java.util.Stack;
import lexer.tokenParser.BaseTokenParser;
import lexer.tokenParser.TokenParser;
import lexer.tokenParser.TokenParserDecorator;
import lexer.tokenizer.Tokenizer;

public class TokenParserFactory {

  public static TokenParser buildTokens(String v) {
    return buildTokensHelper(Tokenizer.getTokens(v), new BaseTokenParser());
  }

  private static TokenParser buildTokensHelper(
      Stack<Tokenizer> tokenizers, TokenParser prevTokenParser) {
    if (tokenizers.isEmpty()) return prevTokenParser;
    TokenParser newTokenParser = new TokenParserDecorator(tokenizers.pop(), prevTokenParser);
    return buildTokensHelper(tokenizers, newTokenParser);
  }
}
