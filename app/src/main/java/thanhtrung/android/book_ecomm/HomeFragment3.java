package thanhtrung.android.book_ecomm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.Book_adapter;
import thanhtrung.android.book_ecomm.model.Book;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rcvBook;
    private Book_adapter book_adapter;

    public HomeFragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment3 newInstance(String param1, String param2) {
        HomeFragment3 fragment = new HomeFragment3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home3, container, false);
        rcvBook = v.findViewById(R.id.recycleviewBook);
        book_adapter= new Book_adapter(this.getContext());
        LinearLayoutManager lnmg= new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL,false);
        rcvBook.setLayoutManager(lnmg);
        book_adapter.setData(getListBook());
        rcvBook.setAdapter(book_adapter);
        return v;
    }

    private List<Book> getListBook(){
        List<Book> list = new ArrayList<>();
        list.add(new Book(R.drawable.img1,"Hành trình người xuất chúng"));
        list.add(new Book(R.drawable.img2,"Hành trình về Phương Đông"));
        list.add(new Book(R.drawable.img3,"Chìa khóa hạnh phúc"));
        return list;
    }
}