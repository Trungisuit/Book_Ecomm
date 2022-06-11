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

import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.ProductActivity;
import thanhtrung.android.book_ecomm.inteface.IClickItemProductListener;
import thanhtrung.android.book_ecomm.model.Product;
import thanhtrung.android.book_ecomm.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> mProducts;
    private Context mContext;

    public ProductAdapter() {
    }

    public ProductAdapter(List<Product> mProducts, Context mContext) {
        this.mProducts = mProducts;
        this.mContext = mContext;
    }

    public void setData(Context mContext, List<Product> list){
        this.mContext = mContext;
        this.mProducts = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Product product = mProducts.get(position);
        if (product == null ){
            return;
        }

        holder.imgBook.setImageResource(product.getImageUrl());
        holder.tvName.setText(product.getProductName());
        holder.tvPrice.setText(product.getProductPrice());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickToDetail(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mProducts != null){
            return mProducts.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private CardView layoutItem;
        private ImageView imgBook;
        private TextView tvName, tvPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item1);
            imgBook = itemView.findViewById(R.id.img_book);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }

    private void onClickToDetail(Product product){
        Intent i = new Intent(mContext, ProductActivity.class);
        i.putExtra("id", product.getProductID());
        mContext.startActivity(i);
    }
}