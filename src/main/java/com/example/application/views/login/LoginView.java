package com.example.application.views.login;

import com.example.application.views.MainLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.example.application.data.service.AuthService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.notification.Notification;

@PageTitle("Login Form")
@Route(value="login", layout=MainLayout.class)
public class LoginView extends VerticalLayout{

    AuthService authService;

    public LoginView(AuthService authService){
        this.authService = authService;
        setId("login-view");
        var username = new TextField("Username");
        var password = new PasswordField("Password");
        add(
                new com.vaadin.flow.component.Component[]{new H1("login"), username, password, new Button("Login", event -> {

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
    }
}
