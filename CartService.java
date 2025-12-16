package service;

import model.CartItem;
import model.Item;

import java.util.*;

public class CartService {

    private List<CartItem> cart = new ArrayList<>();

    public void addToCart(Item item, int qty) {
        cart.add(new CartItem(item, qty));
        System.out.println("Added to cart");
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        cart.forEach(c ->
                System.out.println(c.item.name + " x" + c.quantity +
                        " = ₹" + c.getTotal()));

        double subtotal = cart.stream()
                .mapToDouble(CartItem::getTotal).sum();

        System.out.println("Subtotal: ₹" + subtotal);

        Map<String, Double> categoryTotals = new HashMap<>();
        for (CartItem c : cart) {
            categoryTotals.put(c.item.category,
                    categoryTotals.getOrDefault(c.item.category, 0.0)
                            + c.getTotal());
        }

        System.out.println("Category-wise Totals:");
        categoryTotals.forEach((k, v) ->
                System.out.println(k + ": ₹" + v));
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }
}
