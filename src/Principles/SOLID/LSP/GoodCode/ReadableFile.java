package SOLID.LSP.GoodCode;

public class ReadableFile implements Readable {
    @Override
    public void read() {
        System.out.println("Reading from a file");
    }
}
