
# Rental App

RentalApp is a real estate rental web application developed using Spring (backend) and React (Frontend). The project focuses primarily on security aspects. Oauth2 Authorization Grant Type Flow with the BFF (Backend for Frontend) pattern are applied in the project. This is currently the most desirable and secure solution for frontend-backend communication and token management.

The application is still under development.

## Project Structure


The project currently consists of four applications:
- Frontend – A React application responsible for user interaction.
- RentalApp – The "main" backend application in Spring, containing data and business logic.
- Client – A Spring application responsible for communication between the frontend and backend, known as the BFF (Backend for Frontend). Its purpose is to separate the frontend from the authorization server. The Client handles authorization "on behalf" of the frontend, ensuring that tokens are not exposed to the frontend.
- AuthServer – An authorization server. A custom authorization server written in Spring, responsible for generating and verifying tokens during authorization.
## Screenshots
<img src="/screenshots/rentalapp-main.jpg" />
<img src="/screenshots/rentalapp-addOffer.jpg" />
<img src="/screenshots/rentalap-offers.jpg " />


## Application Status

I have successfully implemented the Oauth2 Authorization Grant Type flow on the backend side. Currently, I am working on the communication between the frontend and the client, as well as on the remaining business logic functionalities.
