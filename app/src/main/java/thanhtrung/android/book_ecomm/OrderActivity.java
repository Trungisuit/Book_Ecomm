package thanhtrung.android.book_ecomm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.CartAdapter;
import thanhtrung.android.book_ecomm.adapter.OrderAdapter;
import thanhtrung.android.book_ecomm.model.Cart;
import thanhtrung.android.book_ecomm.model.Purchased;
import thanhtrung.android.book_ecomm.model.User;

public class OrderActivity extends AppCompatActivity {

    RecyclerView rcvOrder;
    OrderAdapter mOrderAdapter;
    TextView tvTotal, tvDelivery, tvAll, orderName, orderPhone, orderAddress;
    Button btnConfirm;
    String userID;
    int totalPriceProduct;
    List<Cart> mListOrder;
    FirebaseDatabase fDatabase;
    FirebaseFirestore fFirestore;
    FirebaseAuth fAuth;
    DatabaseReference reference, reference1;

    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderName = findViewById(R.id.tv_user_name);
        orderPhone = findViewById(R.id.tv_user_phone);
        orderAddress = findViewById(R.id.tv_user_address);
        rcvOrder = findViewById(R.id.rcv_order);
        tvTotal = findViewById(R.id.totalFeeTxt);
        tvDelivery = findViewById(R.id.deliveryTxt);
        tvAll = findViewById(R.id.totalTxt);
        btnConfirm = findViewById(R.id.btnConfirm);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();
        fFirestore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        mListOrder =new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext(), RecyclerView.VERTICAL, false);
        rcvOrder.setLayoutManager(linearLayoutManager);
        mOrderAdapter = new OrderAdapter(mListOrder, this.getApplicationContext());
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToHistory();
            }
        });

        getlistProduct();
        getUserProfileView();
        rcvOrder.setAdapter(mOrderAdapter);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                mOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Cart cart = snapshot.getValue(Cart.class);
                mListOrder.clear();
                if(cart == null || mListOrder == null || mListOrder.isEmpty())
                {
                    return;
                }
                for (int i = 0 ; i < mListOrder.size() ; i++)
                {
                    if(cart.getProductID() == mListOrder.get(i).getProductID()) {
                        mListOrder.set(i,cart);
                        break;
                    }
                }
                mOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Cart cart = snapshot.getValue(Cart.class);
                mListOrder.clear();
                if(cart == null || mListOrder == null || mListOrder.isEmpty())
                {
                    return;
                }
                for (int i = 0 ; i < mListOrder.size() ; i++)
                {
                    if(cart.getProductID() == mListOrder.get(i).getProductID()) {
                        mListOrder.remove(mListOrder.get(i));
                        mListOrder.clear();
                        break;
                    }
                }
                mOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void addToHistory() {
        reference  = fDatabase.getReference("users").child(fAuth.getUid()).child("Cart");
        reference1 = fDatabase.getReference("users").child(fAuth.getUid()).child("Purchased");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String timestamps = ""+System.currentTimeMillis();
                reference1.child(timestamps).setValue(snapshot.getValue())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                reference.removeValue();
                                Intent intent = new Intent(OrderActivity.this, PaymentSuccess.class);
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(OrderActivity.this,"Thất Bại",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getUserProfileView() {
        reference  = fDatabase.getReference("users").child(userID).child("Info");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
                orderName.setText(user.getName());
                orderPhone.setText(user.getPhone());
                orderAddress.setText(user.getAddress());
                String log = "Id: " + userID + " ,Name: " + user.getName() + ",Email: " + user.getEmail() + "phone: " + user.getPhone()
                        + ",address: " + user.getAddress();
                Log.e("User : ", log);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void getlistProduct(){
        reference  = fDatabase.getReference("users").child(fAuth.getUid()).child("Cart");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
                    Cart cart = dataSnapshot.getValue(Cart.class);
                    mListOrder.add(cart);
                }
                totalPriceProduct = 0;
                for (int i = 0 ; i < mListOrder.size() ; i++)
                {
                    totalPriceProduct += Integer.parseInt(mListOrder.get(i).getTotalPrice());
                }
                tvTotal.setText(String.valueOf(totalPriceProduct)+" VNĐ");
                tvDelivery.setText(" ");
                if(totalPriceProduct > 300000)
                {
                    tvDelivery.setText("Giao Hàng Miễn Phí ");
                    totalPriceProduct = totalPriceProduct;
                    tvAll.setText(String.valueOf(totalPriceProduct)+" VNĐ");
                }
                else {
                    tvDelivery.setText("20000 VNĐ");
                    totalPriceProduct = totalPriceProduct + 20000;
                    tvAll.setText(String.valueOf(totalPriceProduct)+" VNĐ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}