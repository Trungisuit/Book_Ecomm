package thanhtrung.android.book_ecomm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.CartAdapter;
import thanhtrung.android.book_ecomm.model.Cart;

public class CartFragment extends Fragment {

    private RecyclerView rcvCart;
    private CartAdapter mCartAdapter;
    private TextView tvTotal;
    private Button btnPurchase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        rcvCart = v.findViewById(R.id.rcv_cart);
        mCartAdapter = new CartAdapter(this.getContext());
        tvTotal = v.findViewById(R.id.tv_total);
        btnPurchase = v.findViewById(R.id.btnPurchase);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        rcvCart.setLayoutManager(linearLayoutManager);

        mCartAdapter.setData(getListCart());
        rcvCart.setAdapter(mCartAdapter);
        tvTotal.setText(getTotal(getListCart()));
        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(),OrderActivity.class);
                startActivity(intent);
            }
        });
        return v;
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
}
