package ralter.real.myphone.adapters;

import android.content.Context;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ralter.real.myphone.R;
import ralter.real.myphone.models.ContactCallHistory;

public class CallHistoryAdapter extends BaseAdapter {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CONTENT = 1;

    private Context mContext;
    private ArrayList<Object> lstData;
    private SimpleDateFormat timeFormat;

    public CallHistoryAdapter(Context mContext, ArrayList<Object> lstData) {
        this.mContext = mContext;
        this.lstData = lstData;
        timeFormat = new SimpleDateFormat("HH:mm");
    }

    @Override
    public int getCount() {
        return lstData.size();
    }

    @Override
    public Object getItem(int position) {
        return lstData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemViewType(int position) {
        if (lstData.get(position) instanceof String)
            return TYPE_HEADER;
        else
            return TYPE_CONTENT;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        int viewType = getItemViewType(position);

        HeaderViewHolder headerViewHolder = null;
        ContentViewHolder contentViewHolder = null;

        if (convertView == null) {
            switch (viewType) {
                case TYPE_HEADER:
                    view = LayoutInflater.from(mContext).inflate(R.layout.item_call_history_header, null);
                    headerViewHolder = new HeaderViewHolder(view);
                    view.setTag(headerViewHolder);
                    break;
                case TYPE_CONTENT:
                    view = LayoutInflater.from(mContext).inflate(R.layout.item_call_history_content, null);
                    contentViewHolder = new ContentViewHolder(view);
                    view.setTag(contentViewHolder);
                    break;
            }
        } else {
            switch (viewType) {
                case TYPE_HEADER:
                    view = convertView;
                    headerViewHolder = (HeaderViewHolder) view.getTag();
                    break;
                case TYPE_CONTENT:
                    view = convertView;
                    contentViewHolder = (ContentViewHolder) view.getTag();
                    break;
            }
        }

        switch (viewType) {
            case TYPE_HEADER:
                headerViewHolder.tvHeader.setText((String) lstData.get(position));
                break;
            case TYPE_CONTENT:
                ContactCallHistory contact = (ContactCallHistory) lstData.get(position);
                contentViewHolder.ivAvatar.setImageResource(contact.getAvatar());
                contentViewHolder.tvContactName.setText(contact.getContactName());
                contentViewHolder.tvPhoneNumber.setText(contact.getPhoneNumber());
                contentViewHolder.tvTime.setText(timeFormat.format(contact.getDate()));
                switch (contact.getType()) {
                    case CallLog
                            .Calls.INCOMING_TYPE:
                        contentViewHolder.ivStatus.setImageResource(R.drawable.ic_call_comming_24px);
                        contentViewHolder.tvDuration.setText(contact.getDuration());
                        break;
                    case CallLog.Calls.OUTGOING_TYPE:
                        contentViewHolder.ivStatus.setImageResource(R.drawable.ic_call_going_24px);
                        contentViewHolder.tvDuration.setText(contact.getDuration());
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        contentViewHolder.ivStatus.setImageResource(R.drawable.ic_call_missed_24px);
                        contentViewHolder.tvDuration.setText("");
                }
                break;
        }
        return view;
    }

    private static class ContentViewHolder {
        ImageView ivAvatar;
        TextView tvContactName;
        TextView tvPhoneNumber;
        ImageView ivStatus;
        TextView tvDuration;
        TextView tvTime;

        public ContentViewHolder(View view) {
            ivAvatar = view.findViewById(R.id.iv_avatar);
            tvContactName = view.findViewById(R.id.tv_contact_name);
            tvPhoneNumber = view.findViewById(R.id.tv_phone_number);
            ivStatus = view.findViewById(R.id.iv_call_status);
            tvDuration = view.findViewById(R.id.tv_duration);
            tvTime = view.findViewById(R.id.tv_time);
        }
    }

    private static class HeaderViewHolder {
        TextView tvHeader;

        public HeaderViewHolder(View view) {
            this.tvHeader = view.findViewById(R.id.tv_header);
        }
    }
}
