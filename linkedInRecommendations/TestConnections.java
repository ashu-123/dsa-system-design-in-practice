package linkedInRecommendations;

import java.util.*;

/**
 * This is the main driver class to test the implemented logic
 */
public class TestConnections {

    public static void main(String[] args) {

        Map<User, Set<User>> connections = new HashMap<>();
        User userA = new User();
        userA.setId(1).setName("A");
        userA.getSkills().add("Java");
        User userB = new User().setId(2).setName("B");
        userB.getSkills().add("SQL");

        Set<User> connectionsOfA = connections.getOrDefault(userA, new HashSet<>());
        connectionsOfA.add(userB);
        Set<User> connectionsOfB = connections.getOrDefault(userB, new HashSet<User>());
        connectionsOfB.add(userA);
        System.out.println(recommendUsers(userA, 20, connections));
    }

    /**
     * The method that computes a list of recommendations for a given user
     *
     * @param source - user for which the list of recommendations
     * @param k - number of recommendations required
     * @param connectionsGraph - the adjacency list kind of representation to showcase connections within a network of users
     * @return list of top k recommendations
     */
    private static List<RecommendedUser> recommendUsers(
            User source,
            int k,
            Map<User, Set<User>> connectionsGraph) {

        Set<User> visited = new HashSet<>();
        Queue<User> queue = new LinkedList<>();
        queue.offer(source);
        visited.add(source);

        int level = 0;
        PriorityQueue<RecommendedUser> heap = new PriorityQueue<>(k);

        while (!queue.isEmpty() && level < 3) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                User current = queue.poll();
                Set<User> connections = connectionsGraph.getOrDefault(current, new HashSet<>());

                for (User neighbor : connections) {
                    if (visited.contains(neighbor)) continue;

                    visited.add(neighbor);
                    queue.offer(neighbor);

                    // Compute Jaccard Similarity
                    Set<String> common = new HashSet<>(source.getSkills());
                    common.retainAll(neighbor.getSkills());

                    Set<String> all = new HashSet<>(source.getSkills());
                    all.addAll(neighbor.getSkills());

                    double similarity = all.isEmpty() ? 0.0 : ((double) common.size() / all.size());

                    // Only recommend if similarity > 0
                    if (similarity > 0) {
                        RecommendedUser ru = new RecommendedUser(neighbor, similarity, level + 1);

                        if (heap.size() < k) {
                            heap.offer(ru);
                        } else if (heap.peek().compareTo(ru) < 0) {
                            heap.poll();
                            heap.offer(ru);
                        }
                    }
                }
            }
            level++;
        }

        // Convert heap to list and sort descending
        List<RecommendedUser> result = new ArrayList<>(heap);
        result.sort((a, b) -> Double.compare(b.getSimilarityScore(), a.getSimilarityScore()));
        return result;
    }

}
