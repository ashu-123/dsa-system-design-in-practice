package instagramFeedRankingSystem;

import java.util.HashMap;
import java.util.Map;

public class InteractionGraph {

    private final Map<String, Map<String, Integer>> interactionMap = new HashMap<>();

    public void recordInteraction(String fromUser, String toUser) {
        interactionMap
            .computeIfAbsent(fromUser, k -> new HashMap<>())
            .merge(toUser, 1, Integer::sum);
    }

    public int getInteractionScore(String fromUser, String toUser) {
        return interactionMap.getOrDefault(fromUser, Map.of())
                             .getOrDefault(toUser, 0);
    }
}
