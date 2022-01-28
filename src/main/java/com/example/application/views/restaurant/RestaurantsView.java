package com.example.application.views.restaurant;

import com.example.application.data.entity.Restaurant;
import com.example.application.data.service.RestaurantService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import java.util.List;
import com.vaadin.flow.component.notification.Notification;

/*
RestaurantsView.java
Created by Harper Rapkin and Ava Donaldson
 */

@PageTitle("List of Restaurants")
@Route(value = "restaurants", layout = MainLayout.class)
public class RestaurantsView extends VerticalLayout {

    private TextField cuisine = new TextField("Search by cuisine..."); // search bar to type in (Ava Donaldson)
    private Button search = new Button("Search"); // search button
    Grid<Restaurant> grid = new Grid<>(Restaurant.class);
    RestaurantService service;
    
    //creating the field objects for the help popup window system (Harper Rapkin)
    private Button help = new Button("?");//help button
    private Button closeButton = new Button("x");//close for help notification
    private Notification notification = new Notification();//creating a notification object
    //text that appears on the popup window
    private Text text = new Text ("Here you can see all the restaurants listed with their ratings, costs, location and cuisine types. You can also search restaurants by cuisine.\n");

    /**
     * RestaurantsView
     * @param service
     */
    public RestaurantsView(RestaurantService service) {

        this.service = service;
        setSizeFull();
        configureGrid();
        add(cuisine, search, grid);
        add(help); //adding the button to access the help popup window (Harper Rapkin)
        setList();

        //if the user presses the search button it will update the list and add the grid of what cuisine they searched for (Ava Donaldson)
        search.addClickListener(e ->{

            updateList();
            add(grid);

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
     * configureGrid method creates the grid filled with restaurants and each of their attributes
     */
    private void configureGrid() {
        grid.addClassNames("restaurant-grid");
        grid.setSizeFull();
        grid.setColumns("name", "address", "postalCode", "cuisine", "location", "cost");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    /**
     * updateList (ava donaldson)
     */
    private void updateList() {

        //if the search box is empty, or has nothing in it, it just shows the full list of restaurants
        if(cuisine== null|| cuisine.getValue().equals("")){
            List<Restaurant> list = service.findAll();
            grid.setItems(list);

        }

        //if the user searches for a specific cuisine, it will make it case-insensitive and use the findCuisine method in the service class to find the list of restaurants that match the chosen cuisine
        else{
            List<Restaurant> list = service.findCuisine(cuisine.getValue().toLowerCase());
            grid.setItems(list);
        }

    }//end method

    /**
     * setList (ava donaldson)
     */
    private void setList(){
        List<Restaurant> list = service.findAll();
        grid.setItems(list);
    }//end method

}
