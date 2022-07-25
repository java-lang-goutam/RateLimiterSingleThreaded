package rate.limiter;

import java.util.concurrent.TimeUnit;

public class RateLimiterDriver {
    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter();
        rateLimiter.setLimit("clientA", 3, 1, TimeUnit.SECONDS);
        rateLimiter.setLimit("clientB", 2, 1, TimeUnit.SECONDS);

        System.out.println(rateLimiter.isAllowed("clientA", 1000));
        System.out.println(rateLimiter.isAllowed("clientA", 1100));
        System.out.println(rateLimiter.isAllowed("clientA", 1100));
        System.out.println(rateLimiter.isAllowed("clientA", 1200));
        System.out.println(rateLimiter.isAllowed("clientA", 1200));
        System.out.println(rateLimiter.isAllowed("clientA", 2200));
        System.out.println(rateLimiter.isAllowed("clientA", 2200));
        System.out.println(rateLimiter.isAllowed("clientA", 2999));
        System.out.println(rateLimiter.isAllowed("clientA", 2999));

        System.out.println(rateLimiter.isAllowed("clientB", 3000));
        System.out.println(rateLimiter.isAllowed("clientB", 3000));
        System.out.println(rateLimiter.isAllowed("clientB", 3000));
    }
}
