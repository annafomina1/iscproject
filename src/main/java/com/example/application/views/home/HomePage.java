package com.example.application.views.home;

//imports
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import com.vaadin.flow.router.RouteAlias;

/*
The Home Page
Created By: Ava Donaldson
 */

//page title and route
@PageTitle("Home")
@Route(value = "home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)

//HomePage
public class HomePage extends VerticalLayout {

    /**
     * HomePage
     */

    public HomePage() {
        setSizeFull();

        //gets the image of the skibble logo
        Image img = new Image("images/skibble-logo.png", "skibble logo");

        //sets image sizing
        img.setWidth("400px");
        img.setHeight("300px");

        //adds the image to the view
        add(img);

        //sets size and aligns page
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }


}//end
