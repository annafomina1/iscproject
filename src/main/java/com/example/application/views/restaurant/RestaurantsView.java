package com.example.application.views.restaurant;

import com.example.application.data.entity.Restaurant;
import com.example.application.data.service.RestaurantService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import java.util.List;

@PageTitle("Restaurant List")
@Route(value = "restaurants", layout = MainLayout.class)
public class RestaurantsView extends VerticalLayout {

    private TextField cuisine = new TextField("Cuisine");
    private Button search = new Button("Search");

    Grid<Restaurant> grid = new Grid<>(Restaurant.class);
    RestaurantService service;

    public RestaurantsView(RestaurantService service) {

        this.service = service;
        setSizeFull();
        configureGrid();
        add(cuisine, search, grid);
        setList();

        search.addClickListener(e ->{

            updateList();
            add(grid);

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
