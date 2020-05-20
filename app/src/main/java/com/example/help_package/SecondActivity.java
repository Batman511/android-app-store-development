package com.example.help_package;

        import android.Manifest;
        import android.app.ListActivity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.ApplicationInfo;
        import android.content.pm.PackageInfo;
        import android.content.pm.PackageManager;
        import android.os.Bundle;
        import android.os.Environment;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.io.File;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Vector;

public class SecondActivity extends ListActivity {
    //ArrayList<String> names = new ArrayList();
    //ArrayAdapter<String> adapter;
String values[];
    private Button button5;
public static ApplicationInfo app;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_second);
        //DeleteOnButton();
        PackageManager pm = getPackageManager();
//получение имя пакета и его адрес
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for(ApplicationInfo app: packages) {
            Log.d("PackageList", "package: " + app.packageName + ", sourceDir: " + app.sourceDir);
        }

        //вывод версии текущего приложения
        /*PackageInfo packageInfo = getPackageManager().getPackageInfo(packageName, 0);
        String version3 = packageInfo.versionName;
        //version3 = packageInfo.versionCode;
        Toast.makeText(this, version3,Toast.LENGTH_LONG).show();*/


        Package myPackage = Manifest.class.getPackage();
        String specTitle = myPackage.getSpecificationTitle();
        String vendor = myPackage.getSpecificationVendor();
       // String version = myPackage.getSpecificationVersion();
        try {
            String response = new RequestTask().execute("https://amarketproject.000webhostapp.com/versions.php?name1=" + app.packageName ).get(); //http://stackoverflow.com/get_versions.php?id=
           // Toast.makeText(this, app.,Toast.LENGTH_LONG).show();

           // Toast.makeText(this, response1,Toast.LENGTH_LONG).show();
            //            //пусть вресии отделяются набором символов $$$
            Vector<String> lists = new Vector<String>();
            String[] Lists = response.split("\\$\\$\\$");

            for (int i=0;i < Lists.length;i++) lists.add(Lists[i]);
            //names.add("name1");
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

    //возвращаем массив строк (договориться) с помощь функции как получить результат работы асинтаска
    /*new RequestTask().execute("http://stackoverflow.com");*/

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);

        if (values[0]!="not found of versions :(") Toast.makeText(this,  " ожидание скачивания "+ app.packageName + " версия " + values[position], Toast.LENGTH_LONG).show();
        //вызываем асинтаск 2, где скачивает файл

        try {
            String response1 = new RequestTask().execute("https://amarketproject.000webhostapp.com/urls.php?name2=" + app.packageName + "$" + values[position]).get();
            //response1 = response1.substring(0,response1.length()-4);
           // Toast.makeText(this, response1,Toast.LENGTH_LONG).show();
            RequestTask2 req2 = new RequestTask2();
            req2.setContext(this);

            req2.execute(response1,app.packageName); //"https://amarketproject.000webhostapp.com/uploads/" + values[position]

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //удаление приложения
   /* public void DeleteOnButton (Context context, Intent intent) {
    button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(
                new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        try
        {
            String packageName = intent.getData().toString() + getApplicationName(context, intent.getData().toString(), PackageManager.GET_UNINSTALLED_PACKAGES); //ApplicationInfo

            if(intent.getAction().equals("android.intent.action.PACKAGE_ADDED")){
                File sdcard = Environment.getExternalStorageDirectory();
                File file = new File(sdcard,"Downloads/temp.apk");
                file.delete();
            }
        }catch(Exception e){Toast.makeText(context, "onReceive()", Toast.LENGTH_LONG).show();}
        };
    };
    };*/
}