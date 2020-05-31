package com.example.AppStore;

        import android.Manifest;
        import android.app.ListActivity;
        import android.content.Intent;
        import android.content.pm.ApplicationInfo;
        import android.content.pm.PackageInfo;
        import android.content.pm.PackageManager;
        import android.net.Uri;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.Toast;
        import java.util.List;
        import java.util.Vector;

public class SecondActivity extends ListActivity {

    private Button delete_but;
    String values[];
    String version1;

public static ApplicationInfo app;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_second);

        delete_but = findViewById(R.id.button4);
        DeleteOnButton();

        PackageManager pm = getPackageManager();
//получение имя пакета и его адрес
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for(ApplicationInfo app: packages) {
            Log.d("PackageList", "package: " + app.packageName + ", sourceDir: " + app.sourceDir);
        }

        //вывод версии текущего приложения
        try {
        PackageInfo packageInfo = getPackageManager().getPackageInfo(app.packageName, 0);
        String version3 = packageInfo.versionName;

        //вывод версии текщего приложения
        Toast.makeText(this, version3,Toast.LENGTH_LONG).show();
        }catch (Exception e) {
            e.printStackTrace();
        };

        Package myPackage = Manifest.class.getPackage();
        String specTitle = myPackage.getSpecificationTitle();
        String vendor = myPackage.getSpecificationVendor();

        try {
            String response = new RequestTask().execute("https://amarketproject.000webhostapp.com/versions.php?name1=" + app.packageName ).get();

            //пусть вресии отделяются набором символов $$$
            Vector<String> lists = new Vector<String>();
            String[] Lists = response.split("\\$\\$\\$");

            for (int i=0;i < Lists.length;i++) lists.add(Lists[i]);

            //разбиение на версии выше

            values = new String[lists.size()];
            for (int i = 0; i < lists.size(); i++)
                values[i] = lists.get(i);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.list2_item,R.id.app_package, values);
            setListAdapter(adapter);
            if (values[0]=="") values[0]="not found of versions :(";
        } catch (Exception e) {
          e.printStackTrace();
        }

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);

        if (values[0]!="not found of versions :(") Toast.makeText(this,  " ожидание скачивания "+ app.packageName + " версия " + values[position], Toast.LENGTH_LONG).show();
        //вызываем асинтаск 2, где скачиваем файл

        try {
            String response1 = new RequestTask().execute("https://amarketproject.000webhostapp.com/urls.php?name2=" + app.packageName + "$" + values[position]).get();
            //Toast.makeText(this, response1,Toast.LENGTH_LONG).show();
            RequestTask2 req2 = new RequestTask2();
            req2.setContext(this);

            req2.execute(response1,app.packageName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteOnButton () {
        delete_but = (Button)findViewById(R.id.button4);
        delete_but.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent2 = new Intent(Intent.ACTION_DELETE,
                                Uri.fromParts("package", app.packageName,null));
                        startActivity(intent2);
                    }
                }
        );
    }

}