package search;

import java.util.List;

public class OrPredicate implements CompositePredicate {
    private final List<Predicate> operands;

    public OrPredicate(final List<Predicate> operands) {
        this.operands = operands;
    }

    @Override
    public boolean isMatch(final File inputFile) {
        return operands.stream().anyMatch(predicate -> predicate.isMatch(inputFile));
    }
}
