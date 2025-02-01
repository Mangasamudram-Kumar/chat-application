# 💬 Chat Application

A real-time chat application built using **Spring WebSocket** with **STOMP protocol over SockJS**, enabling users to engage in **public and private conversations** with live status updates.

## 📌 Features  
✅ **Real-time messaging** using Spring WebSocket and STOMP protocol  
✅ **Public & Private Chat Channels**  
✅ **STOMP Topic & Queue Subscription** for broadcasting messages  
✅ **Online & Offline User Detection**  
✅ **Browser Close Detection** to update user presence  

## 🎥 Demo  
### 🗨️ One-on-One Conversation  
🔹 Users can have private chat sessions securely.  

### 🏛️ Public Conversation  
🔹 Users can join public chat rooms and interact with multiple participants.  

## 🛠️ Project Requirements  
- **JDK 17**  
- **Spring Boot 3.x.x**  
- **PostgreSQL**  
- **Node.js v20.12.2**  
- **ReactJS v18**  
- **TailwindCSS v3.4.3**  

## 🚀 Project Setup  

### 📌 Using Docker-Compose  
1️⃣ **Create a `.env` file** in the project root and configure the database settings:  

```env
POSTGRES_DB=chat
POSTGRES_USER=postgres
POSTGRES_PASSWORD=password
DATASOURCE_URL=jdbc:postgresql://db:5432/
Note: DATASOURCE_URL is set based on the PostgreSQL container name and port defined in the docker-compose.yaml file.

2️⃣ Run the following command to start the project:

sh
Copy
Edit
docker-compose up --build -d
⚠️ Ensure that ports 3000, 8080, and 5432 are not occupied by other applications.
