package com.github.olzhas27;

import java.util.ArrayList;
import java.util.List;

public class ItemStorage {
    private static ItemStorage instance = new ItemStorage();

    public final List<Item> items = new ArrayList<>();

    private ItemStorage() {
        Object someContent = new Object();
        add(new Item(30, 40));
        add(new Item(50, 30));
        add(new Item(60, 70));
    }

    public static ItemStorage getInstance() {
        return instance;
    }

    public void add(Item item) {
        items.add(item);
    }

    public List<Item> list() {
        return items;
    }
}
