package search;

public class NotPredicate implements CompositePredicate {
    private final Predicate operand;

    public NotPredicate(final Predicate operand) {
        this.operand = operand;
    }

    @Override
    public boolean isMatch(final File inputFile) {
        return !operand.isMatch(inputFile);
    }
}
