package com.example.AppStore;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {


    private PackageManager packageManager = null; //получение информации о пакетах установленных приложений
    private List<ApplicationInfo> applist = null;
    private AppAdapter listadapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().setTitle("Ваши приложения");
        getActionBar().setIcon(R.drawable.wolf1);
        getActionBar().setBackgroundDrawable(new
                ColorDrawable(Color.parseColor("#483D8B"))); // #33B5E5

        packageManager = getPackageManager(); //получение экземпляра класса
        new LoadApplications().execute();
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ApplicationInfo app = applist.get(position);

                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                SecondActivity.app = app;
                startActivity(intent);

    }


    private List checkForLaunchIntent(List list) {
    //получаем список установленных приложений
        ArrayList appList = new ArrayList();

        for(ApplicationInfo info : (List<ApplicationInfo>)list) {
            try{
                if(packageManager.getLaunchIntentForPackage(info.packageName) != null) {
                    appList.add(info);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

    //сортировка
        for (int i = appList.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                //сортировка по названию приложения (очень долго)
                //String name1 = (String)((ApplicationInfo)appList.get(j)).loadLabel(packageManager);
                //String name2 = (String)((ApplicationInfo)appList.get(j+1)).loadLabel(packageManager);

                String name1 = (String)((ApplicationInfo)appList.get(j)).packageName;
                String name2 = (String)((ApplicationInfo)appList.get(j+1)).packageName;
                if (name1.compareTo(name2) > 0){
                    ApplicationInfo tmp = (ApplicationInfo)appList.get(j);
                    appList.set(j,appList.get(j+1));
                    appList.set(j+1,tmp);
                }
            }
        }

        return appList;
    }

    private class LoadApplications extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {

            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA)); //возвращает список всех пакетов приожений

            listadapter = new AppAdapter(MainActivity.this, R.layout.list_item, applist);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) { //выводим список
            setListAdapter(listadapter);
            progress.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(MainActivity.this, null, "Loading apps info...");
            super.onPreExecute();
        }
    }

    //меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.download:
                Intent intent2 = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent2);
                return true;
            case R.id.settings:
                Intent intent3 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent3);
                return true;
            case R.id.open_website:
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}