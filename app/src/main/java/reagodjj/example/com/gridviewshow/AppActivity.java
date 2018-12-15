package reagodjj.example.com.gridviewshow;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import reagodjj.example.com.gridviewshow.Adapter.GridViewAdapter;
import reagodjj.example.com.gridviewshow.model.AppInfo;

public class AppActivity extends AppCompatActivity {
    private GridView gvApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        gvApp = findViewById(R.id.gv_app);

        gvApp.setAdapter(new GridViewAdapter(getAppInfoList(), this));
    }

    private List<AppInfo> getAppInfoList() {
        List<AppInfo> appInfoList = new ArrayList<>();
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);

        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            AppInfo appInfo = new AppInfo();
            appInfo.setAppName(packageInfo.applicationInfo.loadLabel(packageManager).toString());
            appInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(packageManager));
            appInfo.setPackageName(packageInfo.packageName);
            appInfo.setVersionCode(packageInfo.versionCode);
            appInfo.setVersionName(packageInfo.versionName);

            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                appInfoList.add(appInfo);
            }
        }
        return appInfoList;
    }
}
