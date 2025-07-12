package instagramFeedRankingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PostFilter {

    public static List<Post> filterPosts(List<Post> allPosts, String currentUserId, Set<String> blockedUsers, long currentTimeMillis) {
        List<Post> result = new ArrayList<>();
        long maxAgeMillis = 48L * 60 * 60 * 1000; // 48 hours

        for (Post post : allPosts) {
            boolean recent = (currentTimeMillis - post.getTimestamp()) <= maxAgeMillis;
            boolean notBlocked = !blockedUsers.contains(post.getAuthorId());
            boolean notSeen = !post.getSeenByUsers().contains(currentUserId);
            boolean clean = !post.isFlagged();

            if (recent && notBlocked && notSeen && clean) {
                result.add(post);
            }
        }

        return result;
    }
}
