package thanhtrung.android.book_ecomm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import thanhtrung.android.book_ecomm.R;
import thanhtrung.android.book_ecomm.model.Cart;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    final Context mContext;
    List<Cart> mListOrder;

    public OrderAdapter(List<Cart> mListOrder, Context mContext) {
        this.mListOrder = mListOrder;
        this.mContext = mContext;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Cart> list){
        this.mListOrder = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items3, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Cart cart = mListOrder.get(position);
        if (cart == null){
            return;
        }
        int resID = cart.getImg();
        holder.imgCart.setImageResource(resID);
        holder.tvName.setText(cart.getProductName());
        holder.tvPrice.setText(cart.getProductPrice()+" VNĐ");
        holder.tvQuan.setText(cart.getProductQuantity());
        holder.tvTotal.setText(cart.getTotalPrice()+" VNĐ");
    }

    @Override
    public int getItemCount() {
        if (mListOrder != null)
        {
            return mListOrder.size();
        }
        return 0;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        ImageView imgCart;
        TextView tvName, tvTotal, tvPrice, tvQuan;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCart = itemView.findViewById(R.id.img_cart);
            tvName = itemView.findViewById(R.id.tv_cart_name);
            tvPrice = itemView.findViewById(R.id.tv_product_price);
            tvQuan = itemView.findViewById(R.id.tv_Quantity);
            tvTotal = itemView.findViewById(R.id.tv_Total);
        }
    }
}
