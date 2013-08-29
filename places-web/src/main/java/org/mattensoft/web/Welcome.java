package org.mattensoft.web;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class Welcome {

    private String message = "Hello Java EE 7";

    public String getMessage() {
        return message;
    }
}
