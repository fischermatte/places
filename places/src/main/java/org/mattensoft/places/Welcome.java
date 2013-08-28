package org.mattensoft.places;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class Welcome implements Serializable{
    
    private String welcomeMessage = "Hello Places";

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
