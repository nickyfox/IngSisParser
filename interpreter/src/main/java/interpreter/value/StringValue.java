package interpreter.value;

public class StringValue implements Value {

  private final String TYPE = "string";
  private String v;

  public StringValue(String value) {
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
        return new StringValue(v + integer.toString());
      }
    }
    return new StringValue(v + value.getValue());
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
  public Value lessEqualThan(Value value) {
    return value.lessEqualThan(this);
  }

  @Override
  public Value lessThan(Value value) {
    return value.lessThan(this);
  }

  @Override
  public String getValue() {
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
