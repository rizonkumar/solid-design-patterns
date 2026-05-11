package grocery;

public class ItemBasedCriteria implements DiscountCriteria {
    private final String barcode;
    
    public ItemBasedCriteria(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public boolean isApplicable(Item item) {
        return barcode.equals(item.getBarcode());
    }
}
