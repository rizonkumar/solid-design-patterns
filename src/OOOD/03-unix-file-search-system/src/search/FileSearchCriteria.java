package search;

public class FileSearchCriteria {
    private final Predicate predicate;

    public FileSearchCriteria(final Predicate predicate) {
        this.predicate = predicate;
    }

    public boolean isMatch(final File inputFile) {
        return predicate.isMatch(inputFile);
    }
}
