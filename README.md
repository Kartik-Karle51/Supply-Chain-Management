# Supply Chain Management System

This is a Supply Chain Management System built using **Spring Boot**, **MySQL**, and other modern web technologies. The system allows different roles (Admin, Customer, Supplier) to interact with a product order lifecycle. The admin manages customers, suppliers, and products, while customers can place and pay for orders, and suppliers can manage the delivery status.

## Features

### Admin
- **Full control** over customers, suppliers, products, orders, and payments.
- Can **add**, **update**, or **remove** customers, suppliers, and products.
- View all **customers**, **suppliers**, **products**, **orders**, and **payments**.
- Assign new orders to suppliers.


### Customer
- **Place** and **update** orders.
- View supplier details (assigned by the admin).
- Check and browse **all available products**.
- Make **payments** for the orders.
- Check all the orders place by him/her.

### Supplier
- View assigned **orders** from the admin.
- Update order status (e.g., deliver order).
- Check **customer details** for order delivery.

## Entities
The application is designed with six main entities:

- **Order**: Represents customer orders.
- **Product**: Represents products available for customers to order.
- **Customer**: Represents a customer in the system.
- **Supplier**: Represents a supplier who delivers the orders.
- **Payment**: Handles payment information and transactions.
- **AllUsers**: Handles user authentication and security (Spring Security).

## Technologies Used
- **Backend**: Spring Boot
- **Database**: MySQL (MySQL Workbench for DB management)
- **APIs**: Postman for testing APIs
- **Security**: Spring Security for authentication and authorization
- **Mail Service**: For sending notifications via email (MailSender)


