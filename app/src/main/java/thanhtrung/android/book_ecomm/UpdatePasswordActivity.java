package thanhtrung.android.book_ecomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import thanhtrung.android.book_ecomm.model.User;

public class UpdatePasswordActivity extends AppCompatActivity {

    TextView tvOPassword, tvNPassword, tvCPassword;
    Button updatePassword;
    ImageView btnBackToUser;

    String uID, oldPassword, newPassword, confirmPassword, pass;

    FirebaseDatabase fDatabase;
    FirebaseFirestore fFirestore;
    FirebaseAuth fAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();
        fFirestore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        uID = fAuth.getCurrentUser().getUid();

        tvOPassword = findViewById(R.id.password1);
        tvNPassword = findViewById(R.id.password2);
        tvCPassword = findViewById(R.id.password3);
        btnBackToUser = findViewById(R.id.backtouser);
        updatePassword = findViewById(R.id.btn_update_password);

        btnBackToUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdatePasswordActivity.this, UserFragment.class);
                startActivity(i);
                finish();
            }
        });

        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldPassword = tvOPassword.getText().toString();
                newPassword = tvNPassword.getText().toString();
                confirmPassword = tvCPassword.getText().toString();

                if (TextUtils.isEmpty((oldPassword))) {
                    tvOPassword.setError("Không được để trống.");
                    return;
                }

                if (TextUtils.isEmpty((newPassword))) {
                    tvNPassword.setError("Không được để trống.");
                    return;
                }

                if (TextUtils.isEmpty((confirmPassword))) {
                    tvCPassword.setError("Không được để trống.");
                    return;
                }

                if (newPassword.length() < 6) {
                    tvNPassword.setError("Mật khẩu phải có 6 kí tự trở lên.");
                    return;
                }

                if (oldPassword.equals(newPassword)) {
                    tvNPassword.setError("Mật khẩu mới không được giống mật khẩu cũ");
                    return;
                }

                if (!newPassword.equals(confirmPassword)) {
                    tvCPassword.setError("Xác nhận mật khẩu không đúng");
                    return;
                }

                user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UpdatePasswordActivity.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UpdatePasswordActivity.this, "Cập nhật mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}