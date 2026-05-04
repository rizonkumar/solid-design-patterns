package search;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.List;

// Main class responsible for performing file system searches
public class FileSearch {
    // Performs a recursive search through the file system starting from root
    // Returns a list of files that match the given criteria
    public List<File> search(final File root, final FileSearchCriteria criteria) {
        // List to store matching files
        final List<File> result = new ArrayList<>();
        // Stack to handle recursive traversal without actual recursion
        final ArrayDeque<File> recursionStack = new ArrayDeque<>();
        // Start with the root directory
        recursionStack.add(root);
        // Continue until we've processed all files
        while (!recursionStack.isEmpty()) {
            // Get the next file to process
            File next = recursionStack.pop();
            // Check if the file matches our criteria
            if (criteria.isMatch(next)) {
                result.add(next);
            }
            // Add all directory entries to the stack for processing
            for (File entry : next.getEntries()) {
                recursionStack.push(entry);
            }
        }
        return result;
    }
}
