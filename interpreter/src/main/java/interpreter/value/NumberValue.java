package interpreter.value;

public class NumberValue implements Value {

  private final String TYPE = "number";
  private Double v;

  public NumberValue(double value) {
    this.v = value;
  }

  @Override
  public Value plus(StringValue value) {
    return new StringValue(v + value.getValue());
  }

  @Override
  public Value plus(NumberValue value) {
    String auxString = value.getValue().toString();
    double self = Double.parseDouble(auxString);
    if (Double.toString(self) != null) {
      double ceil = Math.ceil(self);
      if (self == ceil) {
        Integer integer = (int) ceil;
        return new NumberValue(integer + v);
      }
    }
    return new NumberValue(value.getValue() + v);
  }

  @Override
  public Value minus(NumberValue value) {
    return new NumberValue(v - value.getValue());
  }

  @Override
  public Value times(NumberValue value) {
    return new NumberValue(value.getValue() * v);
  }

  @Override
  public Value greaterThan(NumberValue value) {
    return new BooleanValue(value.getValue() > v);
  }

  @Override
  public Value greaterEqualThan(NumberValue value) {
    return new BooleanValue(value.getValue() >= v);
  }

  @Override
  public Value lessThan(NumberValue value) {
    return new BooleanValue(value.getValue() < v);
  }

  @Override
  public Value lessEqualThan(NumberValue value) {
    return new BooleanValue(value.getValue() < v);
  }

  @Override
  public Value divide(NumberValue value) {
    if (this.v == 0) {
      throw new ArithmeticException("Can not divide by zero");
    }

    return new NumberValue(value.getValue() / v);
  }

  @Override
  public Value divide(Value value) {
    return value.divide(this);
  }

  @Override
  public Value times(Value value) {
    return value.times(this);
  }

  @Override
  public Value plus(Value value) {
    return value.plus(this);
  }

  @Override
  public Value minus(Value value) {
    return value.minus(this);
  }

  @Override
  public Value and(Value value) {
    return value.and(this);
  }

  @Override
  public Value or(Value value) {
    return value.or(this);
  }

  @Override
  public Value greaterThan(Value value) {
    return value.greaterThan(this);
  }

  @Override
  public Value greaterEqualThan(Value value) {
    return value.greaterEqualThan(this);
  }

  @Override
  public Value lessThan(Value value) {
    return value.lessThan(this);
  }

  @Override
  public Value lessEqualThan(Value value) {
    return value.lessEqualThan(this);
  }

  public Double getValue() {
    return this.v;
  }

  @Override
  public String getType() {
    return TYPE;
  }

  @Override
  public boolean is(String type) {
    return type.equals(getType());
  }
}
