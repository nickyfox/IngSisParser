package lexer.tokens;

import java.util.regex.Pattern;
import lexer.TokenVisitor;

public class DivisionToken extends Token {

  public DivisionToken() {
    super(Pattern.compile("/", Pattern.MULTILINE));
  }

  public DivisionToken(String value) {
    super(Pattern.compile("/", Pattern.MULTILINE), value);
  }

  @Override
  public Token withValue(String value) {
    return new DivisionToken(value);
  }

  @Override
  public void accept(TokenVisitor visitor) {
    visitor.visit(this);
  }
}
