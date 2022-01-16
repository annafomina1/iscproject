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

@PageTitle("Recommended")
@Route(value = "recommended", layout = MainLayout.class)
public class RecommendedView extends VerticalLayout {

    private TextField username = new TextField("Username");
    private Button show = new Button("Show");

    Grid<Restaurant> grid = new Grid<>(Restaurant.class);
    RatingService service;

    public RecommendedView(RatingService service, UserService userService) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        add(grid);

        show.addClickListener(e -> {
            String usernameInput = username.getValue();
            User user = userService.findByUserName(usernameInput);
            if (user == null) {
                Notification.show("Username not found.");
            }

            if (user != null) {
                int userId = user.getId();
                updateList(userId);
            }
        });
    }

    private void configureGrid() {
        grid.addClassNames("restaurant-grid");
        grid.setSizeFull();
        grid.setColumns("name", "address", "postalCode", "cuisine", "location", "cost");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Component createTitle() {
        return new H3("Enter your username:");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(username);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        show.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(show);
        return buttonLayout;
    }

    private void updateList(int userId) {
        List<Restaurant> list = service.getRecommendedRestaurantsByCuisine(userId);
        boolean isRecommendedByCuisine = list.size() != 0;
        if (!isRecommendedByCuisine) {
            list = service.getRecommendedRestaurantsByRating(userId);
        }
        grid.setItems(list);
    }


}
