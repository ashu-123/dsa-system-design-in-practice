package instagramFeedRankingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestDriver {
    public static void main(String[] args) {
        String viewerId = "u1";
        long now = System.currentTimeMillis();

        InteractionGraph graph = new InteractionGraph();
        graph.recordInteraction("u1", "u2");
        graph.recordInteraction("u1", "u2");
        graph.recordInteraction("u1", "u3");

        Set<String> celebs = Set.of("u4");

        List<Post> posts = new ArrayList<>();

        Post post1 = new Post();
        post1.setPostId("p1");
        post1.setAuthorId("u2");
        post1.setTimestamp(now - 1000);
        post1.setLikesCount(50);
        post1.setCommentsCount(20);
        post1.setFlagged(false);

        Post post2 = new Post();
        post2.setPostId("p2");
        post2.setAuthorId("u3");
        post2.setTimestamp(now - 2 * 60 * 60 * 1000);
        post2.setLikesCount(10);
        post2.setCommentsCount(5);
        post2.setFlagged(false);
        post2.setSeenByUsers(Set.of("u1"));

        Post post3 = new Post();
        post3.setPostId("p3");
        post3.setAuthorId("u4");
        post3.setTimestamp(now - 3000);
        post3.setLikesCount(100);
        post3.setCommentsCount(50);
        post3.setFlagged(false);

        Post post4 = new Post();
        post4.setPostId("p4");
        post4.setAuthorId("u5");
        post4.setTimestamp(now - 70 * 60 * 60 * 1000);
        post4.setLikesCount(5);
        post4.setCommentsCount(2);
        post4.setFlagged(false);

        Post post5 = new Post();
        post5.setPostId("p5");
        post5.setAuthorId("u6");
        post5.setTimestamp(now - 2000);
        post5.setLikesCount(0);
        post5.setCommentsCount(0);
        post5.setFlagged(true);

        posts.addAll(List.of(post1, post2, post3, post4, post5));

        PostScorer scorer = new PostScorer(graph, celebs);
        FeedGenerator generator = new FeedGenerator(scorer);

        List<Post> feed = generator.generateFeed(viewerId, posts, 3, now);

        System.out.println("Top Posts for user u1:");
        for (Post p : feed) {
            System.out.println("PostID: " + p.getPostId() + ", Author: " + p.getAuthorId());
        }
    }
}