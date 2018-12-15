package reagodjj.example.com.gridviewshow.model;

import android.graphics.Bitmap;

public class ImageInfo {
    private String imagePath;
    private Bitmap bitmap;
    private String text;

    public ImageInfo(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap imageBitmap) {
        this.bitmap = imageBitmap;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
