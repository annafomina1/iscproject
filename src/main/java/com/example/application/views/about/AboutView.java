package com.example.application.views.about;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout {
    
    //creating the field objects for the help popup window system (Harper Rapkin)
    private Button help = new Button("?");//help button
    private Button closeButton = new Button("x");//close for the help notification
    private Notification notification = new Notification();//creating a notification object
    //text that appears on the popup window
    private Text text = new Text ("Welcome to Skibble, a convenient restaurant finder that meets your preferences and needs! Start off by making a new account in the new user form tab. From there you can check out the restaurants in the Restaurants List and add ratings in the Rating Form. \n");


    public AboutView() {
        setSpacing(false);

        String team = "\nProject Manager: 	 Ava Donaldson \nSystems Analyst: 	 Harper Rapkin\nLead Programmer: 	 Anna Fomina \nGraphic Artist: 	 Christine Shao \nTechnical Writer: 	 Ava Donaldson\n";
        String missionStatement = "\n''Our project aims to assist users in finding an ideal restaurant within their postal code and of their preferred cuisine.''\n";
        String about = "\nHave you ever wanted to eat out but couldn’t decide where to go? Have you wanted to eat a certain cuisine but struggled to find a nearby location? Our organization aims to help make these decisions simpler through our brand new program!\n";
        String space = "\n";

//
        Image img = new Image("images/skibble-logo.png", "skibble logo");
        img.setWidth("300px");
        img.setHeight("300px");
        //add(img);


        add(new Paragraph(space));
        add(new Paragraph(space));
        add(new H1("Welcome to Skibble!"));
        add(img);
        add(new Paragraph(about));
        add(new H2(missionStatement));
        add(new Paragraph(space));
        add(new Paragraph(space));
        add(new Paragraph("Project Manager:   Ava Donaldson"));
        add(new Paragraph("Systems Analyst:   Harper Rapkin"));
        add(new Paragraph("Lead Programmer:   Anna Fomina"));
        add(new Paragraph("Graphic Artist:   Christine Shao"));
        add(new Paragraph("Technical Writer:   Ava Donaldson"));
        add(new Paragraph(space));
        add(new Paragraph(space));
        add(new Paragraph("Contact Information:"));
        add(new Paragraph("SkibbleHelp@Skibble.com         123-456-7890"));
        add(help); //adding the button to access the help popup window (Harper Rapkin)

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
        
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

}
