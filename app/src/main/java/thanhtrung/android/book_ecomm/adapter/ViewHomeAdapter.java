package thanhtrung.android.book_ecomm.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import thanhtrung.android.book_ecomm.HomeFragment;
import thanhtrung.android.book_ecomm.HomeFragment1;
import thanhtrung.android.book_ecomm.HomeFragment2;
import thanhtrung.android.book_ecomm.HomeFragment3;

public class ViewHomeAdapter extends FragmentStateAdapter {

    public ViewHomeAdapter(@NonNull HomeFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment1();

            case 1:
                return new HomeFragment2();

            case 2:
                return new HomeFragment3();

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
