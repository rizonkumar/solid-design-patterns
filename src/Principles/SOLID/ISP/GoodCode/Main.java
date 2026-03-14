package SOLID.ISP.GoodCode;

/**
 * Good: Clients depend only on the interfaces they need (Printer, Scanner, Copier).
 * SimplerPrinter only implements Printer; MultiPurposeMachine implements all three.
 */
public class Main {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Document doc = new Document();

        SimplerPrinter printer = new SimplerPrinter();
        printer.print(doc);

        MultiPurposeMachine machine = new MultiPurposeMachine();
        machine.print(doc);
        machine.scan(doc);
        machine.copy(doc);
    }
}
