package RecommendationSystem;
import java.util.*;

public class RecommendationSystem {

    public static void main(String[] args) {

        // User ratings data (User -> (Product -> Rating))
        Map<String, Map<String, Integer>> data = new HashMap<>();

        data.put("User1", Map.of("Mobile", 5, "Laptop", 3));
        data.put("User2", Map.of("Mobile", 4, "Headphones", 5));
        data.put("User3", Map.of("Laptop", 4, "Headphones", 4));

        String targetUser = "User1";

        // Store recommended products
        Map<String, Integer> recommendations = new HashMap<>();

        for (String user : data.keySet()) {
            if (!user.equals(targetUser)) {

                for (String product : data.get(user).keySet()) {

                    // Recommend only if target user hasn't rated it
                    if (!data.get(targetUser).containsKey(product)) {
                        recommendations.put(
                                product,
                                data.get(user).get(product)
                        );
                    }
                }
            }
        }

        System.out.println("Recommended products for " + targetUser + ":");

        for (String product : recommendations.keySet()) {
            System.out.println("- " + product +
                    " (Rating: " + recommendations.get(product) + ")");
        }
    }
}
