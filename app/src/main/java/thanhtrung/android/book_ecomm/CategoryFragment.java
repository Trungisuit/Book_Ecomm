package thanhtrung.android.book_ecomm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.CategoryAdapter;
import thanhtrung.android.book_ecomm.model.Category;
import thanhtrung.android.book_ecomm.model.Product;

public class CategoryFragment extends Fragment {
    private static final String TAG = "CategoryFragment";
    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    FirebaseFirestore fFirestore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);

        fFirestore = FirebaseFirestore.getInstance();

        rcvCategory = v.findViewById(R.id.rcv_category);
        categoryAdapter = new CategoryAdapter(this.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        rcvCategory.setLayoutManager(linearLayoutManager);
        categoryAdapter.setData(getListCategory());
        rcvCategory.setAdapter(categoryAdapter);
        return v;
    }

    public void getCategories() {
        List<Category> listCategory = new ArrayList<>();
        List<Category> list = new ArrayList<>();

        List<Product> listProduct = new ArrayList<>();

        listProduct.add(new Product("1","Hành trình người xuất chúng", "90000", R.drawable.img1));
        listProduct.add(new Product("3","Chìa khóa hạnh phúc", "94000", R.drawable.img3));
        listProduct.add(new Product("2","Hành trình về phương đông", "95000", R.drawable.img2));
        listProduct.add(new Product("1","Hành trình người xuất chúng", "96000", R.drawable.img1));

        CollectionReference docRef = fFirestore.collection("categories");
        docRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DocumentReference docRef = fFirestore.collection("categories").document(document.getId());
                                docRef
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                                if(task.isSuccessful()){
                                                    DocumentSnapshot documentSnapshot = task.getResult();
                                                    if(documentSnapshot !=null){
                                                        /*Category category  = documentSnapshot.toObject(Category.class);
                                                        category.setProducts(listProduct);
                                                        String log = "Id: "+category.getId()+" ,Name: " + category.getName();
                                                        Log.e("Category : ", log);*/
                                                        listCategory.add(documentSnapshot.toObject(Category.class));
                                                    } else {
                                                        Log.d(TAG, "No such document");
                                                    }
                                                } else {
                                                    Log.d(TAG, "get failed with ", task.getException());
                                                }
                                            }
                                        });
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                categoryAdapter.setData(listCategory);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private List<Category> getListCategory() {

        List<Category> listCategory = new ArrayList<>();

        List<Product> listProduct = new ArrayList<>();

        listProduct.add(new Product("1","Hành trình người xuất chúng", "90000", R.drawable.img1));
        listProduct.add(new Product("3","Chìa khóa hạnh phúc", "94000", R.drawable.img3));
        listProduct.add(new Product("2","Hành trình về phương đông", "95000", R.drawable.img2));
        listProduct.add(new Product("1","Hành trình người xuất chúng", "96000", R.drawable.img1));

        /*CollectionReference docRef = fFirestore.collection("categories");
        docRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DocumentReference docRef = fFirestore.collection("categories").document(document.getId());
                                docRef
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                                if(task.isSuccessful()){
                                                    DocumentSnapshot documentSnapshot = task.getResult();
                                                    if(documentSnapshot !=null){
                                                        Category category  = documentSnapshot.toObject(Category.class);
                                                        category.setProducts(listProduct);
                                                        String log = "Id: "+category.getId()+" ,Name: " + category.getName();
                                                        Log.e("Category : ", log);
                                                        listCategory.add(category);
                                                    } else {
                                                        Log.d(TAG, "No such document");
                                                    }
                                                } else {
                                                    Log.d(TAG, "get failed with ", task.getException());
                                                }
                                            }
                                        });
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });*/
        listCategory.add(new Category("Best Seller", listProduct));
        listCategory.add(new Category("Mới nhất", listProduct));
        listCategory.add(new Category("Tư duy - Kỹ năng sống", listProduct));
        listCategory.add(new Category("Học thuật", listProduct));

        return listCategory;
    }
}
