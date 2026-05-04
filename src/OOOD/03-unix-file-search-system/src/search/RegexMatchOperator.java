package search;

import java.util.regex.Pattern;

// Implements regular expression pattern matching for string values
public class RegexMatchOperator<T extends String> implements ComparisonOperator<T> {
    @Override
    public boolean isMatch(final T attributeValue, final T expectedValue) {
        final Pattern p = Pattern.compile(expectedValue);
        return p.matcher(attributeValue).matches();
    }
}
