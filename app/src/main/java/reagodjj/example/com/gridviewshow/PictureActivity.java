package reagodjj.example.com.gridviewshow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import reagodjj.example.com.gridviewshow.Adapter.GridViewImageAdapter;
import reagodjj.example.com.gridviewshow.model.ImageInfo;

public class PictureActivity extends AppCompatActivity {
    private GridView gvPicture;
    private List<String> imagePathList;
    private List<ImageInfo> imageInfoList;
    private ImageLoadTask imageLoadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        gvPicture = findViewById(R.id.gv_image);

        initData();
    }

    private void initData() {
        imagePathList = new ArrayList<>();
        imagePathList.add("http://img5.duitang.com/uploads/item/201406/26/20140626164837_dzKds.jpeg");
        imagePathList.add("http://img2.imgtn.bdimg.com/it/u=3980629563,3881837630&fm=21&gp=0.jpg");
        imagePathList.add("http://img5q.duitang.com/uploads/item/201505/08/20150508155052_XJaNW.jpeg");
        imagePathList.add("http://img4.duitang.com/uploads/item/201407/02/20140702105736_FdN5P.jpeg");
        imagePathList.add("http://img2.imgtn.bdimg.com/it/u=2866652161,3841912673&fm=21&gp=0.jpg");
        imagePathList.add("http://img4.imgtn.bdimg.com/it/u=883757693,2063816225&fm=21&gp=0.jpg");
        imagePathList.add("http://cdn.duitang.com/uploads/item/201309/26/20130926110955_QtUdX.jpeg");
        imagePathList.add("http://zjimg.5054399.com/allimg/160815/14_160815161625_9.jpg");
        imagePathList.add("http://i-7.vcimg.com/trim/09ce7067d2467c54cf05bbd271ee3ec8430415/trim.jpg");

        imageInfoList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ImageInfo imageInfo = new ImageInfo(imagePathList.get(i));
            imageInfo.setText(getString(R.string.picture) + String.valueOf(i));
            imageInfoList.add(imageInfo);
        }

        GridViewImageAdapter gridViewImageAdapter = new GridViewImageAdapter(this, imageInfoList);
        gvPicture.setAdapter(gridViewImageAdapter);
        imageLoadTask = new ImageLoadTask(this, gridViewImageAdapter);
        imageLoadTask.execute();
    }

    private Bitmap getPicture(String path) {
        //打开网络连接，接收网络图片
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);//默认connect就是接收inputStream或outputStream
            httpURLConnection.setConnectTimeout(10 * 1000);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();

            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressLint("StaticFieldLeak")
    public class ImageLoadTask extends AsyncTask<Void, Void, Void> {
        private Context context;
        private GridViewImageAdapter gridViewImageAdapter;

        ImageLoadTask(Context context, GridViewImageAdapter gridViewImageAdapter) {
            this.context = context;
            this.gridViewImageAdapter = gridViewImageAdapter;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < gridViewImageAdapter.getCount(); i++) {
                ImageInfo imageInfo = (ImageInfo) gridViewImageAdapter.getItem(i);
                imageInfo.setBitmap(getPicture(imageInfo.getImagePath()));
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            gridViewImageAdapter.notifyDataSetChanged();
        }
    }
}
