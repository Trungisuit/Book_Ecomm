package thanhtrung.android.book_ecomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_up);
        startActivity(new Intent(SplashUpActivity.this, Dashboard1.class));
        finish();
    }
}