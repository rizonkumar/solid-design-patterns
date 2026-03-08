package DesignPatterns.BehaviouralPatterns.OLD.MementoPattern.GoodCode;


public class TextEditorMain {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Caretaker caretaker = new Caretaker(); // History // State management

        editor.write("Hello World");
        caretaker.saveState(editor);

        editor.write("Hello Everyone");
        caretaker.saveState(editor);

        // Undo the last write
        caretaker.undo(editor);

        System.out.println("Printing the current content : " + editor.getContent());

    }
}
