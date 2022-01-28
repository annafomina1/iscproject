package com.example.application.views.rating;

import com.example.application.data.entity.Rating;
import com.example.application.data.entity.Restaurant;
import com.example.application.data.entity.User;
import com.example.application.data.service.RatingService;
import com.example.application.data.service.RestaurantService;
import com.example.application.data.service.UserService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.List;
import com.vaadin.flow.component.Text;

/*
This class is a view that allows the user to rate restaurants. It checks the username and password using the user
service. The user is able to select the restaurant they wish to rate from a drop-down menu and enter a rating out
between 1-5. Invalid usernames, passwords, restaurants, and rating values are rejected. The data is stored in the
rating table in the database using the Binder.
Created By: Anna Fomina, Harper Rapkin
Date Created: 2021-12-13
Date Last Edited: 2022-01-26
 */
@PageTitle("Rating Form")
@Route(value = "rating", layout = MainLayout.class)
@Uses(Icon.class)
public class RatingForm extends FormLayout {
    /*creates components for each input field on the form. username, password, restaurant to be rated (from a drop-down menu),
     
     */
    private TextField username = new TextField("Username");
    //make password field
    private PasswordField password = new PasswordField("Password");
    private ComboBox<Restaurant> restaurant = new ComboBox<>("Restaurant");
    private IntegerField value = new IntegerField("Rating");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    
    //creating the field objects for the help popup window system (Harper Rapkin)
    private Button help = new Button("?");//help button
    private Button closeButton = new Button("x");//close for help notification
    private Notification notification = new Notification();
    //text that appears on the popup window
    private Text text = new Text ("If you have made an account, you can add new restaurant ratings here by logging in. ");

    //creates the binder for the ratings
    private Binder<Rating> binder = new Binder<>(Rating.class);

    /**
     * The Rating Form page. Contains the input fields, the buttons with listeners and binds the inputted data to the
     * database.- Anna
     * @param ratingService The rating service.
     * @param restaurantService The restaurant service.
     * @param userService The user service.
     */
    public RatingForm(RatingService ratingService, RestaurantService restaurantService, UserService userService) {

        //creates list of all restaurants - Anna
        List<Restaurant> restaurants = restaurantService.findAll();
        //populated the drop-down menu with all restaurants - Anna
        restaurant.setItems(restaurants);
        restaurant.setItemLabelGenerator(Restaurant::getName);

        //adds the components of the form - Anna
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        add(help); //adding the button to access the help popup window (Harper Rapkin)

        //sets binder
        binder.bindInstanceFields(this);
        //clears form
        clearForm();

        //click listener for when the save button is pressed - Anna
        save.addClickListener(e -> {
            //puts values of username and password input into string variable
            String usernameInput = username.getValue();
            String passwordInput = password.getValue();

            //finds the username of the user and creates the User object
            User user = userService.findByUserName(usernameInput);
            //if the user is not found, shows notification
            if (user == null) {
                Notification.show("Username not found.");
            }

            //checks that the password is correct, if not, shows notification
            if(user!=null) {
                if(!passwordInput.equals(user.getPassword())) {
                    Notification.show("Password is incorrect.");
                }
            }

            //creates a Restaurant object for the restaurant the user selected
            Restaurant selectedRestaurant = restaurant.getValue();
            //shows a notification if the restaurant is not selected
            if (selectedRestaurant == null) {
                Notification.show("Select a restaurant.");
            }

            //checks that the value the user entered is within the range
            boolean valueRange = true;

            if(value.getValue()< 1 || value.getValue() >5) {
                Notification.show("Invalid rating.");

                valueRange=false;
            }

            /*
            Checks that all the inputs into the form are valid. Sets the rating object to the rating value and user ID.
            Updates the rating using the rating service, storing it in the database. Shows a notification that the rating
            was successful. Clears the form (excluding username and password).
             */
            if ((user != null) && (selectedRestaurant != null) && (passwordInput.equals(user.getPassword())) && valueRange) {
                Rating rating = binder.getBean();
                rating.setUserId(user.getId());
                rating.setRestaurantId(selectedRestaurant.getId());

                ratingService.update(rating);

                Notification.show("Rating was stored.");
                clearForm();
            }
        });

        //clears the form if the cancel button is pressed - Anna
        cancel.addClickListener(e -> clearForm());

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
     * Clears the form by setting the binder to an empty rating object and clearing the restaurant selection. Clears
     * the rating and sets it to the default value of one.
     */
    private void clearForm() {
        binder.setBean(new Rating());
        restaurant.clear();
        value.setValue(1);
    }

    /**
     * Creates the form title/instruction.
     * @return The title, a component.
     */
    private Component createTitle() {
        return new H3("Enter Rating:");
    }

    /**
     * Creates the form layout by adding the buttons, setting the min and max values for the rating box.
     * @return The form, a Component.
     */
    private Component createFormLayout() {
        value.setMin(1);
        value.setMax(5);
        value.setHasControls(true);

        FormLayout formLayout = new FormLayout();
        formLayout.add(username, password, restaurant, value);
        return formLayout;
    }

    /**
     * Creates the button layout. Adds and formats the save and cancel buttons.
     * @return The save and cancel buttons, Components.
     */
    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }


}

