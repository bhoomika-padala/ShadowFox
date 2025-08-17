# Console-Based Calculator in Java

## Overview
This project is part of the ShadowFox Java Development Internship - Level 1 task.
This Java console application provides a multifunctional calculator capable of performing:

- **Arithmetic Operations:** Addition, Subtraction, Multiplication, Division, Modulo  
- **Scientific Operations:** Power, Square Root, Nth Root, Factorial, Percentage calculation  
- **Unit Conversions:**  
  - Temperature conversions between Celsius, Fahrenheit, and Kelvin  
  - Currency conversions between Indian Rupees (INR), US Dollars (USD), and Euros (EUR)  

The application uses a menu-driven interface and validates user inputs for robustness.

## Features
  
- Input validation with exceptions handling wrong or invalid input  
- Proper handling of edge cases (e.g., division by zero, roots of negative numbers)  
- Results formatted to 2 decimal places where applicable  
- Continuous menu loops allow multiple operations without restarting the program  

## Requirements

- Java JDK 8 or higher  
- Console or terminal environment  

## How to Run

1. Clone or download the repository  
2. Compile the `Calculator.java` file:

   ```bash
   javac pro/Calculator.java
3. Follow the on-screen menu prompts to select operations and input values.

## Code Structure

- `arithmetic(Scanner sc)`: Basic arithmetic menu and operations  
- `scientific(Scanner sc)`: Scientific calculations menu  
- `conversions(Scanner sc)`: Handles temperature and currency conversions  
- Separate methods for each operation ensure modularity and clarity  

## Notes

- Currency conversion rates are approximate and static; they may not reflect real-time values.  
- Factorial input supports non-negative integers only.  
- Floating-point division is carefully handled (e.g., `5.0/9.0`) to avoid integer division errors.
- The application is designed to be user-friendly with clear prompts and error messages.
- This project is part of the ShadowFox Java Development Internship Level 1 task.
