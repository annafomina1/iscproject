package com.example.application.views.search;

//import com.example.application.data.entity.Restaurant;
//import com.example.application.data.service.RestaurantService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;

@PageTitle("Search")
@Route(value = "search", layout = MainLayout.class)
@Uses(Icon.class)

public class Search extends Div {

    // creates button objects for the search button
    private Button search = new Button("Search");


    public Search() {


        addClassName("search");

        search.addClickListener(e -> {
            System.out.println("");

            int numRestaurants = 8;
            //get restaurants
            String[] restaurants = new String[] {"Abby", "Bob", "Cale", "Dave", "Egg", "Fame", "Greg", "Hippo"};

            findWord(numRestaurants, restaurants);
        });

    }

    public static void findWord(int numRestaurants, String[] restaurants) {

        //variables
        int halfNum = 0; //NOTE: halfNum represents the location of the word in the middle of the alphabetically sorted list
        boolean odd = false;
        boolean found = false;
        String[] newRestaurants;

        var search = new TextField("search");
        String searchWord = String.valueOf(search);

        //while loop continues until the word is found
        while (!found) {

            //if there is an even number of words, the halfNum is the number right in the middle
            if (numRestaurants % 2 == 0) {
                halfNum = numRestaurants / 2;
                numRestaurants = halfNum;
                odd = false;
            }

            //if there is an odd number of words, the halfNum is the lower out of the middle two numbers.
            else {
                halfNum = (numRestaurants - 1) / 2;
                odd = true;
            }

            //if the word in the middle matches the user's word, it prints the definition and the while loop can end
            if (restaurants[halfNum].startsWith(searchWord)) {
                System.out.println(restaurants[halfNum]);
                found = true;
                numRestaurants = 0;
            }

            //if the middle word comes BEFORE the user's word in the alphabet.
            else if (restaurants[halfNum].compareTo(searchWord) > 0) {

                //changes the amount of possible words
                numRestaurants = halfNum;

                //copies the possible words(all BEFORE the middle word) into a new array
                newRestaurants = new String[numRestaurants];
                for (int i = 0; i < numRestaurants; i++) {
                    newRestaurants[i] = restaurants[i];
                }
                //replaces old array with contents of new array, eliminating words that don't match.
                restaurants = new String[numRestaurants];
                restaurants = newRestaurants;
            }

            //if the middle word comes AFTER the user's word in the alphabetically sorted array
            else if (restaurants[halfNum].compareTo(searchWord) < 0) {

                //if the amount of words left was odd...
                if (odd) {
                    // +1 is added to the amount of words (representing the extra middle number behind the chosen middle number)
                    numRestaurants = halfNum + 1;

                    newRestaurants = new String[numRestaurants];
                    for (int i = 0; i < numRestaurants; i++) {
                        newRestaurants[i] = restaurants[i + halfNum];
                    }

                    //replaces the old array with the contents of the new one
                    restaurants = new String[numRestaurants];
                    restaurants = newRestaurants;

                }

                else {

                    numRestaurants = halfNum;


                    newRestaurants = new String[numRestaurants];
                    for (int i = 0; i < numRestaurants; i++) {
                        newRestaurants[i] = restaurants[i + halfNum];
                    }

                    //replaces old array with new array
                    restaurants = new String[numRestaurants];
                    restaurants = newRestaurants;
                }

            }
            if (halfNum == 0 && !(restaurants[0].startsWith(searchWord))) {
                System.out.println("No restaurants found");
                found = true;
            }

        }//end of while loop
    }

}