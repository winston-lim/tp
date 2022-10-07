package seedu.duke.item;

import seedu.duke.id.IdGenerator;
import seedu.duke.user.User;

public class Item {
    public String name;
    public String id;
    public User owner;
    public double pricePerDay;
    public boolean isAvailable;
    public Category.Categories category;

    public Item(String name, int categoryNumber, double price, User owner) {
        this.id = IdGenerator.generateId();
        this.name = name;
        this.pricePerDay = price;
        this.isAvailable = true;
        this.owner = owner;
        this.category = Category.mapCategory(categoryNumber);
    }

    public void setAsAvailable() {
        isAvailable = true;
    }

    public void setAsNotAvailable() {
        isAvailable = false;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getItemId() {
        return id;
    }

    public String getDescription() {
        return this.name + " loaned for: " + pricePerDay;
    }

    public String getStatus() {
        return (this.isAvailable ? "YES" : "NO");
    }

    public void updatePrice(int newPrice) {
        this.pricePerDay = newPrice;
    }

    public String fileFormat() {
        return null; //add later
    }

    @Override
    public String toString() {
        String itemIcon = "[" + (isAvailable ? "Y" : "N") + "] ";
        String itemName = "Item: " + name + "(ID: " + id + ") ";
        String itemCategory = "Category: " + category.toString() + " ";
        String itemOwner = "Owner: " + owner.getUserId() + " ";
        String itemPrice = "PricePerDay: " + pricePerDay;
        return itemIcon + itemName + itemCategory + itemOwner + itemPrice;
    }

}