package thanhtrung.android.book_ecomm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Book_adapter extends  RecyclerView.Adapter<Book_adapter.BookViewHolder> {
    private Context mcontext;
        private List<Book> mListBook;
    public Book_adapter(Context mcontext) {
        this.mcontext = mcontext;
    }
    public void setData(List<Book> list) {
        this.mListBook = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = mListBook.get(position);
        if(book == null)
        {
            return;
        }
        holder.Imgbook.setImageResource(book.getResourceid());
        holder.tvName.setText(book.getName());
    }

    @Override
    public int getItemCount() {
        if(mListBook != null) {
            return mListBook.size();
        }
        return 0;
    }

    public  class BookViewHolder extends RecyclerView.ViewHolder {
        private ImageView Imgbook;
        private TextView tvName;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            Imgbook=itemView.findViewById(R.id.img_sach);
            tvName=itemView.findViewById(R.id.tv_name);

        }
    }
}
