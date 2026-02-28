## 👨‍💻 Author

**Shavez Mohammad**  
Senior Backend Developer  
Java • Spring Boot • Distributed Systems  

# 🚀 Distributed Task Scheduler

A lightweight **Distributed Cron Job Scheduler** built using Spring Boot, Quartz, and Redis.  
This project demonstrates cron-based scheduling, retry mechanisms, and distributed locking for cluster-safe execution.

---

## 📌 Features

- Create dynamic cron jobs via REST API
- Retry failed jobs using configurable `maxAttempts`
- Quartz-based scheduling engine
- Immediate retry (refire) on failure
- Distributed locking with Redis
- Clean modular Spring Boot architecture
- Docker-ready infrastructure

---

## 🏗 Architecture Overview

Client (Postman)
        ↓
Spring Boot REST API
        ↓
Quartz Scheduler
        ↓
HttpCallJob
        ↓
Redis Lock (Distributed Safe)
        ↓
External HTTP Endpoint

---

## ⚙️ Tech Stack

| Layer        | Technology       |
|-------------|------------------|
| Backend     | Spring Boot 3    |
| Scheduler   | Quartz 2.3       |
| Locking     | Redis            |
| Build Tool  | Maven            |
| Container   | Docker           |
| Java        | Java 21          |

---

## 🛠 Setup Instructions

### 1️⃣ Clone Repository

```bash
git clone https://github.com/shavez01/Distributed-task-scheduler.git
cd distributed-task-scheduler
```
### 2️⃣ Install Docker (Required for Redis)

### 3️⃣ Start Redis Container
```bash
docker run -d -p 6379:6379 redis
```

### 4️⃣ Run Application
```bash
mvn clean install
mvn spring-boot:run
```

Application runs at: http://localhost:9090

## 📡 API Usage

```code
http://localhost:9090/jobs
```

```json
{
  "name": "job1",
  "cron": "0/20 * * * * ?",
  "url": "https://postman-echo.com/get",
  "maxAttempts": 3
}
```