package DesignPatterns.BehaviouralPatterns.NEW.CommandPattern.Before;

// Receiver classes - Light and AC with basic on/off methods
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

/**
 * PROBLEM: Monolithic Control & Rigid Dependencies.
 * The 'NaiveRemoteControl' (Invoker) is directly aware of 'Light' and 'AC' (Receivers).
 * It hardcodes individual methods for every device state, destroying extensibility.
 */
class NaiveRemoteControl {

    // ISSUE 1: VIOLATION OF OPEN/CLOSED PRINCIPLE (OCP)
    // If you add a "SmartTV" or "Geyser" to the smart home system, you must rewrite
    // this class to add new fields and constructor arguments.
    private Light light;
    private AC ac;

    // ISSUE 2: LIMITED HISTORY RETENTION
    // Storing a simple string only allows a single level of history tracking.
    // If you press three buttons, you can only reverse the absolute last one,
    // and subsequent calls will cycle unexpectedly or fail to track true historical depth.
    private String lastAction = "";

    public NaiveRemoteControl(Light light, AC ac) {
        this.light = light;
        this.ac = ac;
    }

    // ISSUE 3: EXPLODING INTERFACE METHOD COUNT
    // Adding 10 new devices means adding 20-30 new explicitly named click methods.
    public void pressLightOn() {
        light.on();
        lastAction = "LIGHT_ON";
    }

    public void pressLightOff() {
        light.off();
        lastAction = "LIGHT_OFF";
    }

    public void pressACOn() {
        ac.on();
        lastAction = "AC_ON";
    }

    public void pressACOff() {
        ac.off();
        lastAction = "AC_OFF";
    }

    /**
     * ISSUE 4: SCATTERED STATE-REVERSAL BUSINESS LOGIC
     * The invoker has to reverse-engineer *how* to invert an operational task.
     * The switch block maps strings back to inverted operations, making the method
     * fragile and bloated. This belongs in isolated command abstractions.
     */
    public void pressUndo() {
        switch (lastAction) {
            case "LIGHT_ON":
                light.off();
                lastAction = "LIGHT_OFF";
                break;
            case "LIGHT_OFF":
                light.on();
                lastAction = "LIGHT_ON";
                break;
            case "AC_ON":
                ac.off();
                lastAction = "AC_OFF";
                break;
            case "AC_OFF":
                ac.on();
                lastAction = "AC_ON";
                break;
            default:
                System.out.println("No action to undo.");
                break;
        }
    }
}

// Client Code
public class Main {

    public static void main(String[] args) {
        Light light = new Light();
        AC ac = new AC();
        NaiveRemoteControl remote = new NaiveRemoteControl(light, ac);

        remote.pressLightOn();
        remote.pressACOn();
        remote.pressLightOff();

        // BUG TRACKING DEMO:
        // Because history is managed as a single shallow text variable, multiple calls
        // to pressUndo will not walk back down an execution stack safely.
        remote.pressUndo(); // Should undo LIGHT_OFF -> Light ON
        remote.pressUndo(); // BUG: Will overwrite state randomly or hit default conditions!
    }
}
