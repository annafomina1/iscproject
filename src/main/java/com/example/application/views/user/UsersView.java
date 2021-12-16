package com.example.application.views.user;

import com.example.application.data.entity.Restaurant;
import com.example.application.data.entity.User;
import com.example.application.data.service.RestaurantService;
import com.example.application.data.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import com.vaadin.flow.router.RouteAlias;

import java.util.List;

@PageTitle("User List")
@Route(value = "users", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class UsersView extends VerticalLayout {
    Grid<User> grid = new Grid<>(User.class);
    UserService service;


    public UsersView(UserService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        updateList();

        add(grid);
    }

    private void configureGrid() {
        grid.addClassNames("user-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email", "username", "password", "postalCode", "dateOfBirth");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {
        List<User> list = service.findAll();//null was a param
        grid.setItems(list);
    }

}
