package com.example.application.views.rating;

import com.example.application.data.entity.AverageRating;
import com.example.application.data.service.RatingService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import java.util.List;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@PageTitle("Restaurant Ratings")
@Route(value = "ratings", layout = MainLayout.class)
public class RatingsView extends VerticalLayout{

    Grid<AverageRating> grid = new Grid<>(AverageRating.class);
    RatingService service;
    
    //creating the field objects for the help popup window system (Harper Rapkin)
    private Button help = new Button("?");//help button
    private Button closeButton = new Button("x");//close for help notification
    private Notification notification = new Notification();//creating a notification object
    //text that appears on the popup window
    private Text text = new Text ("Here is the list of restaurants registered with their average ratings and their number of ratings.\n");

    public RatingsView(RatingService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        updateList();

        add(grid);
        add(help); //adding the button to access the help popup window (Harper Rapkin)
        
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
