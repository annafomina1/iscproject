# ICS Semester Project

This program is a restaurant rating website. Users make an account to rate
the available restaurants and add new ones. Restaurant recommendations are 
provided for users. 

Created by Anna Fomina, Ava Donaldson, Harper Rapkin, and Christine Shao.

Link to the GitHub repo: https://github.com/annafomina1/iscproject

Link to Application path: https://github.com/annafomina1/iscproject/tree/367e50003f632f9d3c0ff883e6b88e7e38bbea6d/src/main/java/com/example/application

Entity (AverageRating, FavouriteCuisine, Rating, Restaurant, User): https://github.com/annafomina1/iscproject/tree/367e50003f632f9d3c0ff883e6b88e7e38bbea6d/src/main/java/com/example/application/data/entity
Service (AuthService, RatingRepository, RatingService, RestaurantRepository, RestaurantService, UserRepository, UserService): https://github.com/annafomina1/iscproject/tree/367e50003f632f9d3c0ff883e6b88e7e38bbea6d/src/main/java/com/example/application/data/service
Views (AboutView, HomePage, LoginView, RatingForm, RatingsView, RecommendedView, ResturantsView, RestaurantView, UsersView, UserView, MainLayout): https://github.com/annafomina1/iscproject/tree/367e50003f632f9d3c0ff883e6b88e7e38bbea6d/src/main/java/com/example/application/views 

(Entities are the basic Java classes. Service contains the logic methods and connects to the database. 
Views are the user-friendly interface pages.)

The database is a mySQL database hosted on AWS. Connection in application.properties: https://github.com/annafomina1/iscproject/blob/367e50003f632f9d3c0ff883e6b88e7e38bbea6d/src/main/resources/application.properties 
## Running the application

The project is a standard Maven project. To run it from the command line,
type `mvnw` (Windows), or `./mvnw` (Mac & Linux), then open
http://localhost:8080 in your browser.

You can also import the project to your IDE of choice as you would with any
Maven project. Read more on [how to set up a development environment for
Vaadin projects](https://vaadin.com/docs/latest/guide/install) (Windows, Linux, macOS).

## Project structure

- `MainView.java` in `src/main/java` contains the navigation setup (i.e., the
  side/top bar and the main menu). This setup uses
  [App Layout](https://vaadin.com/components/vaadin-app-layout).
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `views` folder in `frontend/` contains the client-side JavaScript views of your application.
- `themes` folder in `frontend/` contains the custom CSS styles.

## Useful links

- Read the documentation at [vaadin.com/docs](https://vaadin.com/docs).
- Follow the tutorials at [vaadin.com/tutorials](https://vaadin.com/tutorials).
- Watch training videos and get certified at [vaadin.com/learn/training](https://vaadin.com/learn/training).
- Create new projects at [start.vaadin.com](https://start.vaadin.com/).
- Search UI components and their usage examples at [vaadin.com/components](https://vaadin.com/components).
- Discover Vaadin's set of CSS utility classes that enable building any UI without custom CSS in the [docs](https://vaadin.com/docs/latest/ds/foundation/utility-classes). 
- Find a collection of solutions to common use cases in [Vaadin Cookbook](https://cookbook.vaadin.com/).
- Find Add-ons at [vaadin.com/directory](https://vaadin.com/directory).
- Ask questions on [Stack Overflow](https://stackoverflow.com/questions/tagged/vaadin) or join our [Discord channel](https://discord.gg/MYFq5RTbBn).
- Report issues, create pull requests in [GitHub](https://github.com/vaadin/platform).
