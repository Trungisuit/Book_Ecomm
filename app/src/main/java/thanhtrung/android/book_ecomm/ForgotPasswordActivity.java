package thanhtrung.android.book_ecomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText iEmail;
    Button btnConfirm;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        iEmail = findViewById(R.id.email);
        fAuth = FirebaseAuth.getInstance();
        btnConfirm = findViewById(R.id.confirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = iEmail.getText().toString().trim();

                if (TextUtils.isEmpty((email))){
                    iEmail.setError("Không được để trống.");
                    return;
                }
                fAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ForgotPasswordActivity.this, "Vui lòng kiểm tra email của bạn.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgotPasswordActivity.this, "Có lỗi xảy ra." + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}