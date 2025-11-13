# 🍕 The Pizzeria Shop Sale System

A Java OOP-based point of sale application for PIZZA-licious, a custom pizza shop. This application handles pizza orders, drinks, garlic knots, and generates receipts with accurate pricing.

## 📋 Project Overview

This capstone project demonstrates advanced Java Object-Oriented Programming concepts, including:
- Inheritance and Polymorphism
- Encapsulation and Abstraction
- Interface implementation
- Class relationships and design patterns
- File I/O operations

## 🎯 Features

### Order Management
- **Custom Pizza Builder**: Create pizzas with size, crust, sauce, and toppings
- **Signature Pizzas**: Pre-configured specialty pizzas (Margherita, Veggie, Arslan's Special)
- **Drinks & Sides**: Add drinks and garlic knots to orders
- **Order Validation**: Ensures orders meet business rules (pizza or side/drink required)

### Pricing System
- **Size-based Pricing**: Personal (8"), Medium (12"), Large (16")
- **Premium Toppings**: Meats and cheeses with accurate pricing
- **Extra Toppings**: Additional costs for premium extras
- **Stuffed Crust**: Optional $2.00 upgrade

### Receipt Generation
- **Detailed Receipts**: Itemized pricing breakdown
- **File Storage**: Automatically saves receipts to `receipts/` folder
- **Timestamp Naming**: Files named using `yyyyMMdd-HHmmss.txt` format

## 🏗️ Class Structure

### Core Models
- **`Order`**: Manages collection of order items and calculates totals
- **`OrderItem`**: Abstract base class for all orderable items
- **`Pizza`**: Handles pizza customization and pricing logic
- **`Topping`**: Manages topping properties and premium status

### Specialized Classes
- **`SignaturePizza`**: Base class for pre-configured pizzas
- **`MargheritaPizza`, `VeggiePizza`, `ArslansPizza`**: Specific signature pizzas
- **`Drink`**: Handles drink orders with size-based pricing
- **`GarlicKnots`**: Manages side orders

### Utility Classes
- **`InputHandler`**: Handles user input with validation
- **`ReceiptManager`**: Manages receipt generation and file storage
- **`Orderable`**: Interface defining contract for orderable items

## 💰 Pricing Structure

### Pizza Bases
- Personal (8"): $8.50
- Medium (12"): $12.00  
- Large (16"): $16.50

### Premium Toppings
**Meats** (Pepperoni, Sausage, Ham, Bacon, Chicken, Meatball):
- Personal: $1.00 | Extra: +$0.50
- Medium: $2.00 | Extra: +$1.00
- Large: $3.00 | Extra: +$1.50

**Cheeses** (Mozzarella, Parmesan, Ricotta, Goat Cheese, Buffalo):
- Personal: $0.75 | Extra: +$0.30
- Medium: $1.50 | Extra: +$0.60
- Large: $2.25 | Extra: +$0.90

### Other Items
- Stuffed Crust: $2.00
- Drinks: Small $2.00, Medium $2.50, Large $3.00
- Garlic Knots: $1.50 per order

## 🖥️ Application Flow

1. **Home Screen**: Start new order or exit application
2. **Order Screen**: Add pizzas, drinks, garlic knots, or checkout
3. **Pizza Customization**: 
   - Choose size, crust, sauce
   - Add toppings (premium/regular)
   - Option for stuffed crust
4. **Checkout**: Review order, confirm, and generate receipt

## 📸 Sample Output
