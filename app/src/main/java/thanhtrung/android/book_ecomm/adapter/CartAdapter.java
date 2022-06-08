package thanhtrung.android.book_ecomm.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.Confirm_resetpassword;
import thanhtrung.android.book_ecomm.R;
import thanhtrung.android.book_ecomm.model.Cart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private Context mContext;
    private List<Cart> mListCart;
    private ClickListener clickListener;


    public interface ClickListener{
        void onClickAddItems(Cart cart);
        void onClickMinusItems(Cart cart);
        void onClickDeleteItems(Cart cart);

    }

    public CartAdapter(List<Cart> mListCart, Context mContext, ClickListener listener) {
        this.mListCart = mListCart;
        this.mContext = mContext;
        this.clickListener = listener;
    }

    public void setData(List<Cart> list){
        this.mListCart = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items2, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = mListCart.get(position);
        if (cart == null){
            return;
        }

        holder.imgCart.setImageResource(cart.getImg());
        holder.tvName.setText(cart.getProductName());
        holder.tvAuthorName.setText(cart.getAuthorName());
        holder.tvPrice.setText(cart.getProductPrice()+" VNĐ");
        holder.tvQuan.setText(cart.getProductQuantity());
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClickMinusItems(cart);
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClickAddItems(cart);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClickDeleteItems(cart);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListCart != null)
        {
            return mListCart.size();
        }
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{

        ImageView imgCart,plus,minus,delete;
        TextView tvName, tvAuthorName, tvPrice, tvQuan;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCart = itemView.findViewById(R.id.img_cart);
            tvName = itemView.findViewById(R.id.tv_cart_name);
            tvAuthorName = itemView.findViewById(R.id.tv_cart_author_name);
            tvPrice = itemView.findViewById(R.id.tv_cart_price);
            tvQuan = itemView.findViewById(R.id.tv_Quantity);
            plus = itemView.findViewById(R.id.imageViewPlus);
            minus = itemView.findViewById(R.id.imageViewMinus);
            delete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
