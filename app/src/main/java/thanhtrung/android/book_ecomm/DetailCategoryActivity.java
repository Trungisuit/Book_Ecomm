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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.ProductAdapter;
import thanhtrung.android.book_ecomm.adapter.ProductCateAdapter;
import thanhtrung.android.book_ecomm.model.Cart;
import thanhtrung.android.book_ecomm.model.Category;
import thanhtrung.android.book_ecomm.model.Product;

public class DetailCategoryActivity extends AppCompatActivity {

    RecyclerView rcvProduct;
    String cateID, cateName;
    TextView cName;
    ImageView back;
    List<Product> mListProduct;
    FirebaseDatabase fDatabase;
    FirebaseFirestore fFirestore;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);

        fFirestore = FirebaseFirestore.getInstance();
        fDatabase = FirebaseDatabase.getInstance();

        mListProduct =new ArrayList<>();

        Intent i = getIntent();
        cateID = i.getStringExtra("id");
        cateName = i.getStringExtra("name");
        rcvProduct = findViewById(R.id.rcv_product);
        cName = findViewById(R.id.tv_name_category);
        back = findViewById(R.id.detail_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CategoryFragment.class);
                startActivity(i);
                finish();
            }
        });

        cName.setText(cateName);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext(), RecyclerView.VERTICAL, false);
        rcvProduct.setLayoutManager(linearLayoutManager);
        ProductCateAdapter productCateAdapter = new ProductCateAdapter(mListProduct, this.getApplicationContext());

        getListProductCate(cateID);
        rcvProduct.setAdapter(productCateAdapter);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                productCateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product product = snapshot.getValue(Product.class);
                mListProduct.clear();
                if(product == null || mListProduct == null || mListProduct.isEmpty())
                {
                    return;
                }
                for (int i = 0 ; i < mListProduct.size() ; i++)
                {
                    if(product.getProductID() == mListProduct.get(i).getProductID()) {
                        mListProduct.set(i,product);
                        break;
                    }
                }
                productCateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Product product = snapshot.getValue(Product.class);
                mListProduct.clear();
                if (product == null || mListProduct == null || mListProduct.isEmpty())
                {
                    return;
                }
                for (int i = 0 ; i < mListProduct.size() ; i++)
                {
                    if(product.getProductID() == mListProduct.get(i).getProductID()) {
                        mListProduct.remove(mListProduct.get(i));
                        mListProduct.clear();
                        break;
                    }
                }
                productCateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void getListProductCate(String id) {

        reference = fDatabase.getReference("productcate").child(id).child("ListProduct");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Product product = dataSnapshot.getValue(Product.class);
                    mListProduct.add(product);
                    String log = "ID: " + product.getProductID() + "Name: " + product.getProductName() + "IMG: " + product.getImg();
                    Log.e("TAG", log);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}