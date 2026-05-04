package search;

// A basic predicate that compares a file attribute with an expected value
public class SimplePredicate<T> implements Predicate {
    // The name of the file attribute to check
    private final FileAttribute attributeName;
    // The operator to use for comparison (equals, contains, greater than, etc.)
    private final ComparisonOperator<T> operator;
    // The expected value to compare against
    T expectedValue;

    // Creates a new simple predicate with the specified attribute, operator, and expected value
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
        // Extract the actual value of the attribute from the file
        Object actualValue = inputFile.extract(attributeName);
        // Check if the actual value is of the correct type
        if (expectedValue.getClass().isInstance(actualValue)) {
            // Perform the comparison using the specified operator
            return operator.isMatch((T) actualValue, expectedValue);
        } else {
            return false;
        }
    }
}
