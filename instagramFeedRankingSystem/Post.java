package instagramFeedRankingSystem;

import java.util.HashSet;
import java.util.Set;

public class Post {
    private String postId;
    private String authorId;
    private long timestamp; // epoch millis
    private int likesCount;
    private int commentsCount;
    private boolean isFlagged;
    private Set<String> seenByUsers = new HashSet<>();

    public String getPostId() {
        return postId;
    }

    public Post setPostId(String postId) {
        this.postId = postId;
        return this;
    }

    public String getAuthorId() {
        return authorId;
    }

    public Post setAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Post setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public Post setLikesCount(int likesCount) {
        this.likesCount = likesCount;
        return this;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public Post setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
        return this;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public Post setFlagged(boolean flagged) {
        isFlagged = flagged;
        return this;
    }

    public Set<String> getSeenByUsers() {
        return seenByUsers;
    }

    public Post setSeenByUsers(Set<String> seenByUsers) {
        this.seenByUsers = seenByUsers;
        return this;
    }
}
