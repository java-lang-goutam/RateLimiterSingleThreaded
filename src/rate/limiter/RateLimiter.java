package rate.limiter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class RateLimiter {

    private final Map<String, Client> clientDetails = new HashMap<>();
    private final Map<String, Queue<Long>> clientHitDetails = new HashMap<>();

    public void setLimit(final String clientId, final int hitLimit, final int time, final TimeUnit timeUnit) {
        final Client client = getClient(clientId);
        client.setHitLimit(hitLimit);
        client.setTimeLimit(timeUnit.toMillis(time));
    }

    public boolean isAllowed(String clientId, long timeOfHit) {
        final Client client = getClient(clientId);
        if (clientHitDetails.containsKey(clientId)) {
            final Queue<Long> times = clientHitDetails.get(clientId);
            if (times.size() < client.getHitLimit()) {
                times.add(timeOfHit);
                return true;
            }

            // Check if eligible for cleanup
            while (!times.isEmpty() && (timeOfHit - times.peek() > client.getTimeLimit())) times.poll();

            // Check if reached hit limit
            if (times.size() < client.getHitLimit()) {
                times.add(timeOfHit);
                return true;
            }

        } else {
            // fresh req
            final Queue<Long> times = new LinkedList<>();
            times.offer(timeOfHit);
            clientHitDetails.put(clientId, times);
            return true;
        }

        return false;
    }

    /**
     * Get or create new client
     * @param clientId
     * @return
     */
    private Client getClient(final String clientId) {
        Client client = clientDetails.get(clientId);
        if (client == null) {
            client = new Client(clientId);
            clientDetails.put(clientId, client);
        }
        return client;
    }
}
