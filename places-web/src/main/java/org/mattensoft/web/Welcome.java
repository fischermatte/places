package org.mattensoft.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Welcome {

    private String message = "Hello Java EE 7";

    public String getMessage() {
        return message;
    }
}
