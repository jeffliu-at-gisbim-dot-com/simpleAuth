/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisbim.simpleAuthWeb.uiController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeff
 */
public class LogicException extends Exception{
    private List<String> messages;
    public LogicException(String messages) {
        super(messages);
    }
    public LogicException(List<String> messages) {
        super((messages != null && messages.size() > 0 ? messages.get(0) : null));
        if (messages == null) {
            this.messages = new ArrayList<String>();
        }
        else {
            this.messages = messages;
        }
    }
    public List<String> getMessages() {
        return messages;
    }  
}
