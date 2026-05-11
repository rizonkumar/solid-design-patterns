package grocery;

public class CategoryBasedCriteria implements DiscountCriteria {
    private final String category;
    
    public CategoryBasedCriteria(String category) {
        this.category = category;
    }

    @Override
    public boolean isApplicable(Item item) {
        return category.equalsIgnoreCase(item.getCategory());
    }
}
