package thanhtrung.android.book_ecomm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import thanhtrung.android.book_ecomm.ProductActivity;
import thanhtrung.android.book_ecomm.R;
import thanhtrung.android.book_ecomm.model.Cart;
import thanhtrung.android.book_ecomm.model.Product;

public class PurchasedItemAdapter extends RecyclerView.Adapter<PurchasedItemAdapter.PurchasedViewHolder> {

    private Context mContext;
    List<Cart> mListPurchased;

    /*public PurchasedItemAdapter(List<Cart> mListPurchased, Context mContext) {
        this.mListPurchased = mListPurchased;
        this.mContext = mContext;
    }*/

    public void setData(Context mContext, List<Cart> list) {
        this.mContext = mContext;
        this.mListPurchased = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PurchasedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items3, parent, false);
        return new PurchasedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasedViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Cart purchased = mListPurchased.get(position);
        if (purchased == null) {
            return;
        }

        int resID = purchased.getImg();
        holder.imgCart.setImageResource(resID);
        holder.tvName.setText(purchased.getProductName());
        holder.tvPrice.setText(purchased.getProductPrice() + " VNĐ");
        holder.tvQuan.setText(purchased.getProductQuantity());
        holder.tvTotal.setText(purchased.getTotalPrice() + " VNĐ");
    }

    @Override
    public int getItemCount() {
        if (mListPurchased != null) {
            return mListPurchased.size();
        }
        return 0;
    }

    public class PurchasedViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCart;
        TextView tvName, tvTotal, tvPrice, tvQuan;

        public PurchasedViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCart = itemView.findViewById(R.id.img_cart);
            tvName = itemView.findViewById(R.id.tv_cart_name);
            tvPrice = itemView.findViewById(R.id.tv_product_price);
            tvQuan = itemView.findViewById(R.id.tv_Quantity);
            tvTotal = itemView.findViewById(R.id.tv_Total);
        }
    }
}