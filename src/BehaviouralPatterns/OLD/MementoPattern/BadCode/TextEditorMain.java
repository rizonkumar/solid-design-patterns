package BehaviouralPatterns.OLD.MementoPattern.BadCode;

public class TextEditorMain {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.write("Hello World");
        editor.write("Hello Everyone");
        // Problem -> Undo the last write!
        System.out.println("Printing the content : " + editor.getContent());
    }
}
