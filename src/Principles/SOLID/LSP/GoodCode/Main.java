package SOLID.LSP.GoodCode;

public class Main {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        ReadableFile readableFile = new ReadOnlyFile();
        readableFile.read();

        WritableFile writableFile = new WritableFile();
        writableFile.read();
        writableFile.write();

        readAnyFile(readableFile);
        readAnyFile(writableFile);
    }

    public static void readAnyFile(ReadableFile file) {
        file.read();
    }
}
