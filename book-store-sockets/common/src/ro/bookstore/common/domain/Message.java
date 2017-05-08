package ro.bookstore.common.domain;

import ro.bookstore.common.utils.MenuEnum;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Message implements Serializable {

    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    private MenuEnum menuEnum;
    private String dataMessage;

    public Message(MenuEnum menuEnum, String dataMessage) {
        this.menuEnum = menuEnum;
        this.dataMessage = dataMessage;
    }

    public Message() {
    }

    public MenuEnum getMenuEnum() {
        return menuEnum;
    }

    public void setMenuEnum(MenuEnum menuEnum) {
        this.menuEnum = menuEnum;
    }

    public String getDataMessage() {
        return dataMessage;
    }

    public void setDataMessage(String dataMessage) {
        this.dataMessage = dataMessage;
    }

    @Override
    public String toString() {
        return "Message{" +
                "menuEnum=" + menuEnum +
                ", dataMessage='" + dataMessage + '\'' +
                '}';
    }
}
