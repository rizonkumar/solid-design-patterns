package locker;

import java.math.BigDecimal;

public enum LockerSize {
    // Small locker with 10x10x10 dimensions and $5 daily charge
    SMALL(
            "Small",
            new BigDecimal("5.00"),
            new BigDecimal("10.00"),
            new BigDecimal("10.00"),
            new BigDecimal("10.00")),
    // Medium locker with 20x20x20 dimensions and $10 daily charge
    MEDIUM(
            "Medium",
            new BigDecimal("10.00"),
            new BigDecimal("20.00"),
            new BigDecimal("20.00"),
            new BigDecimal("20.00")),
    // Large locker with 30x30x30 dimensions and $15 daily charge
    LARGE(
            "Large",
            new BigDecimal("15.00"),
            new BigDecimal("30.00"),
            new BigDecimal("30.00"),
            new BigDecimal("30.00")),
    XLARGE(
            "Extra Large",
            new BigDecimal("20.00"),
            new BigDecimal("40.00"),
            new BigDecimal("40.00"),
            new BigDecimal("40.00"));

    // Name of the locker size
    final String sizeName;
    // Daily charge for using this size locker
    public final BigDecimal dailyCharge;
    // Width of the locker in inches
    final BigDecimal width;
    // Height of the locker in inches
    final BigDecimal height;
    // Depth of the locker in inches
    final BigDecimal depth;

    // Creates a new locker size with specified dimensions and charges
    LockerSize(
            String sizeName,
            BigDecimal dailyCharge,
            BigDecimal width,
            BigDecimal height,
            BigDecimal depth) {
        this.sizeName = sizeName;
        this.dailyCharge = dailyCharge;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public BigDecimal getDepth() {
        return depth;
    }
}
