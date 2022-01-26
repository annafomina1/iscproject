package com.example.application.views.user;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import java.util.List;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


@PageTitle("User List")
@Route(value = "users", layout = MainLayout.class)
public class UsersView extends VerticalLayout {
    Grid<User> grid = new Grid<>(User.class);
    UserService service;
    
    private Button help = new Button("?");
    private Button closeButton = new Button("x");
    private Notification notification = new Notification();
    private Text text = new Text ("Here is where all the accounts are stored.");


    public UsersView(UserService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        updateList();

        add(grid);
        add(help);
        
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
