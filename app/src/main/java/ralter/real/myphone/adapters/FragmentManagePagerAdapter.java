package ralter.real.myphone.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import ralter.real.myphone.R;
import ralter.real.myphone.fragments.CallHistoryFragment;
import ralter.real.myphone.fragments.NonFragment;

public class FragmentManagePagerAdapter extends FragmentStatePagerAdapter {

    Context mContext;
    CallHistoryFragment fragment1;
    ArrayList<Fragment> lstFragments;
    ArrayList<String> lstTitles;

    public FragmentManagePagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;

        lstFragments = new ArrayList<>();
        lstTitles = new ArrayList<>();

        fragment1 = CallHistoryFragment.newInstance(mContext);
        NonFragment fragment2 = new NonFragment();

        lstFragments.add(fragment1);
        lstFragments.add(fragment2);
        lstTitles.add(mContext.getResources().getString(R.string.title_tab_call_history));
        lstTitles.add("Fragment2");
    }

    @Override
    public Fragment getItem(int i) {
        return lstFragments.get(i);
    }

    @Override
    public int getCount() {
        return lstFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lstTitles.get(position);
    }
}
