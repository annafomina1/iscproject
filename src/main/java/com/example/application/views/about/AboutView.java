package com.example.application.views.about;

//imports
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

/*
This class represents the about us page.
Created By: Ava Donaldson
Date Created: 2021-11-01
Date Last Edited: 2022-01-13
 */

//page title and routes (for application)
@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)

public class AboutView extends VerticalLayout {
    
    //creating the field objects for the help popup window system (Harper Rapkin)
    private Button help = new Button("?");//help button
    private Button closeButton = new Button("x");//close for the help notification
    private Notification notification = new Notification();//creating a notification object
    //text that appears on the popup window
    private Text text = new Text ("Welcome to Skibble, a convenient restaurant finder that meets your preferences and needs! Start off by making a new account in the new user form tab. From there you can check out the restaurants in the Restaurants List and add ratings in the Rating Form. \n");

    /**
     * AboutView method
     * Created by: Ava Donaldson
     */

    public AboutView() {
        setSpacing(false);

        //makes strings of information to be easily added to the screen.
        String missionStatement = "\n''Our project aims to assist users in finding an ideal restaurant within their postal code and of their preferred cuisine.''\n";
        String about = "\nHave you ever wanted to eat out but couldn’t decide where to go? Have you wanted to eat a certain cuisine but struggled to find a nearby location? Our organization aims to help make these decisions simpler through our brand new program!\n";
        String space = "\n"; // string space is used to make space between different sections

        //sets up image settings so that we can add the skibble logo
        Image img = new Image("images/skibble-logo.png", "skibble logo");
        img.setWidth("300px");
        img.setHeight("300px");
        add(new Paragraph(space));
        add(new Paragraph(space));

        //heading
        add(new H1("Welcome to Skibble!"));

        //the logo
        add(img);

        //the paragraph about us
        add(new Paragraph(about));

        //our mission statement
        add(new H2(missionStatement));

        //space
        add(new Paragraph(space));
        add(new Paragraph(space));

        //team members
        add(new Paragraph("Project Manager:   Ava Donaldson"));
        add(new Paragraph("Systems Analyst:   Harper Rapkin"));
        add(new Paragraph("Lead Programmer:   Anna Fomina"));
        add(new Paragraph("Graphic Artist:   Christine Shao"));
        add(new Paragraph("Technical Writer:   Ava Donaldson"));

        //space
        add(new Paragraph(space));
        add(new Paragraph(space));

        //contact information
        add(new Paragraph("Contact Information:"));
        add(new Paragraph("SkibbleHelp@Skibble.com         123-456-7890"));

        //help button
        add(help); //adding the button to access the help popup window (Harper Rapkin)

        //centers the alignment of the information on the page (Ava)
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

    }//end aboutView method

}//end class
