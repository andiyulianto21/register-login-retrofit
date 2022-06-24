package pelaporan.kebakaran.logindandaftar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import pelaporan.kebakaran.logindandaftar.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
}