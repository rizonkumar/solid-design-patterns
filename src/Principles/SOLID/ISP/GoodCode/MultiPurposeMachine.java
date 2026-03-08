package SOLID.ISP.GoodCode;

public class MultiPurposeMachine implements Printer, Scanner, Copier {
    @Override
    public void copy(Document doc) {
        System.out.println("Copy document...");
    }

    @Override
    public void print(Document doc) {
        System.out.println("Printing document...");
    }

    @Override
    public void scan(Document doc) {
        System.out.println("Scan document...");

    }
}
