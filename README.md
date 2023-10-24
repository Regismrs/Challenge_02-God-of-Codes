# Challenge_02-God-of-Codes

### Desafio II - Compass UOL - SP Spring Boot + AWS - 21-08-2023

Welcome to the Challenge_02-God-of-Codes E-commerce backend project! This project is designed to connect three
microservices, each responsible for managing a specific aspect of the e-commerce application. The three microservices
are ms-products, ms-orders, and ms-feedback.

## Table of Contents

- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)

## Project Structure

The project is structured into three microservices, each in its respective directory:

- `ms-products`: Manages product-related operations.
- `ms-orders`: Handles order creation, management, and processing.
- `ms-feedback`: Deals with feedback and reviews for products.

Each microservice contains its own set of controllers, services, and repositories.

## Prerequisites

Before you start, ensure you have met the following requirements:

- Java Development Kit (JDK) 18 or higher.
- Apache Maven for building the project.
- MySQL database server.
- IDE (e.g., IntelliJ IDEA, Eclipse) for development.
-

## Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/Challenge_02-God-of-Codes.git

2. Configure the database connection in the application.properties or application.yml files of each microservice.
   #### Database Settings

    * spring:
        * datasource:
            * url: jdbc:mysql://localhost:
              3306/db_ecommerce?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=America/Sao_Paulo
            * username: root
            * password: root
        * jpa:
            * hibernate:
                * ddl-auto: update
            * show-sql: true
            * properties:
                * hibernate:
                    * dialect: org.hibernate.dialect.MySQLDialect

# Api Endpoints

Each microservice exposes a set of API endpoints for its functionality. You can find more details about the endpoints in the API documentation.

* ms-products:
     
        1. Save a Product
            * URL: /products
            * HTTP Method: POST
            * Request Body (JSON):
                * {
                    "name": "Product name",
                    "description": "Product description",
                    "value": 10.5
                   }
             * Response:
                 * Returns the details of the saved car.
     
        2. Update a Product
  
              * URL: /products/{id}
              * HTTP Method: PUT
              * URL Parameters: id - id of the product to be updated.
              * Request Body (JSON):
                 * {
                     "name": "Product name",
                     "description": "Product description",
                     "value": 10.5
                   }
              * Response:
                * Returns the details of the updated product.
                
          3. Get All Product
              * URL: /products
              * HTTP Method: GET
              * Request Parameters: None
              * Response:
                * Returns a list of products.
      
             4. Get a Product by ID
                * URL: /products/{id}
                * HTTP Method: GET
                * URL Parameters: id - id of the product to be retrieved.
                * Response:
                    * Returns the details of the product with the specified ID.
     
            5. **Delete a Product by ID**
                * URL: /products/{id}
                * HTTP Method: DELETE
                * URL Parameters: id - id of the product to be deleted.
                * Response:
                    * Returns a success message after deletion.

  * ms-orders: 
     
          1. Save a Order
              * URL: /orders
              * HTTP Method: POST
              * Request Body (JSON):
                  * {
                      "products": [
                        {
                          "product_id": 1,
                          "quantity": 2
                        },
                        {
                          "product_id": 2,
                          "quantity": 5
                        }
                      ],
                      "address": {
                        "street": "Street name",
                        "number": 10,
                        "postalCode": "31333333"
                      },
                      "payment_method": "PIX"
                    }
              * Response:
                  * Returns the details of the saved order.
      
           2. Update a Order

               * URL: /orders/{id}
               * HTTP Method: PUT
               * URL Parameters: id - id of the order to be updated.
               * Request Body (JSON):
                  * {
                      "products": [
                        {
                          "product_id": 1,
                          "quantity": 2
                        },
                        {
                          "product_id": 2,
                          "quantity": 5
                        }
                      ],
                      "address": {
                        "street": "Street name",
                        "number": 10,
                        "postalCode": "31333333"
                      },
                      "payment_method": "PIX"
                    }
                   * Response:
                       * Returns the details of the updated order.
  
           3. Get All Orders
              * URL: /orders
              * HTTP Method: GET
              * Request Parameters: None
              * Response:
                 * Returns a list of orders.
     
           4. Get a Order by ID
              * URL: /orders/{id}
              * HTTP Method: GET
              * URL Parameters: id - id of the order to be retrieved.
              * Response:
                * Returns the details of the order with the specified ID.

           5. Cancel a Order by ID
              * URL: /orders/{id}
              * HTTP Method: POST
              * URL Parameters: id - id of the order to be canceled.
              * Request Body (JSON):
                * {
                    "cancelReason": "Cancel reason"
                   }
              * Response:
                 * Returns the details of the canceled order with the specified ID and its status canceled.
  
    * ms-feedback: 

              1. Save a Feedback
                  * URL: /feedbacks
                  * HTTP Method: POST
                  * Request Body (JSON):
                      * {
                            "id": 1,
                            "scale": "SATISFIED",
                            "comment": "Comment here",
                            "order_id": 1
                        }
                  * Response:
                      * Returns the details of the saved feedback.
      
               2. **Update a Feedback**

                   * URL: /feedbacks/{id}
                   * HTTP Method: PUT
                   * URL Parameters: id - id of the feedback to be updated.
                   * Request Body (JSON):
                      * {
                            "id": 1,
                            "scale": "DISSATISFIED",
                            "comment": "Comment here update",
                            "order_id": 1
                        }
                       * Response:
                           * Returns the details of the updated feedback.

               3. Get All Feedbacks
                  * URL: /feedbacks
                  * HTTP Method: GET
                  * Request Parameters: None
                  * Response:
                     * Returns a list of feedbacks.
     
               4. Get a Feedback by ID
                  * URL: /orders/{id}
                  * HTTP Method: GET
                  * URL Parameters: id - id of the order to be retrieved.
                  * Response:
                    * Returns the details of the feedback with the specified ID.

               5. Delete a Feedback by ID
                  * URL: /feedbacks/{id}
                  * HTTP Method: DELETE
                  * URL Parameters: id - id of the order to be canceled.
                  * Response:
                     * Returns a success message after deletion.
## Testing
You can run unit and integration tests for each microservice using the following command:

  ```bash
    mvn test