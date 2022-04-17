package thanhtrung.android.book_ecomm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import thanhtrung.android.book_ecomm.R;
import thanhtrung.android.book_ecomm.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    Context mContext;
    List<Category> mListCategory;

    public CategoryAdapter(Context mContext){
        this.mContext = mContext;
    }

    public void setData(List<Category> list){
        this.mListCategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_items, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = mListCategory.get(position);
        if (category == null)
        {
            return;
        }

        holder.tvNameCategory.setText(mListCategory.get(position).getName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.rcvBook.setLayoutManager(linearLayoutManager);

        ProductAdapter productAdapter = new ProductAdapter();
        productAdapter.setData(category.getProducts());
        holder.rcvBook.setAdapter(productAdapter);
    }

    @Override
    public int getItemCount() {
        return mListCategory.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView tvNameCategory;
        RecyclerView rcvBook;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameCategory = itemView.findViewById(R.id.tv_name_category);

            rcvBook = itemView.findViewById(R.id.recyclerView1);
        }
    }
}
