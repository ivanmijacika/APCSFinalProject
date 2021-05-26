void setup() {
  IInput input = new Input();
  MyClass.helloWorld();
  MyClass obj = new MyClass(input);
  println(obj.getValue());
  obj.useInput();
}
