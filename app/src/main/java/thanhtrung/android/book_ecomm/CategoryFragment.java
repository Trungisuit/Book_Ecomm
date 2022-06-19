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

        listProduct1.add(new Product("1","Bến Xe", "137000", R.drawable.sach1));
        listProduct1.add(new Product("2","Từng Có Người Yêu Tôi Như Sinh Mệnh", "96000", R.drawable.sach2));
        listProduct1.add(new Product("3","Bố già", "63000", R.drawable.sach3));
        listProduct1.add(new Product("4","Hai Số Phận", "175000", R.drawable.sach4));
        listProduct1.add(new Product("5","Cuốn theo chiều gió", "364000", R.drawable.sach5));
        listProduct1.add(new Product("6","Kiêu Hãnh Và Định Kiến", "92000", R.drawable.sach6));

        listProduct2.add(new Product("7","Kế Hoạch Quản Lý Tài Chính Cá Nhân", "132000", R.drawable.sach7));
        listProduct2.add(new Product("8","Học Về Tiền", "85000", R.drawable.sach8));
        listProduct2.add(new Product("9","Quản Trị Rủi Ro Ngân Hàng Trong Nền Kinh Tế Toàn Cầu", "198000", R.drawable.sach9));
        listProduct2.add(new Product("10","Tiền Tệ Ngân Hàng Và Thị Trường Tài Chính", "338000", R.drawable.sach10));
        listProduct2.add(new Product("11","Tâm Lý Hành Vi Trong Đầu Tư Chứng Khoán", "106000", R.drawable.sach11));
        listProduct2.add(new Product("12","Nghệ Thuật đầu tư Dhandho", "238000", R.drawable.sach12));

        listProduct3.add(new Product("13","Việt Nam Sử Lược", "178000", R.drawable.sach13));
        listProduct3.add(new Product("14","Đại Việt Sử Ký Toàn Thư Trọn Bộ", "169000", R.drawable.sach14));
        listProduct3.add(new Product("15","Homo Deus: Lược Sử Tương Lai", "122000", R.drawable.sach15));
        listProduct3.add(new Product("16","Lược Sử Thế Giới", "208000", R.drawable.sach16));
        listProduct3.add(new Product("17","Lịch Sử Tư Tưởng Trung Quốc", "279000", R.drawable.sach17));
        listProduct3.add(new Product("18","Những Người Châu Âu Ở Nước An Nam", "38000", R.drawable.sach18));

        listProduct4.add(new Product("19","Yêu Thầm", "129000", R.drawable.sach19));
        listProduct4.add(new Product("20","Thám tử lừng danh Conan Tập 99", "30000", R.drawable.sach20));
        listProduct4.add(new Product("21","Chú Thuật Hồi Chiến", "100000", R.drawable.sach21));
        listProduct4.add(new Product("22","Văn Phòng Thám Tử Quái Vật", "30000", R.drawable.sach22));
        listProduct4.add(new Product("23","DR. STONE - Tập 13: Chiến Tranh Khoa Học", "50000", R.drawable.sach23));
        listProduct4.add(new Product("24","Thanh Gươm Diệt Quỷ - Tập 23", "30000", R.drawable.sach24));

        listProduct5.add(new Product("25","Napoléon Bonaparte", "400000", R.drawable.sach25));
        listProduct5.add(new Product("26","Những Người Phụ Nữ Thay Đổi Thế Giới", "190000", R.drawable.sach26));
        listProduct5.add(new Product("27","Việt Nam Phong Tục", "130000", R.drawable.sach27));
        listProduct5.add(new Product("28","Đường Mây Qua Xứ Tuyết", "86000", R.drawable.sach28));
        listProduct5.add(new Product("29","Hoa Sen Trên Tuyết", "71000", R.drawable.sach29));
        listProduct5.add(new Product("30","Hành Trình Về Phương Đông", "50000", R.drawable.sach30));

        listProduct6.add(new Product("31","Tuổi Trẻ Đáng Giá Bao Nhiêu", "60000", R.drawable.sach31));
        listProduct6.add(new Product("32","Phá Tan Sự Ngụy Biện", "86000", R.drawable.sach32));
        listProduct6.add(new Product("33","Tăng Cường Sức Mạnh Não Bộ", "78000", R.drawable.sach33));
        listProduct6.add(new Product("34","Tư Duy Sâu", "65000", R.drawable.sach34));
        listProduct6.add(new Product("35","Càng Kỷ Luật, Càng Tự Do", "73000", R.drawable.sach35));
        listProduct6.add(new Product("36","Làm Ra Làm, Chơi Ra Chơi", "120000", R.drawable.sach36));

        listCategory.add(new Category(1,"Văn học", listProduct1));
        listCategory.add(new Category(2,"Kinh tế", listProduct2));
        listCategory.add(new Category(3,"Lịch sử", listProduct3));
        listCategory.add(new Category(4,"Manga", listProduct4));
        listCategory.add(new Category(5,"Văn hóa", listProduct5));
        listCategory.add(new Category(6,"Kĩ năng sống", listProduct6));

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
