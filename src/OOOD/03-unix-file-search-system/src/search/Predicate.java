package search;

// Base interface for all file search predicates
public interface Predicate {
    // Checks if the given file matches the search condition
    boolean isMatch(final File inputFile);
}
