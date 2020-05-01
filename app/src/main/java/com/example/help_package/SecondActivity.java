package com.example.help_package;

        import android.Manifest;
        import android.app.ListActivity;
        import android.content.pm.ApplicationInfo;
        import android.content.pm.PackageManager;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Vector;

public class SecondActivity extends ListActivity {
    //ArrayList<String> names = new ArrayList();
    //ArrayAdapter<String> adapter;
String values[];

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //получить список версий

      //  View header = getLayoutInflater().createView("Версии приложения");
        //ListView listView = getListView();
        //listView.addHeaderView(header);
        PackageManager pm = getPackageManager();
//получение имя пакета и его адрес
        for (ApplicationInfo app : pm.getInstalledApplications(0)) {
            Log.d("PackageList", "package: " + app.packageName + ", sourceDir: " + app.sourceDir);
        }
        Package myPackage = Manifest.class.getPackage();
        String specTitle = myPackage.getSpecificationTitle();
        String vendor = myPackage.getSpecificationVendor();
        String version = myPackage.getSpecificationVersion();
        try {
            String response = new RequestTask().execute("https://amarketproject.000webhostapp.com/find_version.php?id=" + myPackage.getName()).get(); //http://stackoverflow.com/get_versions.php?id=
            //пусть вресии отделяются набором символов $$$
            Vector<String> lists = new Vector<String>();
            String[] Lists = response.split("$$$");

            for (int i=0;i < Lists.length;i++) lists.add(Lists[i]);
            //names.add("name1");
            //разбиение на версии выше

            values = new String[lists.size()];
            for (int i = 0; i < lists.size(); i++)
                values[i] = lists.get(i);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.list2_item,R.id.app_package, values);
            setListAdapter(adapter);

        } catch (Exception e) {
          e.printStackTrace();
        }


    }

    //возвращаем массив строк (договориться) с помощь функции как получить результат работы асинтаска
    /*new RequestTask().execute("http://stackoverflow.com");*/

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);

        Toast.makeText(this,  " ожидание скачивания "+ values[position], Toast.LENGTH_LONG).show();
        //вызываем асинтаск 2, где скачивает файл

        try {
            Void response2 = new RequestTask2().execute("https://amarketproject.000webhostapp.com/uploads/" + values[position]).get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}