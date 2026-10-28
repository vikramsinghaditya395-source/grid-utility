# GridCare Utility Portal (fresh build)

Independent implementation: plain HTML/CSS/JS + Spring Boot 3, Java 17, Spring Data JPA and persistent H2 database.

## Run
1. Install JDK 17+ and Maven 3.9+.
2. `cd backend && mvn spring-boot:run`
3. In another terminal: `cd frontend && python -m http.server 5500`
4. Open http://localhost:5500

Demo admin: `ADMIN001` / `Admin@123`
Demo consumers: `CUS1001` / `9876543210`, `CUS1002` / `9123456780`, `CUS2001` / `9988776655`.
Request consumer OTP. The backend prints the code to its terminal and returns `demoOtp` for local testing (`app.demo-otp-response=true`). OTP expires in 5 minutes, with up to 5 attempts. Disable the demo response and connect an SMS provider before production.

Data is stored in `backend/data/gridcare-db`. The H2 console is at `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:file:./data/gridcare-db`, user `sa`, blank password).

## OOP mapping
- Encapsulation: private entity fields, accessors, service-layer validation.
- Inheritance: `ResidentialTariff` and `CommercialTariff` extend abstract `Tariff`.
- Polymorphism: `TariffService` invokes tariff calculations through `Tariff`.
- Exception handling: `PortalException` and centralized `@RestControllerAdvice`.

Payment is a demo record only; it does not transfer money. The demo admin credentials and in-memory session tokens are for coursework/local demos, not production.
