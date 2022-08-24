package com.company.model;

public class InvoiceLines {
    public String itemName;
    public int itemPrice;
    public int count;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int totalSumOfItem() {

        return itemPrice * count;

    }
}
