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
/*
This class is the form to add a new restaurant.
Created by Harper Rapkin and Anna Fomina
 */
@PageTitle("Restaurant Information Form")
@Route(value = "restaurant", layout = MainLayout.class)
@Uses(Icon.class)
public class RestaurantView extends Div {
    // creates TextField objects for each input field
    private TextField name = new TextField("Name");
    private TextField address = new TextField("Address");
    private TextField postalCode = new TextField("Postal Code");
    private TextField cuisine = new TextField("Cuisine");
    private TextField location = new TextField("Location");
    private TextField cost = new TextField("Cost ($-$$$$)");

// creates button objects for the cancel and save button
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
//binder for restaurant
    private Binder<Restaurant> binder = new Binder<>(Restaurant.class);
    
    //creating the field objects for the help popup window system (Harper Rapkin)
    private Button help = new Button("?");//help button
    private Button closeButton = new Button("x");//close for the help notification
    private Notification notification = new Notification();//creating a notification object
    //text that appears on the popup window
    private Text text = new Text ("Here feel free to add any restaurants with their appropriate information. \n");

    public RestaurantView(RestaurantService restaurantService) {
        addClassName("restaurant-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        add(help); //adding the button to access the help popup window (Harper Rapkin)

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            restaurantService.update(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
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
