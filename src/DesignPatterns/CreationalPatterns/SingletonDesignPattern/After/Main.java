package DesignPatterns.CreationalPatterns.SingletonDesignPattern.After;

/**
 * All code uses JudgeAnalytics.getInstance(). There is only one instance;
 * run and submit counts are shared globally.
 */
class Main {

    public static void main(String[] args) {
        JudgeAnalytics analytics = JudgeAnalytics.getInstance();

        analytics.countRun();
        analytics.countRun();
        analytics.countSubmit();

        // Same instance can be obtained anywhere; counts are global.
        JudgeAnalytics same = JudgeAnalytics.getInstance();
        System.out.println("Total runs: " + same.getRunCount()); // 2
        System.out.println("Total submits: " + same.getSubmitCount()); // 1
    }
}
