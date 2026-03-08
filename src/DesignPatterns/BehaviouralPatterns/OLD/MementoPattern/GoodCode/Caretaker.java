package DesignPatterns.BehaviouralPatterns.OLD.MementoPattern.GoodCode;

import java.util.Stack;

// Caretaker class: Manage Mementos (snapshots of the texteditor state)
public class Caretaker {
    private final Stack<EditorMemento> history = new Stack<>();

    public void saveState(TextEditor editor) {
        history.push(editor.save());
    }

    public void undo(TextEditor editor) {
        if (!history.empty()) {
            history.pop();
            editor.restore(history.peek());
        }
    }
}
