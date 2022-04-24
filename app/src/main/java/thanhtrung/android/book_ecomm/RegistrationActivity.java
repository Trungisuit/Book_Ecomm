package thanhtrung.android.book_ecomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    EditText ifullname, iemail, iphone, ipassword, iconfirmpassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ifullname = findViewById(R.id.fullname);
        iemail = findViewById(R.id.email);
        iphone = findViewById(R.id.phone);
        ipassword = findViewById(R.id.password);
        iconfirmpassword = findViewById(R.id.password2);

        btnRegister = findViewById(R.id.signupbtn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });
    }

    private void PerforAuth() {
        String email=iemail.getText().toString();
        String password=ipassword.getText().toString();
        String confirmpassword=iconfirmpassword.getText().toString();
    }

    public void signin(View view) {
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }

    public void signup(View view) {
        startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
    }
}