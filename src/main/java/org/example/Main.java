package org.example;

import org.example.Paneles.ProductoPanel;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
           ProductoPanel app = new ProductoPanel();
           app.setVisible(true);
       });
    }
}