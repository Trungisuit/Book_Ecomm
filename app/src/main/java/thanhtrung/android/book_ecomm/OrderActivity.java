package thanhtrung.android.book_ecomm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.CartAdapter;
import thanhtrung.android.book_ecomm.model.Cart;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView rcvOrder;
    private CartAdapter mCartAdapter;
    private TextView tvTotal, tvDelivery, tvAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        rcvOrder = findViewById(R.id.rcv_order);
        mCartAdapter = new CartAdapter(this);
        tvTotal = findViewById(R.id.totalFeeTxt);
        tvDelivery = findViewById(R.id.deliveryTxt);
        tvAll = findViewById(R.id.totalTxt);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvOrder.setLayoutManager(linearLayoutManager);

        mCartAdapter.setData(getListCart());
        rcvOrder.setAdapter(mCartAdapter);
        tvTotal.setText(getTotal(getListCart()));
        tvDelivery.setText(getDelivery(getListCart()));
        tvAll.setText(getAll(getListCart()));
    }

    private List<Cart> getListCart(){
        List<Cart> list = new ArrayList<>();
        list.add(new Cart(R.drawable.img1, "Hành trình người xuất chúng", "Lê Chí Linh", "90000", "2", "180000"));
        list.add(new Cart(R.drawable.img3, "Chìa khóa hạnh phúc", "N/A", "90000", "2", "180000"));
        list.add(new Cart(R.drawable.img2, "Hành trình về phương đông", "N/A", "75000", "1", "75000"));
        list.add(new Cart(R.drawable.img3, "Chìa khóa hạnh phúc", "N/A", "80000", "5", "400000"));
        list.add(new Cart(R.drawable.img1, "Hành trình người xuất chúng", "Lê Chí Linh", "85000", "1", "85000"));
        list.add(new Cart(R.drawable.img3, "Chìa khóa hạnh phúc", "N/A", "90000", "2", "180000"));
        return list;
    }

    private String getTotal(List<Cart> list){
        int sum = 0;
        for (Cart item: list) {
            sum += Integer.parseInt(item.getPriceProduct())* Integer.parseInt(item.getQuantityProduct());
        }
        return String.valueOf(sum);
    }

    private String getDelivery(List<Cart> list){
        if (list.size() > 5){
            return "0";
        }
        return String.valueOf(list.size()*10000);
    }

    private String getAll(List<Cart> list){
        int sum = 0;
        for (Cart item: list) {
            sum += Integer.parseInt(item.getPriceProduct())* Integer.parseInt(item.getQuantityProduct());
        }
        if (list.size() > 5){
            return String.valueOf(sum);
        }
        return String.valueOf((list.size()*10000) + sum);
    }
}