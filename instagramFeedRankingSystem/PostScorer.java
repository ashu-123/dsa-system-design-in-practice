package instagramFeedRankingSystem;

import java.util.Set;

public class PostScorer {

    private final InteractionGraph interactionGraph;
    private final Set<String> celebrityIds;

    private static final double RECENCY_WEIGHT = 0.4;
    private static final double ENGAGEMENT_WEIGHT = 0.3;
    private static final double RELATIONSHIP_WEIGHT = 0.2;
    private static final double CELEBRITY_WEIGHT = 0.1;

    public PostScorer(InteractionGraph interactionGraph, Set<String> celebrityIds) {
        this.interactionGraph = interactionGraph;
        this.celebrityIds = celebrityIds;
    }

    public double score(Post post, String viewerId, long currentTimeMillis) {
        double recencyScore = computeRecencyScore(post, currentTimeMillis);
        double engagementScore = computeEngagementScore(post);
        double relationshipScore = computeRelationshipScore(viewerId, post.getAuthorId());
        double celebrityBoost = isCelebrity(post.getAuthorId()) ? 1.0 : 0.0;

        return RECENCY_WEIGHT * recencyScore
             + ENGAGEMENT_WEIGHT * engagementScore
             + RELATIONSHIP_WEIGHT * relationshipScore
             + CELEBRITY_WEIGHT * celebrityBoost;
    }

    private double computeRecencyScore(Post post, long currentTimeMillis) {
        long ageMillis = currentTimeMillis - post.getTimestamp();
        long maxAgeMillis = 48L * 60 * 60 * 1000; // 48 hours
        return 1.0 - Math.min(1.0, (double) ageMillis / maxAgeMillis);
    }

    private double computeEngagementScore(Post post) {
        int engagement = post.getLikesCount() + post.getCommentsCount();
        int max = 1000;
        return Math.min(1.0, (double) engagement / max);
    }

    private double computeRelationshipScore(String viewerId, String authorId) {
        int interactions = interactionGraph.getInteractionScore(viewerId, authorId);
        int max = 50;
        return Math.min(1.0, (double) interactions / max);
    }

    private boolean isCelebrity(String userId) {
        return celebrityIds.contains(userId);
    }
}
