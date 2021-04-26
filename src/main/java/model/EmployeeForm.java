package model;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeForm {
    private int id;

    private String name;

    private String date;

    private MultipartFile avatar;
    private boolean gender;

    public EmployeeForm(int id, String name, String date, MultipartFile avatar, boolean gender) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.avatar = avatar;
        this.gender = gender;
    }

    public EmployeeForm() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
