package reagodjj.example.com.gridviewshow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btLoadText;
    private Button btLoadApp;
    private Button btLoadPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    private void initView() {
        btLoadText = findViewById(R.id.bt_load_text);
        btLoadApp = findViewById(R.id.bt_load_app);
        btLoadPicture = findViewById(R.id.bt_load_picture);
    }

    private void setListener() {
        btLoadText.setOnClickListener(this);
        btLoadApp.setOnClickListener(this);
        btLoadPicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_load_text:
                startActivity(new Intent(MainActivity.this, TextActivity.class));
                break;
            case R.id.bt_load_app:
                startActivity(new Intent(MainActivity.this, AppActivity.class));
                break;
            case R.id.bt_load_picture:
                startActivity(new Intent(MainActivity.this, PictureActivity.class));
                break;
        }
    }
}
