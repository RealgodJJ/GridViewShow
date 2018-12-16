package reagodjj.example.com.gridviewshow.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import reagodjj.example.com.gridviewshow.R;
import reagodjj.example.com.gridviewshow.model.ImageInfo;

public class GridViewImageAdapter extends BaseAdapter {
    private Context context;
    private List<ImageInfo> imageInfoList;

    public GridViewImageAdapter(Context context, List<ImageInfo> imageInfoList) {
        this.context = context;
        this.imageInfoList = imageInfoList;
    }

    @Override
    public int getCount() {
        return imageInfoList == null ? 0 : imageInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_image_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageInfo imageInfo = imageInfoList.get(position);
        viewHolder.tvName.setText(imageInfo.getText());

//        if (imageInfo.getBitmap() == null) {
//            viewHolder.ivPicture.setImageResource(R.mipmap.ic_launcher);
//        } else {
//            viewHolder.ivPicture.setImageBitmap(imageInfo.getBitmap());
//        }
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.centerCrop().placeholder(R.mipmap.ic_launcher).error(R.mipmap.error)
                .fallback(R.mipmap.empty);

        Glide.with(context).load(imageInfo.getImagePath()).apply(requestOptions).into(viewHolder.ivPicture);

        return convertView;
    }

    class ViewHolder {
        private ImageView ivPicture;
        private TextView tvName;

        ViewHolder(View view) {
            ivPicture = view.findViewById(R.id.iv_picture);
            tvName = view.findViewById(R.id.tv_name);
        }
    }
}
