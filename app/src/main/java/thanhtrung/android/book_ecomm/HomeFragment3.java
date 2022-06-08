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
import thanhtrung.android.book_ecomm.adapter.HomePageAdapter;
import thanhtrung.android.book_ecomm.model.Book;
import thanhtrung.android.book_ecomm.model.Product;

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
    private HomePageAdapter homePageAdapter;

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
        homePageAdapter = new HomePageAdapter(this.getContext());
        LinearLayoutManager lnmg= new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL,false);
        rcvBook.setLayoutManager(lnmg);
        homePageAdapter.setData(this.getContext(), getProducts());
        rcvBook.setAdapter(homePageAdapter);
        return v;
    }

    private List<Product> getProducts(){
        List<Product> listProduct = new ArrayList<>();

        listProduct.add(new Product("1","Hành trình người xuất chúng", "90000", R.drawable.img1));
        listProduct.add(new Product("3","Chìa khóa hạnh phúc", "94000", R.drawable.img3));
        listProduct.add(new Product("2","Hành trình về phương đông", "95000", R.drawable.img2));
        listProduct.add(new Product("1","Hành trình người xuất chúng", "96000", R.drawable.img1));

        return listProduct;
    }
}