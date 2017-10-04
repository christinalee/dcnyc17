abstract class Parameterized<T, U> {
    abstract U computeSomething(T t);
}



class Thing extends Parameterized<String, Void> {



    private void voidFunction() {
        // does not need a return
    }

    @Override
    Void computeSomething(String s) {
        return null; // ... WAT?!
    }






    public void main() {
        // Not too bad
        Void thing = computeSomething("something");
    }

    private void useAsArg() {
        // Lol jk it's bad
        Void varName = voidFunction();
    }



}
