package thanhtrung.android.book_ecomm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.ProductCateAdapter;
import thanhtrung.android.book_ecomm.model.Product;

public class SearchActivity extends AppCompatActivity {

    RecyclerView rcvProduct;
    EditText etSearch;
    ImageView back, search;
    String searchText;
    List<Product> mListProduct;
    FirebaseDatabase fDatabase;
    FirebaseFirestore fFirestore;
    DatabaseReference reference;
    ProductCateAdapter productCateAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent i = getIntent();
        searchText = i.getStringExtra("search");

        fFirestore = FirebaseFirestore.getInstance();
        fDatabase = FirebaseDatabase.getInstance();
        mListProduct =new ArrayList<>();

        back = findViewById(R.id.detail_back);
        search = findViewById(R.id.imageSearch);
        etSearch = findViewById(R.id.et_search);
        rcvProduct = findViewById(R.id.rcv_product);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HomeFragment.class);
                startActivity(i);
                finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getListSearchProduct(searchText);
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getListSearchProduct(editable.toString());
            }
        });

        etSearch.setText(searchText);
        linearLayoutManager = new LinearLayoutManager(this.getApplicationContext(), RecyclerView.VERTICAL, false);

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

    private void getListSearchProduct(String searchText) {
        mListProduct.clear();
        //String searchInputToLower = searchText.toLowerCase();
        //String searchInputTOUpper = searchText.toUpperCase();

        reference = fDatabase.getReference("products");
        reference.orderByChild("ProductName")
                .startAt(searchText)
                .endAt(searchText+"\uf8ff").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Product product = dataSnapshot.getValue(Product.class);
                    mListProduct.add(product);
                    String log = "ID: " + product.getProductID() + "Name: " + product.getProductName() + "IMG: " + product.getImg();
                    Log.e("TAG", log);
                }
                productCateAdapter = new ProductCateAdapter(mListProduct, SearchActivity.this);
                rcvProduct.setAdapter(productCateAdapter);
                rcvProduct.setLayoutManager(linearLayoutManager);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}