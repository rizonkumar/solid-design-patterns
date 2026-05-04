package search;

// Wrapper class that encapsulates a search condition for file matching
public class FileSearchCriteria {
    // The predicate that defines what makes a file match
    private final Predicate predicate;

    // Constructor that takes a predicate defining the criteria
    public FileSearchCriteria(final Predicate predicate) {
        this.predicate = predicate;
    }

    // Checks if the given file matches the search criteria
    public boolean isMatch(final File inputFile) {
        return predicate.isMatch(inputFile);
    }
}
