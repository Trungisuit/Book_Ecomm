package thanhtrung.android.book_ecomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import thanhtrung.android.book_ecomm.model.Product;

public class ProductActivity extends AppCompatActivity {
    private static final String TAG = "ProductActivity";
    String proID, proImg, proName,proPrice, proAuthor,proPublisher,proReleaseDate,proSize,proTranslator,proCover,userID;
    int proQuantity,proNumPage,proSold;
    ImageView detailImgProduct,plus,minus,back;
    TextView productName, productPrice, productAuthor, productPublisher, productDateRelease,
            productSize, productTranslator, productCover, productNumPage, quantity;
    int totalQuantity = 1;
    int totalPrice;
    Button btnBuyNow,btnAddToCart;
    FirebaseAuth fAuth;
    FirebaseFirestore fFirestore;
    DatabaseReference reference;
    public static Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        fAuth = FirebaseAuth.getInstance();
        fFirestore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        Intent i = getIntent();
        proID = i.getStringExtra("id");

        productName = findViewById(R.id.detail_product_Name);
        productPrice = findViewById(R.id.detail_Price);
        detailImgProduct = findViewById(R.id.detail_img_Product);
        productAuthor = findViewById(R.id.detail_Author);
        productPublisher = findViewById(R.id.publisher);
        productDateRelease = findViewById(R.id.daterelease);
        productSize = findViewById(R.id.booksize);
        productTranslator = findViewById(R.id.translator);
        productCover = findViewById(R.id.cover);
        productNumPage = findViewById(R.id.page_number);
        back = findViewById(R.id.detail_back);
        plus = findViewById(R.id.imageViewPlus);
        minus = findViewById(R.id.imageViewMinus);
        quantity = findViewById(R.id.tv_Quantity);
        btnBuyNow = findViewById(R.id.btn_BuyNow);
        btnAddToCart = findViewById(R.id.btn_AddToCart);
        ProductView();

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity > 1)
                {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity < 5) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProductActivity.this, CategoryFragment.class);
                startActivity(i);
                finish();
            }
        });

        String timestamps = ""+System.currentTimeMillis();
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> data = new HashMap<>();
                totalPrice = (Integer.parseInt(proPrice)*totalQuantity);
                data.put("ProductID", proID);
                data.put("ProductName",proName);
                data.put("ProductPrice",proPrice);
                data.put("ProductQuantity",""+totalQuantity);
                data.put("AuthorName", proAuthor);
                data.put("TotalPrice",""+ totalPrice);
                data.put("ImageSrc", proImg);
                data.put("Img", getResources().getIdentifier(proImg, "drawable", getPackageName()));
                reference.child(fAuth.getUid()).child("Cart").child(proID).setValue(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ProductActivity.this,"Đã Thêm Vào Giỏ Hàng",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProductActivity.this,"Thất Bại",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void ProductView() {
        product = new Product();

        DocumentReference docRef = fFirestore.collection("products").document(proID);
        docRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot !=null){
                        product  = documentSnapshot.toObject(Product.class);
                        productName.setText(product.getName());
                        productAuthor.setText(product.getAuthor());
                        proName = product.getName();
                        productPrice.setText(product.getPrice());
                        proPrice = product.getPrice();
                        proImg = product.getImg();
                        proAuthor = product.getAuthor();
                        int resID = getResources().getIdentifier(proImg, "drawable", getPackageName());
                        detailImgProduct.setImageResource(resID);
                        String log = "Id: "+product.getId()+" ,Name: " + product.getName() + ",Price: " + product.getPrice()
                                    + ",Author1: " + product.getAuthor()+ ",Author2: " + proAuthor;
                        Log.e("Product : ", log);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}