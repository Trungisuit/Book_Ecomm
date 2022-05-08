package thanhtrung.android.book_ecomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import thanhtrung.android.book_ecomm.model.User;

public class RegistrationActivity extends AppCompatActivity {

    EditText iFullname, iEmail, iPhone, iPassword, iConfirmpassword;
    Button btnRegister;
    TextView btnLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        iFullname = findViewById(R.id.fullname);
        iEmail = findViewById(R.id.email);
        iPhone = findViewById(R.id.phone);
        iPassword = findViewById(R.id.password);
        iConfirmpassword = findViewById(R.id.password2);
        btnRegister = findViewById(R.id.register);
        btnLogin = findViewById(R.id.login);
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class ));
            finish();
        }



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = iEmail.getText().toString().trim();
                String password = iPassword.getText().toString().trim();
                String phone = iPhone.getText().toString().trim();
                String cfrpassword = iConfirmpassword.getText().toString().trim();

                if (TextUtils.isEmpty((email))){
                    iEmail.setError("Không được để trống.");
                    return;
                }

                if (TextUtils.isEmpty((password))){
                    iPassword.setError("Không được để trống.");
                    return;
                }

                if (password.length() < 6){
                    iPassword.setError("Mật khẩu phải có 6 kí tự trở lên.");
                    return;
                }

                if (!password.equals(cfrpassword)) {
                    iConfirmpassword.setError("Xác nhận mật khẩu không đúng");
                    return;
                }

                if (phone.length() < 6){
                    iPassword.setError("SĐT phải đủ 10 số.");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_LONG).show();
                            storeNewUserData();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else {
                            Toast.makeText(RegistrationActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    private void storeNewUserData(){
        String fullname = iFullname.getText().toString();
        String email = iEmail.getText().toString();
        String password = iPassword.getText().toString();
        String phone = iPhone.getText().toString();
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance("https://book-ecomm-39b02-default-rtdb.firebaseio.com/");
        DatabaseReference reference = rootNode.getReference("Users");

        User addNewUser = new User(fullname, email, phone, password);
        reference.setValue(addNewUser);
    }
}