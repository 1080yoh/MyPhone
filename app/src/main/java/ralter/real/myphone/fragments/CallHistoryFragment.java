package ralter.real.myphone.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import ralter.real.myphone.R;
import ralter.real.myphone.adapters.CallHistoryAdapter;
import ralter.real.myphone.models.ContactCallHistory;
import ralter.real.myphone.utils.MapDrawable;
import ralter.real.myphone.utils.MyDateUtils;
import ralter.real.myphone.utils.TimeConvert;

public class CallHistoryFragment extends Fragment {

    Context mContext;
    private ListView lvCallHistory;
    ArrayList<Object> lstData;
    private CallHistoryAdapter callHistoryAdapter;

    public static CallHistoryFragment newInstance(Context mContext) {
        CallHistoryFragment instance = new CallHistoryFragment();
        instance.mContext = mContext;
        return instance;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call_history, container, false);
        lvCallHistory = view.findViewById(R.id.lv_call_history);

        lstData = new ArrayList<>();
        callHistoryAdapter = new CallHistoryAdapter(mContext, lstData);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getData();

        lvCallHistory.setAdapter(callHistoryAdapter);
        callHistoryAdapter.notifyDataSetChanged();
    }

    private void getData() {
        HashMap<Integer, Integer> hmAvatars = MapDrawable.getHashMapIconAvatars();
        Random random = new Random();

        String[] projections = new String[]{
                CallLog.Calls._ID,
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.NUMBER,
                CallLog.Calls.TYPE,
                CallLog.Calls.DURATION,
                CallLog.Calls.DATE
        };
        Cursor cursor = mContext.getContentResolver().query(CallLog.Calls.CONTENT_URI, projections, null, null, CallLog.Calls.DATE + " DESC");

        Date current = new Date(System.currentTimeMillis());
        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, dd MM yyyy");

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            if (name == null)
                name = "<Unknown>";
            String number = cursor.getString(2);
            String type = cursor.getString(3);
            long duration = Long.parseLong(cursor.getString(4));
            String callDate = cursor.getString(5);
            Date date = new Date(Long.valueOf(callDate));

            ContactCallHistory contactCallHistory = new ContactCallHistory(
                    hmAvatars.get(random.nextInt(hmAvatars.size())),
                    name,
                    number,
                    TimeConvert.fromSecToMinutes(duration),
                    date,
                    Integer.parseInt(type)
            );

            if (MyDateUtils.compareDay(date, today) == 0 && lstData.size() == 0) {
                lstData.add(getString(R.string.today));
            } else if (MyDateUtils.compareDay(date, current) < 0) {
                current = date;
                lstData.add(simpleDateFormat.format(current));
            }
            lstData.add(contactCallHistory);
        }
        Log.e("Ralter", "Getdata from callhistory fragment, size = " + lstData.size());
        cursor.close();
    }
}
