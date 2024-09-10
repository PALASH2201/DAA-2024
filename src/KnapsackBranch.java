import java.util.*;

class Item implements Comparable<Item> {
    int value;
    int weight;
    double ratio;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
        this.ratio = (double) value / weight;
    }

    @Override
    public int compareTo(Item other) {
        return Double.compare(other.ratio, this.ratio);
    }
}

public class KnapsackBranch {
    static int maxProfit = Integer.MIN_VALUE;
    static int[] finalResult;

    public static void knapsack(int[] values, int[] weights, int maxWeight) {
        int n = values.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(values[i], weights[i]);
        }
        Arrays.sort(items);

        int[] currentWeight = new int[n];
        int[] currentValue = new int[n];

        branchAndBound(0, 0, 0, items, currentWeight, currentValue, maxWeight);

        System.out.println("Maximum profit: " + maxProfit);
        System.out.println("Items included in knapsack:");
        for (int i = 0; i < finalResult.length; i++) {
            if (finalResult[i] == 1) {
                System.out.println("Item " + (i + 1) + " - Value: " + values[i] + ", Weight: " + weights[i]);
            }
        }
    }

    private static void branchAndBound(int level, int value, int weight, Item[] items,
                                       int[] currentWeight, int[] currentValue, int maxWeight) {
        if (weight <= maxWeight && value > maxProfit) {
            maxProfit = value;
            finalResult = Arrays.copyOf(currentValue, currentValue.length);
        }

        if (level == items.length || weight >= maxWeight) {
            return;
        }

        if (weight + items[level].weight <= maxWeight) {
            currentWeight[level] = 1;
            currentValue[level] = 1;
            branchAndBound(level + 1, value + items[level].value, weight + items[level].weight,
                    items, currentWeight, currentValue, maxWeight);
        }
        currentWeight[level] = 0;
        currentValue[level] = 0;
        branchAndBound(level + 1, value, weight, items, currentWeight, currentValue, maxWeight);
    }

    public static void main(String[] args) {
        int[] values = {20, 50, 60};
        int[] weights = {8, 25, 40};
        int maxWeight = 70;
        System.out.println("Maximum weight of the bag:"+maxWeight);
        System.out.println("Profit:"+Arrays.toString(values));
        System.out.println("Cost:"+Arrays.toString(weights));
        knapsack(values, weights, maxWeight);
    }
}
