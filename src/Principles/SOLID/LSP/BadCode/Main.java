package SOLID.LSP.BadCode;

public class Main {
    public static void main(String[] args) {
        File file = new ReadOnlyFile();
        file.read(); // works fine
        file.write(); // throwing an exception, violation of LSP.
    }
}
