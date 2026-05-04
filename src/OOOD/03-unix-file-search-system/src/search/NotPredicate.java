package search;

// Implements logical NOT operation on a predicate
public class NotPredicate implements CompositePredicate {
    // The predicate to negate
    private final Predicate operand;

    // Creates a new NOT predicate with the specified predicate to negate
    public NotPredicate(final Predicate operand) {
        this.operand = operand;
    }

    @Override
    public boolean isMatch(final File inputFile) {
        return !operand.isMatch(inputFile);
    }
}
