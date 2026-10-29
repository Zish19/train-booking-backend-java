# Train Reservation REST API (Java, Spring Boot, SQL)

A Maven-based REST API in Java for train reservations connected to an SQL database using Spring Data JPA. Supports GET, POST, PUT, and DELETE HTTP methods.

## How to Run

Start the application using Maven Wrapper:

```bash
.\mvnw.cmd spring-boot:run
```

The application starts on:
```
http://localhost:8080
```

H2 Web SQL Console is available at:
```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:traindb
User Name: sa
Password: (leave blank)
```

## Postman Testing

### Method 1: Import Collection
1. Open Postman.
2. Click Import.
3. Select `postman_collection.json` located in `c:\Users\Shivam Kumar\Desktop\javatut\mavenfile\postman_collection.json`.
4. Run the pre-configured requests.

### Method 2: Manual Endpoints

| Method | URL | Description |
| :--- | :--- | :--- |
| GET | http://localhost:8080/api/reservations | Get all reservations |
| GET | http://localhost:8080/api/reservations/1 | Get reservation by ID |
| POST | http://localhost:8080/api/reservations | Create new reservation |
| PUT | http://localhost:8080/api/reservations/1 | Update existing reservation |
| DELETE | http://localhost:8080/api/reservations/1 | Delete reservation by ID |

### Request and Response Examples

#### 1. GET All Reservations
- Method: GET
- URL: `http://localhost:8080/api/reservations`
- Status: 200 OK

#### 2. GET Reservation by ID
- Method: GET
- URL: `http://localhost:8080/api/reservations/1`
- Status: 200 OK

#### 3. POST Create Reservation
- Method: POST
- URL: `http://localhost:8080/api/reservations`
- Headers:
  - `Content-Type: application/json`
- Body:
```json
{
  "passengerName": "Vikram Seth",
  "trainNumber": "12301",
  "trainName": "Howrah Rajdhani",
  "sourceStation": "Howrah",
  "destinationStation": "New Delhi",
  "travelDate": "2026-12-01",
  "seatNumber": "B1-12",
  "ticketClass": "AC 3 Tier",
  "status": "CONFIRMED"
}
```
- Status: 201 Created

#### 4. PUT Update Reservation
- Method: PUT
- URL: `http://localhost:8080/api/reservations/1`
- Headers:
  - `Content-Type: application/json`
- Body:
```json
{
  "seatNumber": "A1-01",
  "ticketClass": "First AC",
  "status": "UPGRADED"
}
```
- Status: 200 OK

#### 5. DELETE Reservation
- Method: DELETE
- URL: `http://localhost:8080/api/reservations/2`
- Status: 200 OK
- Response:
```json
{
  "status": 200,
  "message": "Reservation with ID 2 has been successfully cancelled and deleted."
}
```

## SQL Schema
The application maps the Reservation entity to the `reservations` table in SQL:

- `id`: BIGINT (Primary Key, Auto-Increment)
- `passenger_name`: VARCHAR
- `train_number`: VARCHAR
- `train_name`: VARCHAR
- `source_station`: VARCHAR
- `destination_station`: VARCHAR
- `travel_date`: VARCHAR
- `seat_number`: VARCHAR
- `ticket_class`: VARCHAR
- `status`: VARCHAR
