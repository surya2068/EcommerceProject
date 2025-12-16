package model;

public class Item {
    public int id;
    public String name;
    public String category;
    public double price;

    public Item(int id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
