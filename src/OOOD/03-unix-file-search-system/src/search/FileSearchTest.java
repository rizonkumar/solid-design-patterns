package search;

import java.util.List;

public class FileSearchTest {
    public static void main(String[] args) {
        testFileSearch();
    }

    public static void testFileSearch() {
        final File root = new File(true, 0, "adam", "root");
        final File a = new File(false, 2000, "adam", "a");
        final File b = new File(false, 3000, "george", "b");

        root.addEntry(a);
        root.addEntry(b);

        final FileSearchCriteria criteria =
                new FileSearchCriteria(
                        new AndPredicate(
                                List.of(
                                        new SimplePredicate<>(
                                                FileAttribute.IS_DIRECTORY,
                                                new EqualsOperator<>(),
                                                false),
                                        new SimplePredicate<>(
                                                FileAttribute.OWNER,
                                                new RegexMatchOperator<>(),
                                                "ge.*"))));

        final FileSearch fileSearch = new FileSearch();
        final List<File> result = fileSearch.search(root, criteria);

        if (result.size() == 1 && "b".equals(result.get(0).getFilename())) {
            System.out.println("Test Passed: Found file 'b' correctly!");
        } else {
            System.out.println("Test Failed!");
        }
    }
}
