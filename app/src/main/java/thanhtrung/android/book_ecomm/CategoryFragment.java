package thanhtrung.android.book_ecomm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.CategoryAdapter;
import thanhtrung.android.book_ecomm.model.Category;
import thanhtrung.android.book_ecomm.model.Product;

public class CategoryFragment extends Fragment {

    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);

        rcvCategory = v.findViewById(R.id.rcv_category);
        categoryAdapter = new CategoryAdapter(this.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        rcvCategory.setLayoutManager(linearLayoutManager);

        categoryAdapter.setData(getListCategory());
        rcvCategory.setAdapter(categoryAdapter);
        return v;
    }

    private List<Category> getListCategory() {
        List<Category> list = new ArrayList<>();

        List<Product> listProduct = new ArrayList<>();

        listProduct.add(new Product("Hành trình người xuất chúng", "90000", R.drawable.img1));
        listProduct.add(new Product("Chìa khóa hạnh phúc", "94000", R.drawable.img3));
        listProduct.add(new Product("Hành trình về phương đông", "95000", R.drawable.img2));
        listProduct.add(new Product("Hành trình người xuất chúng", "96000", R.drawable.img1));

        listProduct.add(new Product("Hành trình người xuất chúng", "90000", R.drawable.img1));
        listProduct.add(new Product("Chìa khóa hạnh phúc", "90000", R.drawable.img3));
        listProduct.add(new Product("Hành trình về phương đông", "90000", R.drawable.img2));
        listProduct.add(new Product("Hành trình người xuất chúng", "90000", R.drawable.img1));

        list.add(new Category("Best Seller", listProduct));
        list.add(new Category("Mới nhất", listProduct));
        list.add(new Category("Tư duy - Kỹ năng sống", listProduct));
        list.add(new Category("Học thuật", listProduct));

        return list;
    }
}
