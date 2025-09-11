# RasoiRun Backend 🍲

RasoiRun is the backend service for a comprehensive food delivery application, built with **Spring Boot**. It provides a rich set of RESTful APIs for user management, food catalog operations, shopping carts, order processing, and secure payment integration.  

---

## 🚀 Features

- **User Management & Authentication**
  - Secure registration & login with **JWT (JSON Web Tokens)**
  - Role-based access for **User/Admin**

- **Food Catalog Management**
  - Full **CRUD operations** for food items
  - Image upload & management via **Cloudinary**

- **Shopping Cart**
  - Add/Remove items to a user-specific cart

- **Order Processing**
  - Create, manage & delete user orders
  - Admin can update order status

- **Payment Gateway Integration**
  - Seamless payment handling via **Razorpay**

- **Data Persistence**
  - MongoDB for efficient and scalable storage

- **Layered Architecture**
  - Clean separation of **Controllers, Services, Repositories** for maintainability and scalability  

---

## 🛠️ Technologies Used

- **Backend**: Spring Boot  
- **Database**: MongoDB  
- **Security**: Spring Security, JWT  
- **Image Storage**: Cloudinary  
- **Payment Gateway**: Razorpay  
- **Build Tool**: Maven  

---

## 📦 Getting Started

### ✅ Prerequisites
- Java JDK 17+  
- Maven  
- MongoDB (local or cloud-based)  
- Accounts on **Cloudinary** & **Razorpay**  

---

## ⚙️ Setup & Configuration

### 1️⃣ Clone the Repository
```bash
git clone [your-repo-url]
cd [your-repo-name]

```

### 2️⃣ Configure Environment Variables

Create an application.properties file inside src/main/resources/
or set environment variables with the following keys:

```bash

# MongoDB
spring.data.mongodb.uri=your_mongodb_connection_string

# Cloudinary
cloudinary.cloud_name=your_cloudinary_name
cloudinary.api_key=your_cloudinary_api_key
cloudinary.api_secret=your_cloudinary_api_secret

# JWT
jwt.secret.key=your_jwt_secret

# Razorpay
razorpay_key=your_razorpay_key
razorpay_secret=your_razorpay_secret

# CORS Configuration
frontend.admin.url=http://localhost:3000
frontend.client.url=http://localhost:5173

```

### ▶️ Running the Application
Build the project:

```
mvn clean install
```
Run the application:
```
mvn spring-boot:run
```

By default, the backend runs on:
👉 http://localhost:8080

---

## 📡 API Endpoints

Here’s a quick reference for all API endpoints:  

### 📋 Quick Reference
| Endpoint | Method | Description | Auth |
|----------|--------|-------------|------|
| `/api/register` | POST | Register a new user | None |
| `/api/login` | POST | Authenticate user & get JWT | None |
| `/api/foods` | GET | Get all food items | None |
| `/api/foods` | POST | Add a new food item | Admin |
| `/api/foods/{id}` | GET | Get food item by ID | None |
| `/api/foods/{id}` | DELETE | Delete food item by ID | Admin |
| `/api/cart` | GET | Get current user's cart | User |
| `/api/cart` | POST | Add item to cart | User |
| `/api/cart/remove` | POST | Remove item from cart | User |
| `/api/orders/create` | POST | Create new order (Razorpay) | User |
| `/api/orders/verify` | POST | Verify Razorpay payment | User |
| `/api/orders` | GET | Get current user's orders | User |
| `/api/orders/{orderId}` | DELETE | Delete an order | User |
| `/api/orders/all` | GET | Get all orders | Admin |
| `/api/orders/status/{orderId}?status=` | PATCH | Update status of an order | Admin |

---

### 📂 Grouped by Feature

#### 🔑 Authentication
| Endpoint | Method | Description | Auth |
|----------|--------|-------------|------|
| `/api/register` | POST | Register a new user | None |
| `/api/login` | POST | Authenticate & get JWT | None |

#### 🍔 Foods
| Endpoint | Method | Description | Auth |
|----------|--------|-------------|------|
| `/api/foods` | GET | Get all food items | None |
| `/api/foods` | POST | Add a new food item | Admin |
| `/api/foods/{id}` | GET | Get food item by ID | None |
| `/api/foods/{id}` | DELETE | Delete food item by ID | Admin |

#### 🛒 Cart
| Endpoint | Method | Description | Auth |
|----------|--------|-------------|------|
| `/api/cart` | GET | Get current user's cart | User |
| `/api/cart` | POST | Add item to cart | User |
| `/api/cart/remove` | POST | Remove item from cart | User |

#### 📦 Orders
| Endpoint | Method | Description | Auth |
|----------|--------|-------------|------|
| `/api/orders/create` | POST | Create new order (Razorpay) | User |
| `/api/orders/verify` | POST | Verify Razorpay payment | User |
| `/api/orders` | GET | Get current user's orders | User |
| `/api/orders/{orderId}` | DELETE | Delete an order | User |
| `/api/orders/all` | GET | Get all orders | Admin |
| `/api/orders/status/{orderId}?status=` | PATCH | Update order status | Admin |


## 📌 Project Structure

```
src/main/java/com/rasoirun
│── controller   # REST Controllers
│── service      # Business Logic
│── repository   # MongoDB Repositories
│── model        # Entities / DTOs
│── config       # Security & App Configurations

```
---
### 📄 License

This project is licensed under the MIT License.

