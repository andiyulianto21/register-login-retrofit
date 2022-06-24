package pelaporan.kebakaran.logindandaftar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.HashMap;

import pelaporan.kebakaran.logindandaftar.Api.RegisterApi;
import pelaporan.kebakaran.logindandaftar.DataModel.RegisterModel;
import pelaporan.kebakaran.logindandaftar.Response;
import pelaporan.kebakaran.logindandaftar.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(view -> {
            username = binding.inputUsername.getEditText().getText().toString();
            password = binding.inputPasssword.getEditText().getText().toString();

            sendUser(username, password);
        });

        binding.btnLogin.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
    }

    private void sendUser(String username, String password) {

        RegisterApi api = RetrofitBuilder.getRetrofit().create(RegisterApi.class);
        HashMap<String, String> register = new HashMap<>();
        register.put("username", username);
        register.put("password", password);
        Call<Response> call = api.sendUser(register);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if(response.body().getResponse().equals("exist")){
                    Toast.makeText(MainActivity.this, "Username already exist, try again!", Toast.LENGTH_SHORT).show();
                }else if(response.body().getResponse().equals("failed")){
                    Toast.makeText(MainActivity.this, "Failed to create new Account, try again!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Success make new Account!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}