import java.util.*;

// ============================================================================
// 1. RECEIVERS (The Operational Hardlinks)
// ============================================================================
/**
 * Receivers contain the actual underlying hardware or platform implementation logic.
 * They know *how* to execute the specific operation. Notice they have zero knowledge
 * of the Command interface or the RemoteControl invoker.
 */
class Light {

    public void on() {
        System.out.println("Light turned ON");
    }

    public void off() {
        System.out.println("Light turned OFF");
    }
}

class AC {

    public void on() {
        System.out.println("AC turned ON");
    }

    public void off() {
        System.out.println("AC turned OFF");
    }
}

// ============================================================================
// 2. COMMAND INTERFACE
// ============================================================================
/**
 * Establishes the uniform behavioral contract. By forcing every request to
 * wrap itself inside this interface, the Invoker can trigger any hardware
 * transaction polymorphically via a simple execute() call.
 */
interface Command {
    void execute(); // Encapsulates the execution pathway
    void undo(); // Encapsulates the complete reversal logic
}

// ============================================================================
// 3. CONCRETE COMMANDS (The Decoupled Requests)
// ============================================================================
/**
 * Concrete commands bind a specific action to a specific hardware Receiver.
 * They extract the operational execution logic away from the Invoker class.
 */
class LightOnCommand implements Command {

    // COMPOSITION: Holds a direct reference to the target receiver
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on(); // Delegating to receiver execution mechanics
    }

    @Override
    public void undo() {
        light.off(); // Knowing the exact operational inverse
    }
}

class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

class AConCommand implements Command {

    private AC ac;

    public AConCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.on();
    }

    @Override
    public void undo() {
        ac.off();
    }
}

class ACOffCommand implements Command {

    private AC ac;

    public ACOffCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.off();
    }

    @Override
    public void undo() {
        ac.on();
    }
}

// ============================================================================
// 4. INVOKER (The Trigger Platform)
// ============================================================================
/**
 * The Invoker is completely decoupled from concrete devices like Light or AC.
 * It strictly tracks polymorphic `Command` reference slots, allowing it to execute
 * any current or future hardware command via unified button array indexes.
 */
class RemoteControl {

    // FLEXIBILITY: Generic Command slots allow dynamic runtime reassignment
    private Command[] buttons = new Command[4];

    // TRANSACTION HISTORY LAYER: Using a real data structure stack replaces
    // primitive shallow tracking strings, enabling infinite multi-level undo operations.
    private Stack<Command> commandHistory = new Stack<>();

    // Setter Injection: Allows buttons to be bound or rebound dynamically at runtime
    public void setCommand(int slot, Command command) {
        buttons[slot] = command;
    }

    public void pressButton(int slot) {
        if (buttons[slot] != null) {
            buttons[slot].execute(); // Polymorphic activation
            commandHistory.push(buttons[slot]); // Appending to the chronological transaction trail
        } else {
            System.out.println("No command assigned to slot " + slot);
        }
    }

    /**
     * UNIFIED UNDO RECOVERY
     * Notice there are no switch statements or conditional evaluation branches here.
     * The invoker simply pops the last transaction off the history pile and trusts
     * the command object to safely roll back its own operational state change.
     */
    public void pressUndo() {
        if (!commandHistory.isEmpty()) {
            commandHistory.pop().undo(); // Self-contained inversion execution
        } else {
            System.out.println("No commands to undo.");
        }
    }
}

// ============================================================================
// 5. CLIENT LAYER
// ============================================================================
/**
 * The Client configures the entire pipeline: instantiates the Receivers,
 * instantiates the concrete Command bindings, injects them into the Invoker slots,
 * and passes the control context to the runtime environment.
 */
public class Main {

    public static void main(String[] args) {
        // 1. Initialize hardlinks (Receivers)
        Light light = new Light();
        AC ac = new AC();

        // 2. Encapsulate actions inside standalone transaction wrappers (Commands)
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command acOn = new AConCommand(ac);
        Command acOff = new ACOffCommand(ac);

        // 3. Setup the control console platform (Invoker)
        RemoteControl remote = new RemoteControl();
        remote.setCommand(0, lightOn);
        remote.setCommand(1, lightOff);
        remote.setCommand(2, acOn);
        remote.setCommand(3, acOff);

        // 4. Runtime Simulations
        System.out.println("--- Triggering Action Sequence ---");
        remote.pressButton(0); // Slot 0: Light ON
        remote.pressButton(2); // Slot 2: AC ON
        remote.pressButton(1); // Slot 1: Light OFF

        System.out.println("\n--- Triggering Sequential Undo Walkback ---");
        remote.pressUndo(); // Pops LightOffCommand -> Executes undo() -> Light turns back ON
        remote.pressUndo(); // Pops AConCommand    -> Executes undo() -> AC turns back OFF
    }
}
