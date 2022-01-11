package com.example.application.views.home;

import com.example.application.data.entity.Restaurant;
//import com.example.application.data.service.RestaurantService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;

import java.util.List;

@PageTitle("Home")
@Route(value = "home", layout = MainLayout.class)
public class HomePage extends VerticalLayout {
    //Grid<HomePage> grid = new Grid<>(HomePage.class);
    //HomePageHome home;

    public HomePage() {
        setSizeFull();

        //configureGrid();

       // add(grid);
        Image img = new Image("images/skibble-logo.png", "skibble logo");
        img.setWidth("400px");
        img.setHeight("300px");
        add(img);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }


}
