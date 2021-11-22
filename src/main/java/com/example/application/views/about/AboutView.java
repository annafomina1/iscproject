package com.example.application.views.about;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import com.vaadin.flow.server.StreamResource;


@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
        setSpacing(false);

        String team = "\nProject Manager: 	 Ava Donaldson \nSystems Analyst: 	 Harper Rapkin\nLead Programmer: 	 Anna Fomina \nGraphic Artist: 	 Christine Shao \nTechnical Writer: 	 Ava Donaldson\n";
        String missionStatement = "\n''Our project aims to assist users in finding an ideal restaurant within their postal code and of their preferred cuisine.''\n";
        String about = "\nHave you ever wanted to eat out but couldn’t decide where to go? Have you wanted to eat a certain cuisine but struggled to find a nearby location? Our organization aims to help make these decisions simpler through our brand new program!\n";

//
//        Image img = new Image("images/skibble-logo.png", "skibble logo");
//        img.setWidth("140px");
//        add(img);

        add(new H1("Welcome to Skibble!"));
        add(new H2(missionStatement));
        add(new H3(about));
        add(new Paragraph("Project Manager:   Ava Donaldson"));
        add(new Paragraph("Systems Analyst:   Harper Rapkin"));
        add(new Paragraph("Lead Programmer:   Anna Fomina"));
        add(new Paragraph("Graphic Artist:   Christine Shao"));
        add(new Paragraph("Technical Writer:   Ava Donaldson"));
        add(new Paragraph("Contact Information:"));
        add(new Paragraph("SkibbleHelp@Skibble.com         123-456-7890"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
