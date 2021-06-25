package interpreter;

import common.exeptions.IllegalGrammarException;
import interpreter.value.BooleanValue;
import interpreter.value.NumberValue;
import interpreter.value.StringValue;
import interpreter.value.Value;
import java.util.Stack;
import java.util.function.Consumer;
import parser.ASTVisitor;
import parser.nodes.*;

public class Interpreter implements ASTVisitor {

  private Stack<Value> stack;
  private Memory<String, Value> memory;
  private Console console;
  private Consumer<String> emitter;

  public Interpreter(Console console, Memory<String, Value> memory) {
    this.stack = new Stack<>();
    this.memory = memory;
    this.console = console;
  }

  public Interpreter(Console console, Consumer<String> emitter) {
    this.stack = new Stack<>();
    this.memory = new LocalStorage();
    this.console = console;
    this.emitter = emitter;
  }

  public Interpreter(Console console) {
    this.stack = new Stack<>();
    this.memory = new LocalStorage();
    this.console = console;
  }

  public void start(ASTNode astNode) {
    astNode.accept(this);
  }

  @Override
  public void visit(ProgramNode programNode) {
    programNode.getNodes().forEach(node -> node.accept(this));
  }

  @Override
  public void visit(DeclarationNode declarationNode) {
    String id = declarationNode.getIdentifierNode().getIdentifier();
    String type = declarationNode.getType();
    boolean isFinal = declarationNode.isFinal();

    checkDeclaration(id);
    ExpressionNode expressionNode = declarationNode.getExpressionNode();
    if (expressionNode != null) expressionNode.accept(this);
    Value result = null;
    if (!stack.empty()) {
      result = stack.pop();
      checkTypeOfValueWithId(result, type, id);
    }

    memory.save(id, result, isFinal);
  }

  @Override
  public void visit(AssigmentNode assigmentNode) {
    String id = assigmentNode.getIdentifierNode().getIdentifier();
    boolean isFinal = memory.isFinal(id);
    checkAssignation(id);
    Value value = memory.read(id);
    String type = null;
    if (value != null) type = memory.read(id).getType();

    assigmentNode.getExpressionNode().accept(this);
    Value result = stack.pop();
    if (type != null) {
      checkTypeOfValueWithId(result, type, id);
    }
    memory.save(id, result, isFinal);
  }

  @Override
  public void visit(AdditionNode additionNode) {
    operation(additionNode, Value::plus);
  }

  @Override
  public void visit(SubtractionNode subtractionNode) {
    operation(subtractionNode, Value::minus);
  }

  @Override
  public void visit(DivisionNode divisionNode) {
    operation(divisionNode, Value::divide);
  }

  @Override
  public void visit(MultiplicationNode multiplicationNode) {
    operation(multiplicationNode, Value::times);
  }

  @Override
  public void visit(AndNode andNode) {
    operation(andNode, Value::and);
  }

  @Override
  public void visit(OrNode orNode) {
    operation(orNode, Value::or);
  }

  @Override
  public void visit(MayorComparisonNode mayorComparisonNode) {
    operation(mayorComparisonNode, Value::greaterThan);
  }

  @Override
  public void visit(MinorComparisonNode minorComparisonNode) {
    operation(minorComparisonNode, Value::lessThan);
  }

  @Override
  public void visit(IfNode node) {
    node.getCondition().accept(this);
    if ((Boolean) this.stack.pop().getValue()) this.visit(node.getProgram());
  }

  public void operation(ExpressionNode node, CallbackOperation operation) {
    node.left().accept(this);
    node.right().accept(this);

    Value right = this.stack.pop();
    Value left = this.stack.pop();

    Value result = operation.execute(right, left);
    this.stack.push(result);
  }

  @Override
  public void visit(StringNode value) {
    this.stack.push(new StringValue(value.getValue()));
  }

  @Override
  public void visit(IntegerNode value) {
    this.stack.push(new NumberValue(value.getValue()));
  }

  @Override
  public void visit(BooleanNode value) {
    this.stack.push(new BooleanValue(value.getValue()));
  }

  @Override
  public void visit(IdentifierNode identifierNode) {
    checkAssignation(identifierNode.getIdentifier());
    stack.push(memory.read(identifierNode.getIdentifier()));
  }

  @Override
  public void visit(ExpressionNode expressionNode) {}

  @Override
  public void visit(PrintNode printNode) {
    printNode.getExpressionNode().accept(this);

    if (this.stack.size() > 0) {
      Value value = this.stack.pop();
      emitter.accept(value.getValue().toString());
      console.log(value.getValue());
    }
  }

  private interface CallbackOperation {
    Value execute(Value right, Value left);
  }

  private void checkDeclaration(String id) {
    if (memory.has(id)) {
      throw new IllegalGrammarException(String.format("variable %s is already defined", id));
    }
  }

  private void checkAssignation(String id) {
    if (!memory.has(id)) {
      throw new IllegalGrammarException(String.format("variable %s is not defined", id));
    }
    if (memory.isFinal(id)) {
      throw new IllegalGrammarException(String.format("const %s is already defined", id));
    }
  }

  private void checkTypeOfValueWithId(Value value, String type, String id) {
    if (!value.is(type)) {
      throw new IllegalGrammarException(String.format("%s is not of type %s", id, type));
    }
  }
}
