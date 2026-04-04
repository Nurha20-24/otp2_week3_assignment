# Fuel Calculator - Multi-Language with Database

A JavaFX application that calculates fuel consumption and trip cost with multi-language support (English, French, Japanese, Persian).
UI messages are loaded from a database and calculations are saved to the database.

## Features
- Calculate fuel consumption and trip cost
- Multi-language support: English, French, Japanese, Persian
- Database-driven localization (no property files)
- Saves all calculation records to database
- CI/CD pipeline with Jenkins
- Dockerized application

## Database Setup

### REQUIREMENTS
- MariaDB or MySQL installed

#### step 1: Create Database User
```sql
CREATE USER 'fuelapp'@'localhost' IDENTIFIED BY 'fuel123';
GRANT ALL PRIVILEGES ON fuel_calculator_localization.* TO 'fuelapp'@'localhost';
FLUSH PRIVILEGES;
```

#### step 2: Run Schema
```bash
mariadb -u fuelapp -pfuel123 < schema.sql
```

### Step 3: Insert Localization Strings
```sql
-- English
INSERT INTO localization_strings (`key`, value, language) VALUES
('distance.label', 'Distance (km)', 'en'),
('consumption.label', 'Fuel Consumption (L/100 km)', 'en'),
('price.label', 'Fuel Price (per liter)', 'en'),
('calculate.button', 'Calculate Trip Cost', 'en'),
('result.label', 'Total fuel needed: {0} L | Total cost: {1}', 'en'),
('invalid.input', 'Invalid input! Please enter valid numbers.', 'en');

-- French
INSERT INTO localization_strings (`key`, value, language) VALUES
('distance.label', 'Distance (km)', 'fr'),
('consumption.label', 'Consommation de carburant (L/100 km)', 'fr'),
('price.label', 'Prix du carburant (par litre)', 'fr'),
('calculate.button', 'Calculer le coût du voyage', 'fr'),
('result.label', 'Carburant total nécessaire: {0} L | Coût total: {1}', 'fr'),
('invalid.input', 'Entrée invalide! Veuillez entrer des nombres valides.', 'fr');

-- Japanese
INSERT INTO localization_strings (`key`, value, language) VALUES
('distance.label', '距離（km）', 'ja'),
('consumption.label', '燃料消費量（L/100 km）', 'ja'),
('price.label', '燃料価格（1リットルあたり）', 'ja'),
('calculate.button', '旅行費用を計算する', 'ja'),
('result.label', '必要な燃料: {0} L | 合計費用: {1}', 'ja'),
('invalid.input', '無効な入力です。有効な数字を入力してください。', 'ja');

-- Persian
INSERT INTO localization_strings (`key`, value, language) VALUES
('distance.label', 'مسافت (کیلومتر)', 'fa'),
('consumption.label', 'مصرف سوخت (لیتر/100 کیلومتر)', 'fa'),
('price.label', 'قیمت سوخت (هر لیتر)', 'fa'),
('calculate.button', 'محاسبه هزینه سفر', 'fa'),
('result.label', 'سوخت مورد نیاز: {0} لیتر | هزینه کل: {1}', 'fa'),
('invalid.input', 'ورودی نامعتبر! لطفاً اعداد معتبر وارد کنید.', 'fa');
```

## Database Connection Configuration

Default connection settings in `DatabaseConnection.java`:

| Setting | Default    | Environment Variables |
|---------|------------|-----------------------|
| Host    | localhost  | DB_HOST               |
| Port    | 3306 | DB_PORT               |
| User | fuelapp | DB_USER               |
| Password | fuel123 | DB_PASSWORD           |


## How to Run

### Run locally
```bash
mvn clean javafx:run
```

### Run Docker Image
```bash
docker pull nurha2024/otp2_week3_assignment:latest
docker run nurha2024/otp2_week3_assignment:latest
```

### Run with Docker Compose (includes database)
```bash
docker-compose up
```

## Technologies Used
- Java 21
- JavaFX 21
- MariaDB
- Maven
- JUnit 5
- JaCoCo
- Docker
- Jenkins