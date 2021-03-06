package interpreter.value;

import common.exeptions.IllegalGrammarException;

public interface Value {
  default Value plus(StringValue value) {
    throw new IllegalGrammarException(
        "Can not add a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value plus(NumberValue value) {
    throw new IllegalGrammarException(
        "Can not add a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value plus(BooleanValue value) {
    throw new IllegalGrammarException(
        "Can not add a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value minus(StringValue value) {
    throw new IllegalGrammarException(
        "Can not subtract a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value minus(NumberValue value) {
    throw new IllegalGrammarException(
        "Can not subtract a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value minus(BooleanValue value) {
    throw new IllegalGrammarException(
        "Can not add a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value times(StringValue value) {
    throw new IllegalGrammarException(
        "Can not multiply a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value times(NumberValue value) {
    throw new IllegalGrammarException(
        "Can not multiply a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value times(BooleanValue value) {
    throw new IllegalGrammarException(
        "Can not add a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value divide(StringValue value) {
    throw new IllegalGrammarException(
        "Can not divide a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value divide(NumberValue value) {
    throw new IllegalGrammarException(
        "Can not divide a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value divide(BooleanValue value) {
    throw new IllegalGrammarException(
        "Can not add a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value and(BooleanValue value) {
    throw new IllegalGrammarException(
        "Can not && a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value and(StringValue value) {
    throw new IllegalGrammarException(
        "Can not && a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value and(NumberValue value) {
    throw new IllegalGrammarException(
        "Can not && a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value or(BooleanValue value) {
    throw new IllegalGrammarException(
        "Can not || a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value or(StringValue value) {
    throw new IllegalGrammarException(
        "Can not || a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value or(NumberValue value) {
    throw new IllegalGrammarException(
        "Can not || a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value greaterThan(StringValue value) {
    throw new IllegalGrammarException(
        "Can not compare a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value greaterThan(NumberValue value) {
    throw new IllegalGrammarException(
        "Can not compare a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value greaterThan(BooleanValue value) {
    throw new IllegalGrammarException(
        "Can not compare a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value greaterEqualThan(StringValue value) {
    throw new IllegalGrammarException(
        "Can not compare a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value greaterEqualThan(NumberValue value) {
    throw new IllegalGrammarException(
        "Can not compare a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value greaterEqualThan(BooleanValue value) {
    throw new IllegalGrammarException(
        "Can not compare a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value lessThan(StringValue value) {
    throw new IllegalGrammarException(
        "Can not compare a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value lessThan(NumberValue value) {
    throw new IllegalGrammarException(
        "Can not compare a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value lessThan(BooleanValue value) {
    throw new IllegalGrammarException(
        "Can not compare a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value lessEqualThan(StringValue value) {
    throw new IllegalGrammarException(
        "Can not compare a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value lessEqualThan(NumberValue value) {
    throw new IllegalGrammarException(
        "Can not compare a " + this.getType() + " with a " + value.getType());
  }
  ;

  default Value lessEqualThan(BooleanValue value) {
    throw new IllegalGrammarException(
        "Can not compare a " + this.getType() + " with a " + value.getType());
  }
  ;

  Value divide(Value value);

  Value times(Value value);

  Value plus(Value value);

  Value minus(Value value);

  Value and(Value value);

  Value or(Value value);

  Value greaterThan(Value value);

  Value greaterEqualThan(Value value);

  Value lessEqualThan(Value value);

  Value lessThan(Value value);

  Object getValue();

  String getType();

  boolean is(String type);
}
