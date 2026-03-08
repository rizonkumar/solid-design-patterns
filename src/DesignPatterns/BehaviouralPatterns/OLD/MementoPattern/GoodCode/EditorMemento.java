package DesignPatterns.BehaviouralPatterns.OLD.MementoPattern.GoodCode;

// Mementor Class: Stores the internal state of the Text Editor
public class EditorMemento {
    private final String content;

    public EditorMemento(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
