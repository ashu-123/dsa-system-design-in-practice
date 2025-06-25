package linkedInRecommendations;

/**
 * This class represents a user recommendation.
 */
public class RecommendedUser implements Comparable<RecommendedUser> {

    private User user;
    private double similarityScore;
    private int degreeLevel;

    public RecommendedUser(User user, double similarityScore, int degreeLevel) {
        this.user = user;
        this.similarityScore = similarityScore;
        this.degreeLevel = degreeLevel;
    }

    public User getUser() {
        return user;
    }

    public double getSimilarityScore() {
        return similarityScore;
    }

    public int getDegreeLevel() {
        return degreeLevel;
    }

    @Override
    public int compareTo(RecommendedUser other) {
        int scoreCompare = Double.compare(this.similarityScore, other.similarityScore);
        if (scoreCompare != 0) return scoreCompare;

        // In case of tie, prefer users with lower degree level (closer)
        return Integer.compare(other.degreeLevel, this.degreeLevel); // higher degree level == worse
    }
}

