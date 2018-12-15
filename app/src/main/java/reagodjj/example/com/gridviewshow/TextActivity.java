package reagodjj.example.com.gridviewshow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class TextActivity extends AppCompatActivity {
    private GridView gvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        gvText = findViewById(R.id.gv_text);
        List<String> textList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            textList.add(getString(R.string.item) + i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.grid_text_item, textList);
        gvText.setAdapter(arrayAdapter);
    }
}
