package thanhtrung.android.book_ecomm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {
    private RecyclerView rcvBook;
    private  Book_adapter book_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
         rcvBook = findViewById(R.id.recycleviewBook);
         book_adapter= new Book_adapter(this);
        LinearLayoutManager lnmg= new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcvBook.setLayoutManager(lnmg);
        book_adapter.setData(getListBook());
        rcvBook.setAdapter(book_adapter);
    }
    private List<Book> getListBook(){
        List<Book> list = new ArrayList<>();
        list.add(new Book(R.drawable.sach1,"Hành trình người xuất chúng"));
        list.add(new Book(R.drawable.sach2,"Hành trình về Phương Đông"));
        return  list;
    }
}