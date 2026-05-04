package search;

// Base interface for all comparison operations in the file search system
public interface ComparisonOperator<T> {
    boolean isMatch(final T attributeValue, final T expectedValue);
}
