package thanhtrung.android.book_ecomm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.OrderAdapter;
import thanhtrung.android.book_ecomm.adapter.PurchasedAdapter;
import thanhtrung.android.book_ecomm.model.Cart;
import thanhtrung.android.book_ecomm.model.Purchased;

public class PurchaseHistory extends AppCompatActivity {

    RecyclerView rcvPurchased;
    PurchasedAdapter mPurchasedAdapter;
    String userID;
    List<Purchased> mListPurchased;
    FirebaseDatabase fDatabase;
    FirebaseFirestore fFirestore;
    FirebaseAuth fAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        rcvPurchased = findViewById(R.id.rcv_cart);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();
        fFirestore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        mListPurchased =new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext(), RecyclerView.VERTICAL, false);
        rcvPurchased.setLayoutManager(linearLayoutManager);
        mPurchasedAdapter = new PurchasedAdapter(mListPurchased, this.getApplicationContext());

        getlistPurchased();
        rcvPurchased.setAdapter(mPurchasedAdapter);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                mPurchasedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Purchased purchased = snapshot.getValue(Purchased.class);
                mListPurchased.clear();
                if(purchased == null || mListPurchased == null || mListPurchased.isEmpty())
                {
                    return;
                }
                for (int i = 0 ; i < mListPurchased.size() ; i++)
                {
                    if(purchased.getId() == mListPurchased.get(i).getId()) {
                        mListPurchased.set(i,purchased);
                        break;
                    }
                }
                mPurchasedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Purchased purchased = snapshot.getValue(Purchased.class);
                mListPurchased.clear();
                if(purchased == null || mListPurchased == null || mListPurchased.isEmpty())
                {
                    return;
                }
                for (int i = 0 ; i < mListPurchased.size() ; i++)
                {
                    if(purchased.getId() == mListPurchased.get(i).getId()) {
                        mListPurchased.remove(mListPurchased.get(i));
                        mListPurchased.clear();
                        break;
                    }
                }
                mPurchasedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void getlistPurchased(){

        reference  = fDatabase.getReference("users").child(userID).child("Purchased");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
                    String purchased = dataSnapshot.getKey();
                    String x = "Id: " + purchased;
                    Log.e("Purchased : ", x);
                    mListPurchased.add(new Purchased(purchased));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}