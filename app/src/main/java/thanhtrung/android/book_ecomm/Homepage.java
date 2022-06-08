package thanhtrung.android.book_ecomm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.HomePageAdapter;
import thanhtrung.android.book_ecomm.model.Product;

public class Homepage extends AppCompatActivity {
    private RecyclerView rcvBook;
    private HomePageAdapter homePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        rcvBook = findViewById(R.id.recycleviewBook);
        /*homePageAdapter = new HomePageAdapter();
        homePageAdapter.setData(this, getProducts());
        LinearLayoutManager lnmg= new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcvBook.setLayoutManager(lnmg);
        rcvBook.setAdapter(homePageAdapter);*/
    }

    private List<Product> getProducts(){
        List<Product> listProduct = new ArrayList<>();

        listProduct.add(new Product("1","Hành trình người xuất chúng", "90000", R.drawable.img1));
        listProduct.add(new Product("3","Chìa khóa hạnh phúc", "94000", R.drawable.img3));
        listProduct.add(new Product("2","Hành trình về phương đông", "95000", R.drawable.img2));
        listProduct.add(new Product("1","Hành trình người xuất chúng", "96000", R.drawable.img1));

        return listProduct;
    }
}