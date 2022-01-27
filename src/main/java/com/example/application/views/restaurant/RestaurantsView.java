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

@PageTitle("List of Restaurants")
@Route(value = "restaurants", layout = MainLayout.class)
public class RestaurantsView extends VerticalLayout {

    private TextField cuisine = new TextField("Search by cuisine...");
    private Button search = new Button("Search");

    Grid<Restaurant> grid = new Grid<>(Restaurant.class);
    RestaurantService service;
    
    private Button help = new Button("?");
    private Button closeButton = new Button("x");
    private Notification notification = new Notification();
    private Text text = new Text ("Here you can see all the restaurants listed with their ratings, costs, location and cuisine types. You can also search restaurants by cuisine.\n");


    public RestaurantsView(RestaurantService service) {

        this.service = service;
        setSizeFull();
        configureGrid();
        add(cuisine, search, grid);
        add(help);
        setList();

        search.addClickListener(e ->{

            updateList();
            add(grid);

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

    private void configureGrid() {
        grid.addClassNames("restaurant-grid");
        grid.setSizeFull();
        grid.setColumns("name", "address", "postalCode", "cuisine", "location", "cost");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {

        if(cuisine== null|| cuisine.getValue().equals("")){
            List<Restaurant> list = service.findAll();
            grid.setItems(list);

        }
        else{
            List<Restaurant> list = service.findCuisine(cuisine.getValue().toLowerCase());
            grid.setItems(list);
        }

    }

    private void setList(){
        List<Restaurant> list = service.findAll();
        grid.setItems(list);
    }

}
