package kz.akbar.efc.phonebook.model;

public enum UserRequestParam {
    SAVE("save"),
    DELETE("delete"),
    GET("get");

    private String name;

    UserRequestParam(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
