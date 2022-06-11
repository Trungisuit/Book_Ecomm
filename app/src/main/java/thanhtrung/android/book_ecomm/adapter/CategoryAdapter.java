package thanhtrung.android.book_ecomm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import thanhtrung.android.book_ecomm.DetailCategoryActivity;
import thanhtrung.android.book_ecomm.ProductActivity;
import thanhtrung.android.book_ecomm.R;
import thanhtrung.android.book_ecomm.inteface.IClickItemProductListener;
import thanhtrung.android.book_ecomm.model.Category;
import thanhtrung.android.book_ecomm.model.Product;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    Context mContext;
    List<Category> mListCategory;

    public CategoryAdapter(List<Category> list, Context mContext){
        this.mListCategory = list;
        this.mContext = mContext;
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

        holder.tvNameCategory.setText(mListCategory.get(position).getCategoryName());
        holder.tvNameCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onClickToDetail(category); }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.rcvBook.setLayoutManager(linearLayoutManager);

        ProductAdapter productAdapter = new ProductAdapter();
        productAdapter.setData(mContext, category.getListProduct());
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

    private void onClickToDetail(Category category){
        Intent i = new Intent(mContext, DetailCategoryActivity.class);
        i.putExtra("id", String.valueOf(category.getCategoryID()));
        i.putExtra("name", category.getCategoryName());
        mContext.startActivity(i);
    }

}
