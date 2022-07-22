package design_patterns.factory;

interface InterfaceTest {

  // impossible, public by default
  //  private int myAge = -1;

  int myAge = -1; // actually is always public final

  // no allowed, must have a body
  //  static void doSth();

  int thisMethodMustBeImplemented();

  static void doSth() {
    System.out.println("Sth done.");
  }

  // implicitly public
  default void printHello() {
    System.out.println("Hello world!");
    //    InterfaceTest.myAge = 5;
    //    myAge = 5;
  }

  // not possible, must be declared static or default
  //  void printBye() {
  //    System.out.println("Bye");
  //  }

  class Impl implements InterfaceTest {

    @Override
    public int thisMethodMustBeImplemented() {
      return 0;
    }

    // this method is optional, implementation is already provided from interface
    @Override
    public void printHello() {
      InterfaceTest.super.printHello();
    }
  }
}
