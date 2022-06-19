package thanhtrung.android.book_ecomm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.OrderAdapter;
import thanhtrung.android.book_ecomm.model.Cart;

public class PurchasedItemActivity extends AppCompatActivity {

    RecyclerView rcvItem;
    OrderAdapter mOrderAdapter;
    TextView tvTotal, tvDelivery, tvAll, tvPurchasedId, tvOrderDate;
    String userID, listID;
    int totalPriceProduct;
    List<Cart> mListOrder;
    FirebaseDatabase fDatabase;
    FirebaseFirestore fFirestore;
    FirebaseAuth fAuth;
    DatabaseReference reference, reference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased_item);

        Intent i = getIntent();
        listID = i.getStringExtra("id");

        rcvItem = findViewById(R.id.recyclerView1);
        tvPurchasedId = findViewById(R.id.tv_orderid);
        tvTotal = findViewById(R.id.totalFeeTxt);
        tvDelivery = findViewById(R.id.deliveryTxt);
        tvAll = findViewById(R.id.totalTxt);
        tvOrderDate  = findViewById(R.id.tv_order_date);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();
        fFirestore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        mListOrder =new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext(), RecyclerView.VERTICAL, false);
        rcvItem.setLayoutManager(linearLayoutManager);
        mOrderAdapter = new OrderAdapter(mListOrder, this.getApplicationContext());
        tvPurchasedId.setText(listID);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String dateString = formatter.format(new Date(Long.parseLong(listID)));
        tvOrderDate.setText(dateString);

        getlistProduct();
        rcvItem.setAdapter(mOrderAdapter);
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
                if (cart == null || mListOrder == null || mListOrder.isEmpty())
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

    private void getlistProduct(){

        reference  = fDatabase.getReference("users").child(userID).child("Purchased").child(listID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
                    Cart cart = dataSnapshot.getValue(Cart.class);
                    String log = "Id: "+cart.getProductID()+" ,Name: " + cart.getProductName() + ",Price: " + cart.getProductPrice();
                    Log.e("Product : ", log);
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