package com.example.application.views.restaurant;

import com.example.application.data.entity.Restaurant;
import com.example.application.data.entity.User;
import com.example.application.data.service.RatingService;
import com.example.application.data.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import java.util.List;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.Text;
/*
This view is the Recommendations Page. A user enters their username and is shown a list of recommended restaurants.
Created By: Anna Fomina, Harper Rapkin
Date Created: 2021-12-13
Date Last Edited: 2022-01-26
 */
@PageTitle("Recommended For You")
@Route(value = "recommended", layout = MainLayout.class)
public class RecommendedView extends VerticalLayout {

    //username field and show button - Anna
    private TextField username = new TextField("Username");
    private Button show = new Button("Show");
    //grid and service
    Grid<Restaurant> grid = new Grid<>(Restaurant.class);
    RatingService service;
    
    //creating the field objects for the help popup window system (Harper Rapkin)
    private Button help = new Button("?");//help button
    private Button closeButton = new Button("x");//close for the help notification
    private Notification notification = new Notification();//creating a notification object
    //text that appears on the popup window
    private Text text = new Text ("Based on your ratings, we have gathered some recommendations for you!");

    /**
     * This is the main method for the Recommended View page.
     * @param service Rating service.
     * @param userService User service,
     */
    public RecommendedView(RatingService service, UserService userService) {
        this.service = service;
        //adds title, input field and button
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        add(help); //adding the button to access the help popup window (Harper Rapkin)



        //click listener for show button
        show.addClickListener(e -> {
            //adds the empty grid
            setSizeFull();
            configureGrid();
            add(grid);

            //gets value of username input and finds that user in the database
            String usernameInput = username.getValue();
            User user = userService.findByUserName(usernameInput);
            //if the user is not found, shows a notification
            if (user == null) {
                Notification.show("Username not found.");
            }
            //if the user is found, update the list with their recommendations
            if (user != null) {
                int userId = user.getId();
                updateList(userId);
            }
        });

        //setting the attributes and actions for the button (Harper Rapkin)
        Button closeButton = new Button("x");
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });

        //setting the attributes and actions for the button, adding the notification when the button is clicked (Harper Rapkin)
        help.addClickListener(e ->{
            HorizontalLayout layout = new HorizontalLayout(text, closeButton);
            notification.add(layout);
            notification.open();

        });
    }

    /**
     * Configures the grid by setting the width to auto and creating columns for each column in the restaurant table.
     * - Anna
     */
    private void configureGrid() {
        grid.addClassNames("restaurant-grid");
        grid.setSizeFull();
        grid.setColumns("name", "address", "postalCode", "cuisine", "location", "cost");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    /**
     * Creates the instructions for the page. - Anna
     * @return The title, a Component.
     */
    private Component createTitle() {
        return new H3("Enter your username to see your recommendations:");
    }

    /**
     * Creates the form layout by adding the username input field. - Anna
     * @return the form layout, a Component.
     */
    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(username);
        return formLayout;
    }

    /**
     * Creates the button layout by adding the button and formatting it. - Anna
     * @return The button layout, a Component.
     */
    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        show.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(show);
        return buttonLayout;
    }

    /**
     * Updates the list by populating it with the recommendations generated for the user. - Anna
     * @param userId The user ID for whom the recommendations are for.
     */
    private void updateList(int userId) {
        //makes a list of the recommendations by cuisine for the user
        List<Restaurant> list = service.getRecommendedRestaurantsByCuisine(userId);
        //if the list by cuisine is empty...
        boolean isRecommendedByCuisine = list.size() != 0;
        if (!isRecommendedByCuisine) {
            //set the list to be the recommendations by rating
            list = service.getRecommendedRestaurantsByRating(userId);
        }
        //populate the grid with the list items
        grid.setItems(list);
    }


}
