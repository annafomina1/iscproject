package com.example.application.views.login;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.example.application.data.service.AuthService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

/*
The page view for the login form, gets username and password from the user and uses AuthService to auntheticate their input
Created By: Harper Rapkin
Date Created: 04/01/2022
Date Last Edited: 04/01/2022
 */
@PageTitle("Login Form")
@Route(value="login", layout=MainLayout.class)
public class LoginView extends VerticalLayout{

    //creating an AuthService object
    AuthService authService;
    
    //definning the fields
    private Button help = new Button("?");//help button
    private Button closeButton = new Button("x");//close for the help notification
    private Notification notification = new Notification();//creating a notification object
    private Text text = new Text ("If you have made an account, login here.");//the text that appears on the popup window

    /**
    LoginView constructor that connects to AuthService, authenticates the user, and has option to show help notification
    @param authService the AuthService
    */
    public LoginView(AuthService authService){
        //defining the authService
        this.authService = authService;
        setId("login-view");
        
        //creating the objects for the username and password form
        var username = new TextField("Username");
        var password = new PasswordField("Password");
        //adding a header, the username and password fillable elements, and a button to login
        add(
                new com.vaadin.flow.component.Component[]{new H1("Login"), username, password, new Button("Login", event -> {

                    try {
                        //if the login is successful
                        authService.authenticate(username.getValue(), password.getValue());
                        Notification notification = Notification.show("Login Successful");
                        username.clear();
                        password.clear();
                        
                    } catch (AuthService.AuthException e) {
                        //if the login is unsuccessful
                        Notification notification = Notification.show("Login Unsuccessful");
                        username.clear();
                        password.clear();
                    }

                })});
        //adding the button to access the help popup window
        add(help);

        //setting the attributes and actions for the button
        Button closeButton = new Button("x");
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });

        //setting the attributes and actions for the button, adding the notification when the button is clicked
        help.addClickListener(e ->{
            HorizontalLayout layout = new HorizontalLayout(text, closeButton);
            notification.add(layout);
            notification.open();

        });
    }
}
