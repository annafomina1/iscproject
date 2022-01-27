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

@PageTitle("Login Form")
@Route(value="login", layout=MainLayout.class)
public class LoginView extends VerticalLayout{

    AuthService authService;
    
    private Button help = new Button("?");
    private Button closeButton = new Button("x");
    private Notification notification = new Notification();
    private Text text = new Text ("If you have made an account, login here.");

    public LoginView(AuthService authService){
        this.authService = authService;
        setId("login-view");
        var username = new TextField("Username");
        var password = new PasswordField("Password");
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
}
