package instagramFeedRankingSystem;

import java.util.*;

public class FeedGenerator {

    private final PostScorer postScorer;

    public FeedGenerator(PostScorer postScorer) {
        this.postScorer = postScorer;
    }

    public List<Post> generateFeed(String userId, List<Post> posts, int k, long currentTimeMillis) {

        PriorityQueue<ScoredPost> minHeap = new PriorityQueue<>(Comparator.comparingDouble(sp -> sp.score));

        for (Post post : posts) {
            double score = postScorer.score(post, userId, currentTimeMillis);

            if (minHeap.size() < k) {
                minHeap.offer(new ScoredPost(post, score));
            } else if (score > minHeap.peek().score) {
                minHeap.poll();
                minHeap.offer(new ScoredPost(post, score));
            }
        }

        List<ScoredPost> topPosts = new ArrayList<>(minHeap);
        topPosts.sort((a, b) -> Double.compare(b.score, a.score)); // descending

        List<Post> result = new ArrayList<>();
        for (ScoredPost sp : topPosts) {
            result.add(sp.post);
        }
        return result;
    }

    private static class ScoredPost {
        Post post;
        double score;

        public ScoredPost(Post post, double score) {
            this.post = post;
            this.score = score;
        }
    }
}
