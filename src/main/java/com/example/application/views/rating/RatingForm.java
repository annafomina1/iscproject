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
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@PageTitle("Rating Entry Form")
@Route(value = "rating", layout = MainLayout.class)
@Uses(Icon.class)
public class RatingForm extends FormLayout {

    private TextField userName = new TextField("Username");
    private ComboBox<Restaurant> restaurant = new ComboBox<>("Restaurant");
    private IntegerField value = new IntegerField("Rating");
    private TextField comment = new TextField("Comment");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<Rating> binder = new Binder<>(Rating.class);

    public RatingForm(RatingService ratingService, RestaurantService restaurantService, UserService userService) {
        addClassName("user-view");

        List<Restaurant> restaurants = restaurantService.findAll();
        restaurant.setItems(restaurants);
        restaurant.setItemLabelGenerator(Restaurant::getName);

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        save.addClickListener(e -> {
            String usernameInput = userName.getValue();
            User user = userService.findByUserName(usernameInput);
            if (user == null) {
                Notification.show("Username was not found.");
            }

            Restaurant selectedRestaurant = restaurant.getValue();
            if (selectedRestaurant == null) {
                Notification.show("Select Restaurant.");
            }

            if ((user != null) && (selectedRestaurant != null)) {
                Rating rating = binder.getBean();
                rating.setUserId(user.getId());
                rating.setRestaurantId(selectedRestaurant.getId());

                ratingService.update(rating);

                Notification.show("Rating was stored.");
                clearForm();
            }
        });

        cancel.addClickListener(e -> clearForm());
    }

    private void clearForm() {
        binder.setBean(new Rating());
        restaurant.clear();
    }

    private Component createTitle() {
        return new H3("Enter Rating:");
    }

    private Component createFormLayout() {
        value.setMin(0);
        value.setMax(5);
        value.setHasControls(true);

        FormLayout formLayout = new FormLayout();
        formLayout.add(userName, restaurant, value, comment);
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

