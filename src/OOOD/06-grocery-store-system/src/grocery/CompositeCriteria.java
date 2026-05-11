package grocery;

import java.util.ArrayList;
import java.util.List;

// Composite criteria that combines multiple discount criteria
public class CompositeCriteria implements DiscountCriteria {
    // List of criteria to be combined
    private final List<DiscountCriteria> criteriaList;

    // Creates a new composite criteria with the given list of criteria
    public CompositeCriteria(List<DiscountCriteria> criteriaList) {
        this.criteriaList = new ArrayList<>(criteriaList);
    }

    // Checks if the item satisfies all the criteria in the list
    @Override
    public boolean isApplicable(Item item) {
        return criteriaList.stream().allMatch(criteria -> criteria.isApplicable(item));
    }

    // Adds a new criteria to the composite
    public void addCriteria(DiscountCriteria criteria) {
        criteriaList.add(criteria);
    }

    // Removes a criteria from the composite
    public void removeCriteria(DiscountCriteria criteria) {
        criteriaList.remove(criteria);
    }
}
