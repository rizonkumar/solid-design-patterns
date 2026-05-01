package booking;

import java.math.BigDecimal;

public class Ticket {
    private final Screening screening;
    private final Seat seat;
    private final BigDecimal price;

    public Ticket(Screening screening, Seat seat, BigDecimal price) {
        this.screening = screening;
        this.seat = seat;
        this.price = price;
    }

    public Screening getScreening() { return screening; }
    public Seat getSeat() { return seat; }
    public BigDecimal getPrice() { return price; }
}
