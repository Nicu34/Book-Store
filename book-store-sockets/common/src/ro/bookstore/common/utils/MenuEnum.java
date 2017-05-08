package ro.bookstore.common.utils;

import java.io.Serializable;

/**
 * Created by nicu on 4/24/2017.
 */
public enum MenuEnum implements Serializable {
    ADD_BOOK("ADD"), DELETE_BOOK("DELETE_BOOK"), UPDATE_BOOK("UPDATE_BOOK"), FILTER_BOOKS("FILTER_BOOKS"), GET_BOOKS("GET_BOOKS"), FIND_BOOK("FIND_BOOK"),
    ADD_SALE("ADD"), DELETE_SALE("DELETE_SALE"), UPDATE_SALE("UPDATE_SALE"), FILTER_SALES("FILTER_SALES"), GET_SALES("GET_SALES"), FIND_SALE("FIND_SALE"),
    ADD_CLIENT("ADD"), DELETE_CLIENT("DELETE_CLIENT"), UPDATE_CLIENT("UPDATE_CLIENT"), FILTER_CLIENTS("FILTER_CLIENTS"), GET_CLIENTS("GET_CLIENTS"), FIND_CLIENT("FIND_CLIENT"),
    SORT_CLIENTS("SORT_CLIENTS"), BUY("BUY"), OK("OK"), ERROR("ERROR");

    private String action;

    MenuEnum(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
