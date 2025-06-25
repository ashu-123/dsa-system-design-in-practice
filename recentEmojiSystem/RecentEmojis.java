package recentEmojiSystem;

import java.util.*;

/**
 * The class showcasing the inner workings of a Recent Emoji tracker feature present in most smartphone keyboards.
 */
public class RecentEmojis {
    private static final int MAX_RECENT_EMOJIS = 10;
    private LinkedHashSet<String> emojiSet;

    public RecentEmojis() {
        emojiSet = new LinkedHashSet<>(MAX_RECENT_EMOJIS, 0.75f);
    }

    /**
     * Updates the order of the recently used emojis whenever a user chooses an emoji from the options available
     * @param emoji the emoji chosen by the user
     */
    public void useEmoji(String emoji) {

        emojiSet.add(emoji);

        if (emojiSet.size() > MAX_RECENT_EMOJIS) {
            Iterator<String> iterator = emojiSet.iterator();
            iterator.next();  // Remove the first element (least recently used)
            iterator.remove();
        }
    }

    /**
     * Provides a list of emojis used in order of recently used i.e. from Most Recent to Least Recent
     * @return List of recently used emojis
     */
    public List<String> getRecentEmojis() {
        return new ArrayList<>(emojiSet);
    }

    public static void main(String[] args) {
        RecentEmojis recentEmojis = new RecentEmojis();

        recentEmojis.useEmoji("😊");
        recentEmojis.useEmoji("😂");
        recentEmojis.useEmoji("😍");
        recentEmojis.useEmoji("😎");

        System.out.println("Recent emojis: " + recentEmojis.getRecentEmojis());
    }
}
