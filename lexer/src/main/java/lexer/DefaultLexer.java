package lexer;

import java.util.ArrayList;
import java.util.List;
import lexer.tokenParser.TokenParser;
import lexer.tokenParserFactory.TokenParserFactory;
import lexer.tokens.EOFToken;
import lexer.tokens.Token;

public class DefaultLexer implements Lexer {
  TokenParser tokenParser;

  public DefaultLexer(String v) {
    tokenParser = TokenParserFactory.buildTokens(v);
  }

  public List<Token> lex(String str) {
    List<Token> tokens = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    Token currentToken = null;

    for (char ch : str.toCharArray()) {
      sb.append(ch);
      Token newToken = tokenParser.getToken(sb.toString().trim());
      if (newToken == null && currentToken != null) {
        sb = new StringBuilder(("" + ch).trim());
        tokens.add(currentToken);
        currentToken = tokenParser.getToken(sb.toString());
      } else currentToken = newToken;
    }
    tokens.add(tokenParser.getToken(sb.toString()));
    tokens.add(new EOFToken());
    return tokens;
  }
}
