package com.example.application.views.restaurant;

import com.example.application.data.entity.Restaurant;
import com.example.application.data.service.RestaurantService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;

@PageTitle("Restaurant Information Form")
@Route(value = "restaurant", layout = MainLayout.class)
@Uses(Icon.class)
public class RestaurantView extends Div {
    // creates TextField objects for each field
    private TextField name = new TextField("Name");
    private TextField address = new TextField("Address");
    private TextField postalCode = new TextField("Postal Code");
    private TextField cuisine = new TextField("Cuisine");
    private TextField location = new TextField("Location");
    private TextField cost = new TextField("Cost");
    //^ drop down?
// creates button objects for the cancel and save button
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
//binder for restaurant
    private Binder<Restaurant> binder = new Binder<>(Restaurant.class);
    
    private Button help = new Button("?");
    private Button closeButton = new Button("x");
    private Notification notification = new Notification();
    private Text text = new Text ("Here feel free to add any restaurants with their appropriate information. \n");

    public RestaurantView(RestaurantService restaurantService) {
        addClassName("restaurant-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        add(help);

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            restaurantService.update(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
        });
        
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
        binder.setBean(new Restaurant());
    }

    private Component createTitle() {
        return new H3("Restaurant Information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(name, address, postalCode, cuisine, location, cost);
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
