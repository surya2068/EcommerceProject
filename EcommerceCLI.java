package main;

import model.Item;
import service.CartService;
import service.CheckoutService;
import data.DataLoader;

import java.util.*;

public class EcommerceCLI{

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Map<Integer, Item> products =
                DataLoader.loadProducts("products.csv");
        Map<String, Double> coupons =
                DataLoader.loadCoupons("coupons.csv");

        CartService cartService = new CartService();

        while (true) {
            System.out.println("""
                    1.View Products
                    2.Add to Cart
                    3.View Cart
                    4.Checkout
                    5.Exit
                    """);

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> products.values().forEach(p ->
                        System.out.println(p.id + " | " +
                                p.name + " | ₹" + p.price));

                case 2 -> {
                    System.out.print("Product ID: ");
                    int id = sc.nextInt();
                    System.out.print("Qty: ");
                    int qty = sc.nextInt();
                    cartService.addToCart(products.get(id), qty);
                }

                case 3 -> cartService.viewCart();

                case 4 -> {
                    CheckoutService.checkout(
                            cartService.getCart(), coupons);
                    cartService.clearCart();
                }

                case 5 -> {
                    System.out.print("Are you sure you want to exit? (Y/N): ");
                    String ans = sc.next();

                    if (ans.equalsIgnoreCase("Y")) {
                        System.out.println("Thank you! Exiting...");
                        System.exit(0);
                    } else {
                        System.out.println("Exit cancelled.");
                    }
                }
                
            }
        }
    }
}
