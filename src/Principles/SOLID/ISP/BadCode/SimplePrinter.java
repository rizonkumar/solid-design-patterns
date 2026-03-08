package SOLID.ISP.BadCode;

/**
 * This SimplerPrinter just want to simple work which is printing
 * but we are forced to implement the method which wont be available in the
 * SimplePrinter
 *
 */
public class SimplePrinter implements Machine {
    @Override
    public void print(Document doc) {

    }

    @Override
    public void scan(Document doc) {
        throw new UnsupportedOperationException("Scan not supported");
    }

    @Override
    public void copy(Document doc) {
        throw new UnsupportedOperationException("Scan not supported");
    }
}
