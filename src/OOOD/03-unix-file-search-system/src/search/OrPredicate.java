package search;

import java.util.List;

// Implements logical OR operation between multiple predicates
public class OrPredicate implements CompositePredicate {
    // List of predicates, at least one of which must match
    private final List<Predicate> operands;

    // Creates a new OR predicate with the specified predicates
    public OrPredicate(final List<Predicate> operands) {
        this.operands = operands;
    }

    @Override
    public boolean isMatch(final File inputFile) {
        return operands.stream().anyMatch(predicate -> predicate.isMatch(inputFile));
    }
}
