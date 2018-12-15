package reagodjj.example.com.gridviewshow.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import reagodjj.example.com.gridviewshow.R;
import reagodjj.example.com.gridviewshow.model.AppInfo;

public class GridViewAppAdapter extends BaseAdapter {
    private List<AppInfo> appInfoList;
    private Context context;

    public GridViewAppAdapter(List<AppInfo> appInfoList, Context context) {
        this.appInfoList = appInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return appInfoList == null ? 0 : appInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return appInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_app_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivIcon.setImageDrawable(appInfoList.get(position).getAppIcon());
        viewHolder.tvAppName.setText(appInfoList.get(position).getAppName() +
                appInfoList.get(position).getVersionName());

        return convertView;
    }

    class ViewHolder {
        private ImageView ivIcon;
        private TextView tvAppName;

        ViewHolder(View view) {
            ivIcon = view.findViewById(R.id.iv_app_icon);
            tvAppName = view.findViewById(R.id.tv_app_name);
        }
    }
}
