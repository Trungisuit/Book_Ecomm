package thanhtrung.android.book_ecomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

import thanhtrung.android.book_ecomm.model.User;

public class RegistrationActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    public static final String TAG1 = "TAG";
    EditText iFullname, iEmail, iPhone, iAddress, iPassword, iConfirmpassword;
    Button btnRegister;
    TextView btnLogin;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        iFullname = findViewById(R.id.fullname);
        iEmail = findViewById(R.id.email);
        iPhone = findViewById(R.id.phone);
        iAddress = findViewById(R.id.address);
        iPassword = findViewById(R.id.password);
        iConfirmpassword = findViewById(R.id.password2);
        btnRegister = findViewById(R.id.register);
        btnLogin = findViewById(R.id.login);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class ));
            finish();
        }



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = iFullname.getText().toString().trim();
                String email = iEmail.getText().toString().trim();
                String phone = iPhone.getText().toString().trim();
                String address = iAddress.getText().toString().trim();
                String password = iPassword.getText().toString().trim();
                String cfrpassword = iConfirmpassword.getText().toString().trim();

                if (TextUtils.isEmpty((name))){
                    iFullname.setError("Không được để trống.");
                    return;
                }

                if (TextUtils.isEmpty((email))){
                    iEmail.setError("Không được để trống.");
                    return;
                }

                if (TextUtils.isEmpty((address))){
                    iAddress.setError("Không được để trống.");
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

                if (phone.length() < 10){
                    iPhone.setError("SĐT phải đủ 10 số.");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_LONG).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, String> user = new HashMap<>();
                            user.put("Password", password);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: user profile is created for" + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG1, "onFailure: " + e.toString());
                                }
                            });
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
        String address = iAddress.getText().toString();
        String phone = iPhone.getText().toString();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");

        HashMap<String,Object> data = new HashMap<>();
        data.put("ID",userID);
        data.put("Name",fullname);
        data.put("Email", email);
        data.put("Phone",phone);
        data.put("Address", address);
        reference.child(userID).child("Info").setValue(data);
    }
}