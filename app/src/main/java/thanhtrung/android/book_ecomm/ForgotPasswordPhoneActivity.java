package thanhtrung.android.book_ecomm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class ForgotPasswordPhoneActivity extends AppCompatActivity {

    EditText iPhone;
    Button btnConfirm;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_phone);

        iPhone = findViewById(R.id.phone);
        fAuth = FirebaseAuth.getInstance();
        btnConfirm = findViewById(R.id.confirm);

    }

    public void verifyPhoneNumber(View view){
        String phone = iPhone.getText().toString().trim();

        if (TextUtils.isEmpty((phone))){
            iPhone.setError("Không được để trống.");
            return;
        }

        if (phone.length() != 10){
            iPhone.setError("Vui lòng nhập đúng định dạng.");
            return;
        }

        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNum").equalTo(phone);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    iPhone.setError(null);

                    Intent intent = new Intent(getApplicationContext(), Confirm_resetpassword.class);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                    finish();

                } else {
                    iPhone.setError("Số điện thoại không tồn tại.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}