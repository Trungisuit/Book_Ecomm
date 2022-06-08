package thanhtrung.android.book_ecomm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.ProductActivity;
import thanhtrung.android.book_ecomm.PurchaseHistory;
import thanhtrung.android.book_ecomm.PurchasedItemActivity;
import thanhtrung.android.book_ecomm.R;
import thanhtrung.android.book_ecomm.model.Cart;
import thanhtrung.android.book_ecomm.model.Category;
import thanhtrung.android.book_ecomm.model.Product;
import thanhtrung.android.book_ecomm.model.Purchased;

public class PurchasedAdapter extends RecyclerView.Adapter<PurchasedAdapter.HistoryViewHolder>{

    Context mContext;
    List<Purchased> mListPurchased;

    public Context getmContext() {
        return mContext;
    }

    public PurchasedAdapter(List<Purchased> mListPurchased, Context mContext) {
        this.mListPurchased = mListPurchased;
        this.mContext = mContext;
    }

    public void setData(List<Purchased> list){
        this.mListPurchased = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.history_items, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Purchased purchased = mListPurchased.get(position);
        if (purchased == null)
        {
            return;
        }

        holder.tvPurchasedId.setText(purchased.getId());
        holder.tvPurchasedId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickToDetail(purchased);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListPurchased.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{

        TextView tvPurchasedId, tvTotal, tvPrice, tvQuan;
        RecyclerView rcvBook;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPurchasedId = itemView.findViewById(R.id.tv_orderid);
        }
    }

    private void onClickToDetail(Purchased purchased) {
        Intent i = new Intent(this.getmContext(), PurchasedItemActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("id", purchased.getId());
        mContext.startActivity(i);
    }
}
