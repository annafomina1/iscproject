package com.example.application.views.rating;
import com.example.application.data.entity.AverageRating;
import com.example.application.data.entity.Rating;
import com.example.application.data.service.RatingService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;

import java.util.List;

@PageTitle("Restaurant Ratings")
@Route(value = "ratings", layout = MainLayout.class)
public class RatingsView extends VerticalLayout{

    Grid<AverageRating> grid = new Grid<>(AverageRating.class);
    RatingService service;

    public RatingsView(RatingService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        updateList();

        add(grid);
    }

    private void configureGrid() {
        grid.addClassNames("ratings-grid");
        grid.setSizeFull();
        grid.setColumns("name", "value", "count");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {
        List<AverageRating> list = service.getAverageRatings();
        grid.setItems(list);
    }


}
