package parser.nodes;

import parser.ASTVisitor;

public class MayorComparisonNode extends ExpressionNode {

  public MayorComparisonNode(ExpressionNode expLeft, ExpressionNode expRight) {
    super(expLeft, expRight);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
