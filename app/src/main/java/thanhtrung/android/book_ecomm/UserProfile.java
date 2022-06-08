package thanhtrung.android.book_ecomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import thanhtrung.android.book_ecomm.model.Cart;
import thanhtrung.android.book_ecomm.model.Product;
import thanhtrung.android.book_ecomm.model.User;

public class UserProfile extends AppCompatActivity {

    TextView tvUserName, userName, userEmail, userPhone, userAddress;
    Button btnUpdate;
    String uID, uName, uEmail, uPhone, uAddress;
    FirebaseDatabase fDatabase;
    FirebaseFirestore fFirestore;
    FirebaseAuth fAuth;
    DatabaseReference reference;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();
        fFirestore = FirebaseFirestore.getInstance();
        uID = fAuth.getCurrentUser().getUid();

        tvUserName = findViewById(R.id.tv_fullname);
        btnUpdate = findViewById(R.id.btn_update);

        userName = findViewById(R.id.et_fullname);
        userEmail = findViewById(R.id.et_email);
        userPhone = findViewById(R.id.et_phone);
        userAddress = findViewById(R.id.et_address);
        UserProfileView();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uName = userName.getText().toString();
                uPhone = userPhone.getText().toString();
                uAddress = userAddress.getText().toString();

                if (TextUtils.isEmpty((uName))){
                    userName.setError("Không được để trống.");
                    return;
                }

                if (TextUtils.isEmpty((uPhone))){
                    userPhone.setError("Không được để trống.");
                    return;
                }

                if (TextUtils.isEmpty((uAddress))){
                    userAddress.setError("Không được để trống.");
                    return;
                }

                if (uPhone.length() < 10){
                    userPhone.setError("SĐT phải đủ 10 số.");
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("Name", uName);
                hashMap.put("Phone", uPhone);
                hashMap.put("Address", uAddress);
                fDatabase.getReference("users").child(uID).child("Info").updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(UserProfile.this,"Cập nhật thông tin thành công",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void UserProfileView() {
        reference  = fDatabase.getReference("users").child(uID).child("Info");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    user = snapshot.getValue(User.class);
                    tvUserName.setText(user.getName());
                    userName.setText(user.getName());
                    userEmail.setText(user.getEmail());
                    userPhone.setText(user.getPhone());
                    userAddress.setText(user.getAddress());
                    String log = "Id: " + uID + " ,Name: " + user.getName() + ",Email: " + user.getEmail() + "phone: " + user.getPhone()
                            + ",address: " + user.getAddress();
                    Log.e("User : ", log);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}