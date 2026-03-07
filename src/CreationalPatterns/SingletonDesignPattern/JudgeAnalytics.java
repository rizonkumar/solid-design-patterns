package CreationalPatterns.SingletonDesignPattern;

public class JudgeAnalytics {
    private int run = 0;
    private int submit = 0;

    public void countRun() {
        run++;
    }

    public void countSubmit() {
        submit++;
    }

    public int getRunCount() {
        return run;

    }

    public int getSubmitCount() {
        return submit;
    }
}
