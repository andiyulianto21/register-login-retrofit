package pelaporan.kebakaran.logindandaftar.DataModel;

import com.google.gson.annotations.SerializedName;

public class RegisterModel {

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public RegisterModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
