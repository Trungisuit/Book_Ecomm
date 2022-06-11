package thanhtrung.android.book_ecomm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.Book_adapter;
import thanhtrung.android.book_ecomm.adapter.HomePageAdapter;
import thanhtrung.android.book_ecomm.model.Book;
import thanhtrung.android.book_ecomm.model.Product;

public class HomeFragment2 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String mParam1;
    String mParam2;
    RecyclerView rcvBook;
    HomePageAdapter homePageAdapter;

    List<Product> mListProduct;
    FirebaseDatabase fDatabase;
    DatabaseReference reference;
    LinearLayoutManager linearLayoutManager;

    public HomeFragment2() {
        // Required empty public constructor
    }

    public static HomeFragment2 newInstance(String param1, String param2) {
        HomeFragment2 fragment = new HomeFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home2, container, false);
        rcvBook = v.findViewById(R.id.recycleviewBook);

        fDatabase = FirebaseDatabase.getInstance();
        mListProduct =new ArrayList<>();

        linearLayoutManager= new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL,false);
        rcvBook.setLayoutManager(linearLayoutManager);

        getListProduct();

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                homePageAdapter.notifyDataSetChanged();
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
                homePageAdapter.notifyDataSetChanged();
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
                homePageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        return v;
    }

    private List<Product> getProducts(){
        List<Product> listProduct = new ArrayList<>();

        listProduct.add(new Product("1","Hành trình người xuất chúng", "90000", R.drawable.img1));
        listProduct.add(new Product("3","Chìa khóa hạnh phúc", "94000", R.drawable.img3));
        listProduct.add(new Product("2","Hành trình về phương đông", "95000", R.drawable.img2));
        listProduct.add(new Product("30","Hành trình người xuất chúng", "96000", R.drawable.img1));

        return listProduct;
    }

    private void getListProduct() {
        mListProduct.clear();

        reference = fDatabase.getReference("products");
        reference.orderByChild("ProductDate").limitToLast(5).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Product product = dataSnapshot.getValue(Product.class);
                    mListProduct.add(product);
                    String log = "ID: " + product.getProductID() + "Name: " + product.getProductName() + "IMG: " + product.getImg();
                    Log.e("TAG", log);
                }
                Collections.reverse(mListProduct);
                homePageAdapter = new HomePageAdapter(mListProduct, getContext());
                rcvBook.setAdapter(homePageAdapter);
                rcvBook.setLayoutManager(linearLayoutManager);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}