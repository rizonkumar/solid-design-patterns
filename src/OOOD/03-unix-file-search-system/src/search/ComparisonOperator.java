package search;

public interface ComparisonOperator<T> {
    boolean isMatch(final T attributeValue, final T expectedValue);
}
