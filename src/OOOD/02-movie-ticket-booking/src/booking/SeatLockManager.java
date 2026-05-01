package booking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * PESSIMISTIC LOCKING IMPLEMENTATION
 * 
 * Pessimistic locking assumes that conflicts are highly likely to happen. 
 * Therefore, it prevents conflicts by actively "locking" a resource (a seat) 
 * as soon as a user selects it, preventing anyone else from even attempting 
 * to book it until the lock expires or the user completes their purchase.
 * 
 * Pros: Excellent user experience because once a user clicks a seat, they are guaranteed to get it.
 * Cons: Higher overhead and complexity. Users can "hog" seats and abandon their carts, 
 * which is why an expiration timer is absolutely required.
 */
public class SeatLockManager {
    private final Map<String, SeatLock> lockedSeats = new ConcurrentHashMap<>();
    private final Duration lockDuration;

    public SeatLockManager(Duration lockDuration) {
        this.lockDuration = lockDuration;
    }

    /**
     * Attempts to acquire a pessimistic lock on a seat for a specific user.
     */
    public synchronized boolean lockSeat(Screening screening, Seat seat, String userId) {
        String lockKey = generateLockKey(screening, seat);

        // Clean up any stale locks before checking availability
        cleanupLockIfExpired(lockKey);
        
        // If the seat is already locked by someone else, deny the request
        if (isLocked(screening, seat)) {
            return false;
        }

        // Grant the lock to the user for the specified duration (e.g., 5 minutes)
        SeatLock lock = new SeatLock(userId, LocalDateTime.now().plus(lockDuration));
        lockedSeats.put(lockKey, lock);
        return true;
    }

    public synchronized boolean isLocked(Screening screening, Seat seat) {
        String lockKey = generateLockKey(screening, seat);
        cleanupLockIfExpired(lockKey);
        return lockedSeats.containsKey(lockKey);
    }

    private void cleanupLockIfExpired(String lockKey) {
        SeatLock lock = lockedSeats.get(lockKey);
        if (lock != null && lock.isExpired()) {
            lockedSeats.remove(lockKey);
        }
    }

    private String generateLockKey(Screening screening, Seat seat) {
        return screening.getId() + "-" + seat.getSeatNumber();
    }

    private static class SeatLock {
        private final String userId;
        private final LocalDateTime expirationTime;

        public SeatLock(String userId, LocalDateTime expirationTime) {
            this.userId = userId;
            this.expirationTime = expirationTime;
        }

        public boolean isExpired() {
            return LocalDateTime.now().isAfter(expirationTime);
        }

        public String getUserId() {
            return userId;
        }
    }
}
