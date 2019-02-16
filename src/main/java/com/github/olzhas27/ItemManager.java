package com.github.olzhas27;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ItemManager {
    int backPackWeight;
    final ItemStorage storage = ItemStorage.getInstance();
    private List<Item> bestItems;
    private long bestAmount;
    private int bestWeight = Integer.MAX_VALUE;

    public ItemManager(int backPackWeight) {
        this.backPackWeight = backPackWeight;
    }

    public void processItems() {
        if (storage.items.isEmpty()) {
            throw new NoItemException();
        }
        List<Item> items = storage.list();
        makeAllItems(items);
        bestItems.sort(Comparator.comparingLong(Item::getAmount).thenComparingInt(Item::getWeight).reversed());
        bestItems.forEach(item -> {
            System.out.println(item.toString());
        });
    }


    private int calcWeight(List<Item> items) {
        return items.stream().mapToInt(Item::getWeight).sum();
    }

    private long calcAmount(List<Item> items) {
        return items.stream().mapToLong(Item::getAmount).sum();
    }

    private void checkSet(List<Item> items) {
        int newWeight = calcWeight(items);
        long newAmount = calcAmount(items);

        if (bestItems == null && newWeight <= backPackWeight) {
            bestItems = items;
            bestAmount = newAmount;
            bestWeight = newWeight;

        } else {
            if (newWeight <= backPackWeight) {

                if (newAmount > bestAmount) {
                    bestItems = items;
                    bestAmount = newAmount;
                    bestWeight = newWeight;
                } else {
                    if (newAmount == bestAmount) {

                        if (newWeight > bestWeight) {
                            bestItems = items;
                            bestAmount = newAmount;
                            bestWeight = newWeight;
                        }

                        if (newWeight == bestWeight && items.size() < bestItems.size()) {
                            bestItems = items;
                            bestAmount = newAmount;
                            bestWeight = newWeight;
                        }
                    }
                }
            }

        }
    }

    private void makeAllItems(List<Item> items) {
        if (items.size() > 0) {
            checkSet(items);
        }
        for (int i = 0; i < items.size(); i++) {
            List<Item> newItems = new ArrayList<>(items);
            newItems.remove(i);
            makeAllItems(newItems);
        }
    }
}
