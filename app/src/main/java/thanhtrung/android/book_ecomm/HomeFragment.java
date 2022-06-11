package thanhtrung.android.book_ecomm;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.Book_adapter;
import thanhtrung.android.book_ecomm.adapter.ViewHomeAdapter;
import thanhtrung.android.book_ecomm.model.Book;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    String mParam1;
    String mParam2;
    TabLayout mTabLayout;
    ViewPager2 mViewPager;
    ViewHomeAdapter myViewHomeAdapter;
    EditText etSearch;
    Button search;

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
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

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        mTabLayout = v.findViewById(R.id.tab_layout);
        mViewPager = v.findViewById(R.id.view_pager);
        etSearch = v.findViewById(R.id.searchText);
        search = v.findViewById(R.id.btnSearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SearchActivity.class);
                i.putExtra("search", etSearch.getText().toString());
                startActivity(i);
            }
        });

        myViewHomeAdapter = new ViewHomeAdapter(this);

        mViewPager.setAdapter(myViewHomeAdapter);
        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("BÁN CHẠY");
                    break;

                case 1:
                    tab.setText("MỚI NHẤT");
                    break;

                case 2:
                    tab.setText("GIÁ RẺ");
                    break;
            }
        }).attach();
        return v;
    }
}