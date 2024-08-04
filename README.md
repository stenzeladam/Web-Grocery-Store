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
- Java 22.0.2: https://www.oracle.com/java/technologies/downloads/?er=221886
- Maven 3.9.8: https://maven.apache.org/download.cgi
- Node.js (for the frontend only): https://nodejs.org/en
- npm (for the frontend only): https://docs.npmjs.com/downloading-and-installing-node-js-and-npm


## Installation

### Backend
1. Clone the repository and navigate to the project folder in your terminal.

2. Build the project using Maven:
```bash
mvn clean install
```
3. Run the Spring Boot application:
```bash
mvn spring-boot:run
```
4. Alternatively you can use an IDE. The `main` method is in GroceryStoreApplication.java as your entry point into the application. Run this class/file to start the backend. From the IDE, you can also run the tests by navigating to src/test/java/groceryStore.GroceryStore and run the test files.
5. If not using a front end, you can make API calls (with a tool such as Postman) to get an order using `http://localhost:8080/send_order` and sending a JSON object with the order details in the body. Go to [API Endpoints](#api-endpoints) for an example on how to send an order as an object.

### Frontend (Optional)
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

### Set Prices Example
- **Call this API method first**: At least once to create a Price object to use for the rest of the application. There will only ever be one Price object instance. The following prices in the example are the default prices as specified in the assignment documentation.
- **URL:** `/set_Prices`
- **Method:** `POST`
- **Request Body (Raw JSON):**
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

### Create Order Example
- **URL:** `/send_order`
- **Method:** `POST`
- **Request Body (Raw JSON):**
  ```json
  {
    "bread": [1, 0, 0, 12, 9, 2, 24],
    "vegetables": 0,
    "belgianBeers": 0,
    "dutchBeers": 6,
    "germanBeers": 7
  }
  ```
- **Note**: for the `bread` array, the values represent the quantity of bread pieces, and their index number is the how many days old the piece(s) of bread are. In this example, there is 1 piece of bread that is 0 days old, 12 pieces of bread that are 3 days old, 9 pieces of bread that are 4 days old, 2 pieces of bread that are 5 days old, and 24 pieces of bread that are 6 days old. These values in the array must be integers. The values for `belgianBeers`, `dutchBeers`, and `germanBeers` must be integers, and the value for `vegetables` may have a decimal. 
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


