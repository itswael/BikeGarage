# 🚴‍♂️ BikeGarage

BikeGarage is an Android-based application designed for **bike service center owners** to efficiently manage their services, customers, and billing operations. This system simplifies tracking service records, issuing invoices, and maintaining customer data—reducing paperwork and enhancing productivity.

---

## 🔧 Tech Stack

### 🖥️ Backend
- **Spring Boot** (Java)
- **Spring Data JPA**
- **Spring Security** with **BCrypt**
- **REST APIs**
- **Firebase Authentication** (Google Sign-In)
- **Firebase Realtime Database / Firestore** (cloud-based storage)

### 📱 Android App
- **Java (Android SDK)**
- **Google Sign-In**
- **Multilingual Support:** English, Hindi
- **Currency Support:** INR

---

## 📦 Features

- 🔐 **Authentication**
    - Secure sign-in with Google
    - Firebase-based session management

- 👥 **Customer Management**
    - Add, update, and delete customer profiles
    - View customer service history

- 🛠️ **Service Tracking**
    - Manage bike service records
    - Add details like parts replaced, labor cost, and next due date

- 💰 **Billing**
    - Auto-generate bills/invoices in INR
    - Store billing records for reference

- 🌐 **Multilingual Interface**
    - English (default) and Hindi (optional)

---

## 🧱 Architecture

- MVC-based layered backend (Controller → Service → Repository)
- DTO pattern for clean API contracts
- Firebase handles authentication and NoSQL data storage
- Android app communicates via REST APIs

---

## 🔒 Security

- **BCrypt** for password encryption (for admin-level internal credentials if needed)
- **Role-based access control** (to restrict different user actions)
- **Google Sign-In** using Firebase Authentication

---

## 🚀 Getting Started

### Backend (Spring Boot)

```bash
# Clone the repo
git clone https://github.com/your-username/bikegarage.git
cd bikegarage/backend

# Build the project
./mvnw clean install

# Run the server
./mvnw spring-boot:run
```

### Android App
- Open the Android project in Android Studio

- Configure Firebase project and connect it

- Replace google-services.json with your Firebase config

- Run the app on a real device or emulator

```bash
bikegarage/
│
├── backend/           # Spring Boot backend (REST APIs, Auth)
├── android-app/       # Android client app (UI + logic)
└── README.md
```

## 👨‍💻 Contributing
- Fork the repository
- Create a new branch: feature/your-feature-name
- Commit your changes
- Push and create a pull request

## 📄 License
This project is licensed under the **Wael Non-Commercial Attribution License (WNCA)**.

You're free to use, modify, and share this project **for non-commercial purposes**, as long as:
- **Credit is given** to the original author, *Mohammad Wael*.
- A copy of the license is included with any substantial portion of this work.
- You do **not** use it for monetary gain without written permission.

See the [LICENSE](./LICENSE) file for full terms.

[![License: WNCA](https://img.shields.io/badge/license-WNCA-blue.svg)](./LICENSE)