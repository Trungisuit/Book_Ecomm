package thanhtrung.android.book_ecomm.inteface;

import thanhtrung.android.book_ecomm.model.Product;

public interface IClickItemProductListener {

    public default void onClickItemProduct(Product product) {
    }
}
