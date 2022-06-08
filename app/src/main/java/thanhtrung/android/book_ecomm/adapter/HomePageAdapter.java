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

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.HomePageViewHolder> {

    private List<Product> mProducts;
    private Context mContext;

    public HomePageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(Context mContext, List<Product> list){
        this.mContext = mContext;
        this.mProducts = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new HomePageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Product product = mProducts.get(position);
        if (product == null ){
            return;
        }

        holder.imgBook.setImageResource(product.getImageUrl());
        holder.tvName.setText(product.getName());
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

    public class HomePageViewHolder extends RecyclerView.ViewHolder {

        private CardView layoutItem;
        private ImageView imgBook;
        private TextView tvName;

        public HomePageViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item);
            imgBook = itemView.findViewById(R.id.img_sach);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    private void onClickToDetail(Product product){
        Intent i = new Intent(mContext, ProductActivity.class);
        i.putExtra("id", product.getId());
        mContext.startActivity(i);
    }
}