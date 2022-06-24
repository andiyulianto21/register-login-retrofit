package pelaporan.kebakaran.logindandaftar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.HashMap;
import java.util.Map;

import pelaporan.kebakaran.logindandaftar.Api.RegisterApi;
import pelaporan.kebakaran.logindandaftar.databinding.ActivityLoginBinding;
import pelaporan.kebakaran.logindandaftar.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {


    ActivityLoginBinding bindings;
    private String username, password;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindings = ActivityLoginBinding.inflate(LayoutInflater.from(LoginActivity.this));
        setContentView(bindings.getRoot());

        count = 0;

        bindings.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = bindings.loginUsername.getEditText().getText().toString();
                password = bindings.loginPasssword.getEditText().getText().toString();

                if(count > 2){
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(LoginActivity.this)
                            .setPositiveButton("go to Register Page", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                }
                            })
                            .setNegativeButton("no", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setMessage("Please Register if you don't have a account!");
                    builder.create().show();
                }
                validationLogin();
            }
        });
    }

    private void validationLogin() {

        Map<String, String> param = new HashMap<>();
        param.put("username_login", username);
        param.put("password_login", password);
        RegisterApi api = RetrofitBuilder.getRetrofit().create(RegisterApi.class);

        Call<Response> call = api.validateLogin(param);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.body().getResponse().equals("login success")){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                }else if(response.body().getResponse().equals("username or password invalid")){
                    Toast.makeText(LoginActivity.this, "Username or Password Invalid", Toast.LENGTH_SHORT).show();
                    count++;
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}