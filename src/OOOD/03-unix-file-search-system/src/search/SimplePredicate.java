package search;

public class SimplePredicate<T> implements Predicate {
    private final FileAttribute attributeName;
    private final ComparisonOperator<T> operator;
    T expectedValue;

    public SimplePredicate(
            final FileAttribute attributeName,
            final ComparisonOperator<T> operator,
            final T expectedValue) {
        this.attributeName = attributeName;
        this.operator = operator;
        this.expectedValue = expectedValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isMatch(final File inputFile) {
        Object actualValue = inputFile.extract(attributeName);
        if (expectedValue.getClass().isInstance(actualValue)) {
            return operator.isMatch((T) actualValue, expectedValue);
        } else {
            return false;
        }
    }
}
