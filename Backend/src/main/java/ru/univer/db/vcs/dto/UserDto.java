package ru.univer.db.vcs.dto;

public class UserDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private int repo_storage;
    private int history_item;

    public UserDto() {
    }

    public UserDto(int id, String name, String email, String password, int repo_storage, int history_item) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.repo_storage = repo_storage;
        this.history_item = history_item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRepo_storage() {
        return repo_storage;
    }

    public void setRepo_storage(int repo_storage) {
        this.repo_storage = repo_storage;
    }

    public int getHistory_item() {
        return history_item;
    }

    public void setHistory_item(int history_item) {
        this.history_item = history_item;
    }
}
