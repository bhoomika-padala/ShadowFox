package pro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

class Product {
    private String name;
    private String barcode;
    private double price;
    private int quantity;

    public Product(String name, String barcode, double price, int quantity) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public String getBarcode() { return barcode; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
}

public class InventoryManagementSystem {
    private JFrame frame;
    private JTextField nameField, barcodeField, priceField, quantityField, filterField;
    private JTable table;
    private DefaultTableModel tableModel;

    // --- Data model using basic arrays ---
    private Product[] inventory;
    private int productCount;
    private static final int INITIAL_CAPACITY = 10;

    public InventoryManagementSystem() {
        // --- Initialize the array-based data model ---
        inventory = new Product[INITIAL_CAPACITY];
        productCount = 0;

        frame = new JFrame("Inventory Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        // --- Top Panel for form inputs ---
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        formPanel.add(new JLabel("Product Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Barcode:"));
        barcodeField = new JTextField();
        formPanel.add(barcodeField);

        // --- Add KeyListener for Barcode validation (<=14 digits) ---
        barcodeField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Allow only digits
                if (!Character.isDigit(c)) {
                    e.consume(); // Ignore the key press
                }
                // Limit the length to 14
                if (barcodeField.getText().length() >= 14) {
                    e.consume(); // Ignore the key press
                }
            }
        });

        formPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        formPanel.add(priceField);

        formPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        formPanel.add(quantityField);

        JButton addButton = new JButton("Add Product");
        formPanel.add(addButton);

        JButton deleteButton = new JButton("Delete Selected");
        formPanel.add(deleteButton);

        frame.add(formPanel, BorderLayout.NORTH);

        // --- Table to show products ---
        String[] columns = {"Name", "Barcode", "Price", "Quantity"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // --- Bottom Panel for extra actions ---
        JPanel bottomPanel = new JPanel();

        JButton calcButton = new JButton("Calculate Total Stock Value");
        bottomPanel.add(calcButton);

        bottomPanel.add(new JLabel("Show products with quantity <"));
        filterField = new JTextField(5);
        bottomPanel.add(filterField);

        JButton filterButton = new JButton("Filter");
        bottomPanel.add(filterButton);

        JButton resetButton = new JButton("Reset");
        bottomPanel.add(resetButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // --- Action Listeners ---
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Get the unique barcode from the selected row in the table (view)
                    String barcodeToDelete = (String) table.getValueAt(selectedRow, 1);
                    
                    int indexToDelete = -1;
                    // Find the product in our inventory array (model)
                    for (int i = 0; i < productCount; i++) {
                        if (inventory[i].getBarcode().equals(barcodeToDelete)) {
                            indexToDelete = i;
                            break;
                        }
                    }
                    
                    if (indexToDelete != -1) {
                        // Shift elements to the left to fill the gap in the array
                        for (int i = indexToDelete; i < productCount - 1; i++) {
                            inventory[i] = inventory[i + 1];
                        }
                        inventory[productCount - 1] = null; // Clear the last reference
                        productCount--;
                        updateTable(); // Refresh the table view from the updated model
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Select a row to delete.");
                }
            }
        });

        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotalValue();
            }
        });

        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterByQuantity();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetFilter();
            }
        });

        frame.setVisible(true);
    }

    private void addProduct() {
        String name = nameField.getText().trim();
        String barcode = barcodeField.getText().trim();
        String priceText = priceField.getText().trim();
        String quantityText = quantityField.getText().trim();

        if (name.isEmpty() || barcode.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields must be filled!");
            return;
        }

        // --- Barcode validation for pasted values ---
        if (barcode.length() > 14) {
            JOptionPane.showMessageDialog(frame, "Barcode cannot be more than 14 digits.");
            return;
        }
        for (int i = 0; i < barcode.length(); i++) {
            if (!Character.isDigit(barcode.charAt(i))) {
                JOptionPane.showMessageDialog(frame, "Barcode must contain only digits.");
                return;
            }
        }
        
        // Check for duplicate barcode
        for (int i = 0; i < productCount; i++) {
            if (inventory[i].getBarcode().equals(barcode)) {
                JOptionPane.showMessageDialog(frame, "A product with this barcode already exists.");
                return;
            }
        }

        try {
            double price = Double.parseDouble(priceText);
            int quantity = Integer.parseInt(quantityText);

            if (price < 0 || quantity < 0) {
                JOptionPane.showMessageDialog(frame, "Price and Quantity must be non-negative.");
                return;
            }

            Product newProduct = new Product(name, barcode, price, quantity);

            // --- Add to our array, resizing if necessary ---
            if (productCount == inventory.length) {
                Product[] newInventory = new Product[inventory.length * 2]; // Double the size
                // Copy elements from old array to new array
                for (int i = 0; i < inventory.length; i++) {
                    newInventory[i] = inventory[i];
                }
                inventory = newInventory;
            }

            inventory[productCount] = newProduct;
            productCount++;

            updateTable(); // Refresh the JTable with the new data

            // Clear input fields
            nameField.setText("");
            barcodeField.setText("");
            priceField.setText("");
            quantityField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Price must be a number and Quantity must be an integer.");
        }
    }

   
    private void updateTable() {
        tableModel.setRowCount(0); // Clear existing rows

        for (int i = 0; i < productCount; i++) {
            Product p = inventory[i];
            Object[] row = {p.getName(), p.getBarcode(), p.getPrice(), p.getQuantity()};
            tableModel.addRow(row);
        }
    }

    private void calculateTotalValue() {
        double total = 0;
        
        for (int i = 0; i < productCount; i++) {
            total += inventory[i].getPrice() * inventory[i].getQuantity();
        }
        JOptionPane.showMessageDialog(frame, "Total Stock Value: " + String.format("%.2f", total));
    }

    private void filterByQuantity() {
        String filterText = filterField.getText().trim();
        if (filterText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Enter a number to filter.");
            return;
        }

        try {
            int filterValue = Integer.parseInt(filterText);
            
            tableModel.setRowCount(0); // Clear the table view
            
         
            for (int i = 0; i < productCount; i++) {
                Product p = inventory[i];
                if (p.getQuantity() < filterValue) {
                    Object[] row = {p.getName(), p.getBarcode(), p.getPrice(), p.getQuantity()};
                    tableModel.addRow(row);
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Enter a valid number.");
        }
    }

    private void resetFilter() {
        filterField.setText("");
        updateTable(); 
    }

    public static void main(String[] args) {
        new InventoryManagementSystem();
    }
}