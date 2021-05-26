public class MyClass {
    
    private IInput input;
    
    public MyClass(IInput input) { this.input = input; }
    
    public void useInput() {
       System.out.println("Input provided: " + input.getValue()); 
    }
    
    
    public int getValue() {
        return 5;
    }

    public static void helloWorld() {
        System.out.println("Hello World!");
    }

}
