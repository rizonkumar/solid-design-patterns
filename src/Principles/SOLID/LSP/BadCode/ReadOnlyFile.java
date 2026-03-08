package SOLID.LSP.BadCode;

public class ReadOnlyFile extends File {
    // here in read-only file, the write method is not supported hence this is BAD_CODE
    public void write() {
        throw new UnsupportedOperationException("Can't write to a ready only file");
    }
}
