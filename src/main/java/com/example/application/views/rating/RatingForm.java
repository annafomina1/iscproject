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
    
    private Button help = new Button("?");
    private Button closeButton = new Button("x");
    private Notification notification = new Notification();
    private Text text = new Text ("If you have made an account, you can add new restaurant ratings here by logging in. ");

    private Binder<Rating> binder = new Binder<>(Rating.class);

    public RatingForm(RatingService ratingService, RestaurantService restaurantService, UserService userService) {

        List<Restaurant> restaurants = restaurantService.findAll();
        restaurant.setItems(restaurants);
        restaurant.setItemLabelGenerator(Restaurant::getName);

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        add(help);

        binder.bindInstanceFields(this);
        clearForm();

        save.addClickListener(e -> {
            String usernameInput = username.getValue();
            String passwordInput = password.getValue();

            User user = userService.findByUserName(usernameInput);
            if (user == null) {
                Notification.show("Username not found.");
            }

            if(user!=null) {
                if(!passwordInput.equals(user.getPassword())) {
                    Notification.show("Password is incorrect.");
                }
            }

            Restaurant selectedRestaurant = restaurant.getValue();
            if (selectedRestaurant == null) {
                Notification.show("Select a restaurant.");
            }

            boolean valueRange = true;

            if(value.getValue()< 1 || value.getValue() >5) {
                Notification.show("Invalid rating.");

                valueRange=false;
            }

            if ((user != null) && (selectedRestaurant != null) && (passwordInput.equals(user.getPassword())) && valueRange) {
                Rating rating = binder.getBean();
                rating.setUserId(user.getId());
                rating.setRestaurantId(selectedRestaurant.getId());

                ratingService.update(rating);

                Notification.show("Rating was stored.");
                clearForm();
            }
        });

        cancel.addClickListener(e -> clearForm());
        
        Button closeButton = new Button("x");
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });

        help.addClickListener(e ->{
            HorizontalLayout layout = new HorizontalLayout(text, closeButton);
            notification.add(layout);
            notification.open();

        });
    }

    private void clearForm() {
        binder.setBean(new Rating());
        restaurant.clear();
        value.setValue(1);
    }

    private Component createTitle() {
        return new H3("Enter Rating:");
    }

    private Component createFormLayout() {
        value.setMin(1);
        value.setMax(5);
        value.setHasControls(true);

        FormLayout formLayout = new FormLayout();
        formLayout.add(username, password, restaurant, value);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }


}

