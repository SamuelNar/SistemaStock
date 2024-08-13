package org.example;

import org.example.Paneles.ProductoPanel;
import javax.swing.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
           ProductoPanel app = new ProductoPanel();
           app.setVisible(true);
       });
    }
}