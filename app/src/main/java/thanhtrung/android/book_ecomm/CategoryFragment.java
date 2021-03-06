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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

import thanhtrung.android.book_ecomm.adapter.CategoryAdapter;
import thanhtrung.android.book_ecomm.model.Category;
import thanhtrung.android.book_ecomm.model.Product;
import thanhtrung.android.book_ecomm.model.Purchased;

public class CategoryFragment extends Fragment {
    RecyclerView rcvCategory;
    CategoryAdapter categoryAdapter;
    List<Category> mListCategories;
    FirebaseDatabase fDatabase;
    FirebaseFirestore fFirestore;
    DatabaseReference reference, reference1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);

        fFirestore = FirebaseFirestore.getInstance();
        fDatabase = FirebaseDatabase.getInstance();

        mListCategories =new ArrayList<>();

        rcvCategory = v.findViewById(R.id.rcv_category);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        rcvCategory.setLayoutManager(linearLayoutManager);
        categoryAdapter = new CategoryAdapter(getmListCategories(), this.getContext());

        rcvCategory.setAdapter(categoryAdapter);
        return v;
    }

    private List<Category> getmListCategories() {

        List<Category> listCategory = new ArrayList<>();

        List<Product> listProduct1 = new ArrayList<>();
        List<Product> listProduct2 = new ArrayList<>();
        List<Product> listProduct3 = new ArrayList<>();
        List<Product> listProduct4 = new ArrayList<>();
        List<Product> listProduct5 = new ArrayList<>();
        List<Product> listProduct6 = new ArrayList<>();

        listProduct1.add(new Product("1","B???n Xe", "137000", R.drawable.sach1));
        listProduct1.add(new Product("2","T???ng C?? Ng?????i Y??u T??i Nh?? Sinh M???nh", "96000", R.drawable.sach2));
        listProduct1.add(new Product("3","B??? gi??", "63000", R.drawable.sach3));
        listProduct1.add(new Product("4","Hai S??? Ph???n", "175000", R.drawable.sach4));
        listProduct1.add(new Product("5","Cu???n theo chi???u gi??", "364000", R.drawable.sach5));
        listProduct1.add(new Product("6","Ki??u H??nh V?? ?????nh Ki???n", "92000", R.drawable.sach6));

        listProduct2.add(new Product("7","K??? Ho???ch Qu???n L?? T??i Ch??nh C?? Nh??n", "132000", R.drawable.sach7));
        listProduct2.add(new Product("8","H???c V??? Ti???n", "85000", R.drawable.sach8));
        listProduct2.add(new Product("9","Qu???n Tr??? R???i Ro Ng??n H??ng Trong N???n Kinh T??? To??n C???u", "198000", R.drawable.sach9));
        listProduct2.add(new Product("10","Ti???n T??? Ng??n H??ng V?? Th??? Tr?????ng T??i Ch??nh", "338000", R.drawable.sach10));
        listProduct2.add(new Product("11","T??m L?? H??nh Vi Trong ?????u T?? Ch???ng Kho??n", "106000", R.drawable.sach11));
        listProduct2.add(new Product("12","Ngh??? Thu???t ?????u t?? Dhandho", "238000", R.drawable.sach12));

        listProduct3.add(new Product("13","Vi???t Nam S??? L?????c", "178000", R.drawable.sach13));
        listProduct3.add(new Product("14","?????i Vi???t S??? K?? To??n Th?? Tr???n B???", "169000", R.drawable.sach14));
        listProduct3.add(new Product("15","Homo Deus: L?????c S??? T????ng Lai", "122000", R.drawable.sach15));
        listProduct3.add(new Product("16","L?????c S??? Th??? Gi???i", "208000", R.drawable.sach16));
        listProduct3.add(new Product("17","L???ch S??? T?? T?????ng Trung Qu???c", "279000", R.drawable.sach17));
        listProduct3.add(new Product("18","Nh???ng Ng?????i Ch??u ??u ??? N?????c An Nam", "38000", R.drawable.sach18));

        listProduct4.add(new Product("19","Y??u Th???m", "129000", R.drawable.sach19));
        listProduct4.add(new Product("20","Th??m t??? l???ng danh Conan T???p 99", "30000", R.drawable.sach20));
        listProduct4.add(new Product("21","Ch?? Thu???t H???i Chi???n", "100000", R.drawable.sach21));
        listProduct4.add(new Product("22","V??n Ph??ng Th??m T??? Qu??i V???t", "30000", R.drawable.sach22));
        listProduct4.add(new Product("23","DR. STONE - T???p 13: Chi???n Tranh Khoa H???c", "50000", R.drawable.sach23));
        listProduct4.add(new Product("24","Thanh G????m Di???t Qu??? - T???p 23", "30000", R.drawable.sach24));

        listProduct5.add(new Product("25","Napol??on Bonaparte", "400000", R.drawable.sach25));
        listProduct5.add(new Product("26","Nh???ng Ng?????i Ph??? N??? Thay ?????i Th??? Gi???i", "190000", R.drawable.sach26));
        listProduct5.add(new Product("27","Vi???t Nam Phong T???c", "130000", R.drawable.sach27));
        listProduct5.add(new Product("28","???????ng M??y Qua X??? Tuy???t", "86000", R.drawable.sach28));
        listProduct5.add(new Product("29","Hoa Sen Tr??n Tuy???t", "71000", R.drawable.sach29));
        listProduct5.add(new Product("30","H??nh Tr??nh V??? Ph????ng ????ng", "50000", R.drawable.sach30));

        listProduct6.add(new Product("31","Tu???i Tr??? ????ng Gi?? Bao Nhi??u", "60000", R.drawable.sach31));
        listProduct6.add(new Product("32","Ph?? Tan S??? Ng???y Bi???n", "86000", R.drawable.sach32));
        listProduct6.add(new Product("33","T??ng C?????ng S???c M???nh N??o B???", "78000", R.drawable.sach33));
        listProduct6.add(new Product("34","T?? Duy S??u", "65000", R.drawable.sach34));
        listProduct6.add(new Product("35","C??ng K??? Lu???t, C??ng T??? Do", "73000", R.drawable.sach35));
        listProduct6.add(new Product("36","L??m Ra L??m, Ch??i Ra Ch??i", "120000", R.drawable.sach36));

        listCategory.add(new Category(1,"V??n h???c", listProduct1));
        listCategory.add(new Category(2,"Kinh t???", listProduct2));
        listCategory.add(new Category(3,"L???ch s???", listProduct3));
        listCategory.add(new Category(4,"Manga", listProduct4));
        listCategory.add(new Category(5,"V??n h??a", listProduct5));
        listCategory.add(new Category(6,"K?? n??ng s???ng", listProduct6));

        return listCategory;
    }

    private void getListCategory() {

        List<Product> mListProduct = new ArrayList<>();

        int i = 1;


        for (i=1; i<=6 ; i++){
            reference = fDatabase.getReference("productcate").child(String.valueOf(i));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //String categoryName = snapshot.getValue();
                    reference1 = reference.child("ListProduct");
                    reference1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Product product = dataSnapshot.getValue(Product.class);
                                mListProduct.add(product);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }
}
