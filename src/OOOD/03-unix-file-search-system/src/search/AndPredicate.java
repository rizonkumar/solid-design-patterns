package search;

import java.util.List;

// Implements logical AND operation between multiple predicates
public class AndPredicate implements CompositePredicate {
    // List of predicates that must all match for this predicate to match
    private final List<Predicate> operands;

    // Creates a new AND predicate with the specified predicates
    public AndPredicate(final List<Predicate> operands) {
        this.operands = operands;
    }

    // Checks if the given file matches ALL predicates
    @Override
    public boolean isMatch(final File inputFile) {
        return operands.stream().allMatch(predicate -> predicate.isMatch(inputFile));
    }
}
