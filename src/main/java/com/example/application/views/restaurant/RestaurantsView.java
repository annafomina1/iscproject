package com.example.application.views.restaurant;

import com.example.application.data.entity.Restaurant;
import com.example.application.data.service.RestaurantService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;

import java.util.List;

@PageTitle("Restaurant List")
@Route(value = "restaurants", layout = MainLayout.class)
public class RestaurantsView extends VerticalLayout {
    Grid<Restaurant> grid = new Grid<>(Restaurant.class);
    RestaurantService service;

    public RestaurantsView(RestaurantService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        updateList();

        add(grid);
    }

    private void configureGrid() {
        grid.addClassNames("restaurant-grid");
        grid.setSizeFull();
        grid.setColumns("name", "address", "postalCode", "cuisine", "location", "cost");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {
        List<Restaurant> list = service.findAll();
        grid.setItems(list);
    }

}
