package vending;

public class Rack {
    private final String rackCode;
    private final Product product;
    private int count;

    public Rack(final String rackCode, final Product product, final int count) {
        this.rackCode = rackCode;
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public int getProductCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRackCode() {
        return rackCode;
    }
}