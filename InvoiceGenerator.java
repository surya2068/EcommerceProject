package util;

import model.CartItem;
import java.io.*;
import java.util.List;

public class InvoiceGenerator {

    public static void generate(List<CartItem> cart,
                                double subtotal,
                                double discount,
                                double gst,
                                double total) throws Exception {

        String file = "invoice_" + System.currentTimeMillis() + ".txt";
        PrintWriter pw = new PrintWriter(file);

        pw.println("------ INVOICE ------");
        for (CartItem c : cart) {
            pw.println(c.item.name + " x" + c.quantity +
                    " = ₹" + c.getTotal());
        }

        pw.println("Subtotal: ₹" + subtotal);
        pw.println("Discount: ₹" + discount);
        pw.println("GST (18%): ₹" + gst);
        pw.println("Total: ₹" + total);
        pw.println("---------------------");

        pw.close();
        System.out.println("Invoice generated: " + file);
    }
}
