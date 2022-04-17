package thanhtrung.android.book_ecomm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import thanhtrung.android.book_ecomm.Confirm_resetpassword;
import thanhtrung.android.book_ecomm.R;
import thanhtrung.android.book_ecomm.model.Cart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private Context mContext;
    private List<Cart> mListCart;

    public CartAdapter(Context mContext) {
        this.mContext = mContext;
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

        holder.imgCart.setImageResource(cart.getImageSrc());
        holder.tvName.setText(cart.getNameProduct());
        holder.tvAuthorName.setText(cart.getNameAuthor());
        holder.tvPrice.setText(cart.getPriceProduct());
        holder.tvQuan.setText(cart.getQuantityProduct());
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

        ImageView imgCart;
        TextView tvName;
        TextView tvAuthorName;
        TextView tvPrice;
        TextView tvQuan;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCart = itemView.findViewById(R.id.img_cart);
            tvName = itemView.findViewById(R.id.tv_cart_name);
            tvAuthorName = itemView.findViewById(R.id.tv_cart_author_name);
            tvPrice = itemView.findViewById(R.id.tv_cart_price);
            tvQuan = itemView.findViewById(R.id.tv_cart_quan);
        }
    }
}
