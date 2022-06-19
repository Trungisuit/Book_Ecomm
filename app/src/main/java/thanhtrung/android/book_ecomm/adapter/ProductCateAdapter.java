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
import thanhtrung.android.book_ecomm.model.Product;

public class ProductCateAdapter extends RecyclerView.Adapter<ProductCateAdapter.ProductViewHolder> {

    private List<Product> mProducts;
    private Context mContext;
    String proImg;

    public ProductCateAdapter() {
    }

    public ProductCateAdapter(List<Product> mProducts, Context mContext) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items4, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Product product = mProducts.get(position);
        if (product == null ){
            return;
        }

        proImg = product.getImg();
        int resID = mContext.getResources().getIdentifier(proImg, "drawable", mContext.getPackageName());
        holder.imgPro.setImageResource(resID);
        holder.tvName.setText(product.getProductName());
        holder.tvAuthor.setText(product.getAuthorName());
        holder.tvPrice.setText(product.getProductPrice()+" VNĐ");
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
        private ImageView imgPro;
        private TextView tvName, tvPrice, tvAuthor;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item2);
            imgPro = itemView.findViewById(R.id.img_cart);
            tvAuthor = itemView.findViewById(R.id.tv_cart_author_name);
            tvName = itemView.findViewById(R.id.tv_cart_name);
            tvPrice = itemView.findViewById(R.id.tv_cart_price);
        }
    }

    private void onClickToDetail(Product product){
        Intent i = new Intent(mContext, ProductActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("id", product.getProductID());
        mContext.startActivity(i);
    }
}