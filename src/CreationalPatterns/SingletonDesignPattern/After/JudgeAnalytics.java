package CreationalPatterns.SingletonDesignPattern.After;

/**
 * Singleton: Ensures only one instance of JudgeAnalytics exists system-wide.
 * - Private constructor: no one can do "new JudgeAnalytics()".
 * - getInstance(): single point of access; creates the instance once, reuses it forever.
 * - Thread-safe: the static holder (JudgeAnalyticsHolder) is lazily loaded by the JVM.
 */
public class JudgeAnalytics {
    private int run = 0;
    private int submit = 0;

    private JudgeAnalytics() {
        // Prevent instantiation from outside.
    }

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

    /**
     * Single point of access. The instance is created lazily when first requested
     * and is thread-safe due to the class-loading semantics of the JVM.
     */
    public static JudgeAnalytics getInstance() {
        return JudgeAnalyticsHolder.INSTANCE;
    }

    private static class JudgeAnalyticsHolder {
        private static final JudgeAnalytics INSTANCE = new JudgeAnalytics();
    }
}
