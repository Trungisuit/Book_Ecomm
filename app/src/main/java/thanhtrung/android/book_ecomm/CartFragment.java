package thanhtrung.android.book_ecomm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import thanhtrung.android.book_ecomm.model.Cart;
import thanhtrung.android.book_ecomm.model.Product;

public class CartFragment extends Fragment {

    RecyclerView rcvCart;
    CartAdapter mCartAdapter;
    TextView tvTotal;
    Button btnPurchase;
    List<Cart> mListCart;
    String userID;
    int quantityadd, quantityminus, totalPriceProduct, proStock, proSold;
    Product product;
    FirebaseFirestore fFirestore;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    DatabaseReference reference, reference1, reference2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        rcvCart = v.findViewById(R.id.rcv_cart);
        tvTotal = v.findViewById(R.id.tv_total);
        btnPurchase = v.findViewById(R.id.btnPurchase);

        fAuth = FirebaseAuth.getInstance();
        fFirestore = FirebaseFirestore.getInstance();
        fDatabase = FirebaseDatabase.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        rcvCart.setLayoutManager(linearLayoutManager);

        mListCart =new ArrayList<>();
        mCartAdapter = new CartAdapter(mListCart, this.getContext(), new CartAdapter.ClickListener() {
            @Override
            public void onClickAddItems(Cart cart) {
                quantityadd =  Integer.parseInt(cart.getProductQuantity());
                proStock =  Integer.parseInt(cart.getProductStock());
                proSold =  Integer.parseInt(cart.getProductSold());
                int price =  Integer.parseInt(cart.getProductPrice());
                if(quantityadd < 5) {
                    quantityadd++;
                    int total = (quantityadd * price);
                    HashMap<String,Object> data = new HashMap<>();
                    data.put("ProductID",cart.getProductID());
                    data.put("ProductQuantity",""+quantityadd);
                    data.put("ProductStock",""+(proStock - 1));
                    data.put("ProductSold",""+(proSold + 1));
                    data.put("TotalPrice",""+total);
                    data.put("ProductName",cart.getProductName());
                    data.put("ProductPrice",cart.getProductPrice());
                    data.put("AuthorName", cart.getAuthorName());
                    data.put("Img", cart.getImg());
                    reference.child(cart.getProductID()).setValue(data);

                    reference2 = fDatabase.getReference("products");
                    reference2.child(cart.getProductID()).child("ProductStock")
                        .setValue(Integer.parseInt(cart.getProductStock())-1);
                    reference2.child(cart.getProductID()).child("ProductSold")
                            .setValue(Integer.parseInt(cart.getProductSold())+1);
                }
            }

            @Override
            public void onClickMinusItems(Cart cart) {
                quantityminus =  Integer.parseInt(cart.getProductQuantity());
                proStock =  Integer.parseInt(cart.getProductStock());
                proSold =  Integer.parseInt(cart.getProductSold());
                int price =  Integer.parseInt(cart.getProductPrice());
                if(quantityminus > 1) {
                    quantityminus--;

                    int total = (quantityminus * price);
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("ProductID",cart.getProductID());
                    data.put("ProductQuantity", "" + quantityminus);
                    data.put("ProductStock",""+(proStock + 1));
                    data.put("ProductSold",""+(proSold - 1));
                    data.put("TotalPrice", "" + total);
                    data.put("ProductName",cart.getProductName());
                    data.put("ProductPrice",cart.getProductPrice());
                    data.put("AuthorName", cart.getAuthorName());
                    data.put("Img", cart.getImg());
                    reference.child(cart.getProductID()).setValue(data);

                    reference2 = fDatabase.getReference("products");
                    reference2.child(cart.getProductID()).child("ProductStock")
                            .setValue(Integer.parseInt(cart.getProductStock())+1);
                    reference2.child(cart.getProductID()).child("ProductSold")
                            .setValue(Integer.parseInt(cart.getProductSold())-1);
                }
            }

            @Override
            public void onClickDeleteItems(Cart cart) {
                delteteproduct(cart);
            }
        });

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(),OrderActivity.class);
                startActivity(intent);
            }
        });
        totalPriceProduct = 0;
        getlistProduct();
        rcvCart.setAdapter(mCartAdapter);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                mCartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Cart cart = snapshot.getValue(Cart.class);
                mListCart.clear();
                if(cart == null || mListCart == null || mListCart.isEmpty())
                {
                    return;
                }
                for (int i = 0 ; i < mListCart.size() ; i++)
                {
                    if(cart.getProductID() == mListCart.get(i).getProductID()) {
                        mListCart.set(i,cart);
                        break;
                    }
                }
                mCartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Cart cart = snapshot.getValue(Cart.class);
                mListCart.clear();
                if(cart == null || mListCart == null || mListCart.isEmpty())
                {
                    return;
                }
                for (int i = 0 ; i < mListCart.size() ; i++)
                {
                    if(cart.getProductID() == mListCart.get(i).getProductID()) {
                        mListCart.remove(mListCart.get(i));
                        mListCart.clear();
                        break;
                    }
                }
                mCartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        return v;
    }

    private void getlistProduct(){
        reference  = fDatabase.getReference("users").child(fAuth.getUid()).child("Cart");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
                    Cart cart = dataSnapshot.getValue(Cart.class);
                    mListCart.add(cart);
                }
                totalPriceProduct = 0;
                for (int i = 0 ; i < mListCart.size() ; i++)
                {
                    totalPriceProduct += Integer.parseInt(mListCart.get(i).getTotalPrice());
                }
                tvTotal.setText(String.valueOf(totalPriceProduct)+" VN??");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Th???t b???i",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getTotal(List<Cart> list){
        int sum = 0;
        for (Cart item: list) {
            sum += Integer.parseInt(item.getProductPrice())* Integer.parseInt(item.getProductQuantity());
        }
        return String.valueOf(sum);
    }

    private void delteteproduct(Cart cart)
    {
        new AlertDialog.Builder(this.getContext())
                .setTitle("X??a")
                .setMessage("B???n c?? mu???n x??a s???n ph???m n??y kh??ng?")
                .setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        reference2 = fDatabase.getReference("products");
                        reference2.child(cart.getProductID()).child("ProductStock")
                                .setValue(Integer.parseInt(cart.getProductStock()) + Integer.parseInt(cart.getProductQuantity()));

                        reference = fDatabase.getReference("users").child(fAuth.getUid()).child("Cart");
                        reference.child(cart.getProductID()).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(getActivity(),"???? x??a th??nh c??ng s???n ph???m",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("Quay L???i",null)
                .show();
    }
}
