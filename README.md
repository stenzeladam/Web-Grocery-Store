# Grocery Store Application

This project is a coding assignment that creates web-based grocery store application that allows users to place orders for bread, vegetables, and various types of beers. The application is built using Spring Boot for the backend.
A frontend was optional, but I decided to do one anyways. It is called "Web-Grocery-Store" after all, so I thought it'd make sense to make a web-browser based frontend.

Per the assignment requirements, the backend will work with just Java installed on your machine - nothing fancy needed in addition.
The instructions were to keep it simple, so I tried to keep the backend logic straightforward while allowing for flexibility for potential changing requirements in the future.
Unit tests for the backend can be found in the tests folder, because "trust me bro, it works" doesn't inspire that much confidence.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Frontend Components](#frontend-components)
- [Running Tests](#running-tests)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Features
- **Order Processing:** Calculate total prices for different items, apply discounts automatically, and generate a receipt.
- **Discounts:** Apply discounts based on the age of bread, the quantity of vegetables ordered, and ordering 6-packs of beer. The number of beers in a discount pack can be changed; a 12-pack for example. 
- **User Interface:** A simple and responsive frontend built with React and Material-UI.

## Prerequisites
- Java 22.0.2 (OpenJDK)
- Node.js and npm (for the frontend only)
- Maven

## Installation

### Backend
1. Clone the repository:

```bash
git clone https://github.com/yourusername/Web-Grocery-Store.git
cd Web-Grocery-Store
```
2. Build the project using Maven:

```bash
mvn clean install
```
3. Run the Spring Boot application:
```bash
mvn spring-boot:run
```

## Frontend
1. Navigate to the `frontend` directory:
```bash
cd frontend
```
2. Install the dependencies:
```bash
npm install
```
3. Start the React development server:
``` bash
npm start
```

## Usage

1. Open your browser and navigate to http://localhost:3000 (or whatever port your terminal says is open when running it).
2. Enter the quantities for bread, vegetables, and beers.
3. Click on the "Submit Order" button to place the order.
4. View the receipt generated based on your input.

## API Endpoints

### Set Prices
- **URL:** `/set_Prices`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "BREAD_PRICE": 1.00,
    "VEGETABLES_PRICE": 1.00,
    "BELGIAN_BEERS_PRICE": 0.75,
    "DUTCH_BEERS_PRICE": 0.50,
    "GERMAN_BEERS_PRICE": 1.00
  }
  ```
- **Response**: The updated price list.

### Create Order
- **URL:** `/send_order`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "bread": [1, 0, 0, 3, 3, 4, 4],
    "vegetables": 0,
    "belgianBeers": 0,
    "dutchBeers": 6,
    "germanBeers": 7
  }
  ```
- **Response**: A list of items with their quantities, unit prices, total prices, and applied discounts.

## Frontend Components

### index.tsx
The main entry point of the React application.

### App.tsx
The main application component, containing the state and handlers for the order form and receipt.

### Components
- **BreadInput:** Component for entering bread quantities.
- **Vegetables:** Component for entering the quantity of vegetables.
- **BelgianBeer:** Component for entering the quantity of Belgian beers.
- **DutchBeer:** Component for entering the quantity of Dutch beers.
- **GermanBeer:** Component for entering the quantity of German beers.
- **SubmitOrderButton:** Button to submit the order.
- **ResetButton:** Button to reset the order form.
- **ReceiptTable:** Component to display the receipt.

## Running Tests

### Backend
1. Navigate to the project root directory.
2. Run the tests using Maven:
```bash
mvn test
```
### Frontend
1. Navigate to the `frontend` directory.
2. Run the tests using npm:
```bash
npm test
```

## Project Structure

### Backend

- ### src/main/java/groceryStore/GroceryStore
  - **GroceryStoreApplication.java:** Main application class.
  - **GroceryStoreController.java:** REST controller for handling requests.
  - **Prices.java:** Singleton class for managing item prices.
  - **Order.java:** Class representing an order.
  - **Item.java:** Class representing an item in the order.
  - **BreadEvaluator.java:** Helper class for evaluating bread orders.
  - **VegetableEvaluator.java:** Helper class for evaluating vegetable orders.
  - **BeerEvaluator.java:** Helper class for evaluating beer orders.
  - **WebConfig.java:** Configuration class for CORS settings.

### Frontend

- ### src
  - **index.tsx:** Main entry point for the React application.
  - **App.tsx:** Main application component.

- ### components
  - **BreadInput.tsx:** Bread input component.
  - **Vegetables.tsx:** Vegetables input component.
  - **BelgianBeer.tsx:** Belgian beer input component.
  - **DutchBeer.tsx:** Dutch beer input component.
  - **GermanBeer.tsx:** German beer input component.
  - **SubmitOrderButton.tsx:** Submit order button component.
  - **ResetButton.tsx:** Reset button component.
  - **ReceiptTable.tsx:** Receipt table component.
  - **App.css:** CSS file for styling.

## Contributing
It's a coding assignment, so not really.

## License
I don't have the money to sue anyone for stealing this, nor would I be bothered by it. It's a humble coding assignment.


