package service;

import model.CartItem;
import util.InvoiceGenerator;

import java.util.List;
import java.util.Map;

public class CheckoutService {

    public static void checkout(List<CartItem> cart,
                                Map<String, Double> coupons) throws Exception {

        double subtotal = cart.stream()
                .mapToDouble(CartItem::getTotal).sum();

        System.out.print("Enter coupon code (or NONE): ");
        String code = new java.util.Scanner(System.in).next();

        double discountPercent = coupons.getOrDefault(code, 0.0);
        double discount = subtotal * discountPercent / 100;

        double afterDiscount = subtotal - discount;
        double gst = afterDiscount * 0.18;
        double total = afterDiscount + gst;

        InvoiceGenerator.generate(cart, subtotal, discount, gst, total);
    }
}
