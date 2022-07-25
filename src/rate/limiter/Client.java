package rate.limiter;

public class Client {

    public static final Long DEFAULT_TIME = 1000L;
    public static final int DEFAULT_HIT = 100;

    private String id;
    private long timeLimit;
    private int hitLimit;

    public Client(String id) {
        this.id = id;
        this.timeLimit = DEFAULT_TIME;
        this.hitLimit = DEFAULT_HIT;
    }

    public Client(String id, long timeLimit, int hitLimit) {
        this.id = id;
        this.timeLimit = timeLimit;
        this.hitLimit = hitLimit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getHitLimit() {
        return hitLimit;
    }

    public void setHitLimit(int hitLimit) {
        this.hitLimit = hitLimit;
    }
}
