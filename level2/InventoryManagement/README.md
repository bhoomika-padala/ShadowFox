
#  Inventory Management System using GUI

A simple desktop application built with Java Swing for basic inventory management. It allows users to add, view, and delete products, as well as perform simple inventory analysis like calculating total stock value and filtering by quantity.

This project uses basic Java arrays to manage the inventory data, demonstrating fundamental programming concepts.

## Features ✨

  * **Add Products:** Add new products with a name, unique barcode, price, and quantity.
  * **Delete Products:** Select and remove products from the inventory list.
  * **View Inventory:** All products are displayed in a clear, tabular format.
  * **Calculate Total Stock Value:** Instantly calculate the total monetary value of all items in stock (`Price` × `Quantity`).
  * **Filter by Quantity:** Display only the products that have a quantity less than a specified number.
  * **Reset View:** Clear any active filters to show the complete inventory list.
  * **Input Validation:** The barcode field is restricted to a maximum of 14 digits to ensure data integrity.

## How to Run 🚀

### Prerequisites

You need to have the **Java Development Kit (JDK)** version 8 or higher installed on your system.

### Steps


1.  **Compile**
    Open a terminal or command prompt, navigate to `YourProjectFolder` , and run the following command:

    ```bash
    javac InventoryManagementSystem.java
    ```

2.  **Run**
    After successful compilation, run the application from the same directory (`YourProjectFolder`) with this command:

    ```bash
    java InventoryManagementSystem
    ```

The application window should now appear on your screen.
