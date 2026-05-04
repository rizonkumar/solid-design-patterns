package search;

import java.util.Objects;

// Implements exact equality comparison between values
public class EqualsOperator<T> implements ComparisonOperator<T> {
    @Override
    public boolean isMatch(final T attributeValue, final T expectedValue) {
        return Objects.equals(attributeValue, expectedValue);
    }
}
