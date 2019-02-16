package com.github.olzhas27;



public class Item {
    private int weight; // - продолжительность в секундах
    private long amount; // - стоимость одного показа

    public Item(int weight, long amount) {
        this.weight = weight;
        this.amount = amount;
    }

    public int getWeight() {
        return weight;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("Item { weight : %d, amount: %d }", weight, amount);
    }
}
