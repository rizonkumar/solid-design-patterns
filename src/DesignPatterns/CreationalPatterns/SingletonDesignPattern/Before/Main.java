package DesignPatterns.CreationalPatterns.SingletonDesignPattern.Before;

/**
 * PROBLEM: JudgeAnalytics is a plain class. Anyone can create multiple instances.
 * Each instance has its own run/submit counts — there is no single source of truth.
 */
class JudgeAnalytics {

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

/**
 * PROBLEM: Main (and any other class) creates its own JudgeAnalytics.
 * If another service also does "new JudgeAnalytics()", we now have two separate
 * analytics objects. Counts are not shared; there is no global view.
 */
class Main {

    public static void main(String[] args) {
        JudgeAnalytics analytics1 = new JudgeAnalytics();
        JudgeAnalytics analytics2 = new JudgeAnalytics();

        analytics1.countRun();
        analytics1.countRun();
        analytics2.countSubmit();

        // Each instance has different counts. "Global" totals do not exist.
        System.out.println("analytics1 runs: " + analytics1.getRunCount()); // 2
        System.out.println(
            "analytics1 submits: " + analytics1.getSubmitCount()
        ); // 0
        System.out.println("analytics2 runs: " + analytics2.getRunCount()); // 0
        System.out.println(
            "analytics2 submits: " + analytics2.getSubmitCount()
        ); // 1
        // No way to get "total runs = 2, total submits = 1" from a single object.
    }
}
