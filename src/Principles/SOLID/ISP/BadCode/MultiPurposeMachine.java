package SOLID.ISP.BadCode;

// Here the Multipurpose machine uses all the machine interface
public class MultiPurposeMachine implements Machine {
    @Override
    public void print(Document doc) {
        System.out.println("Printing document...");
    }

    @Override
    public void scan(Document doc) {
        System.out.println("Scan document...");
    }

    @Override
    public void copy(Document doc) {
        System.out.println("Copy document...");
    }
}
