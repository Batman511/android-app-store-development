package com.example.AppStore;

import android.app.ListActivity;
import android.content.Context;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import java.util.Vector;

public class ThirdActivity extends ListActivity {
    String values[];
    private Button act_search;
    Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        addListenerOnButton();
    }

          public void addListenerOnButton () {
        con = this;
        act_search = (Button) findViewById(R.id.search);
        act_search.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            EditText your_choice =(EditText)findViewById(R.id.editText);
                            String response3 = new RequestTask().execute("https://amarketproject.000webhostapp.com/search.php?key=" + your_choice.getText()).get();
                            Vector<String> lists = new Vector<String>();

                            String[] Lists = response3.split("\\$\\$\\$");
                            for (int i = 0; i < Lists.length; i++) lists.add(Lists[i]);

                            values = new String[lists.size()];
                            for (int i = 0; i < lists.size(); i++)
                                values[i] = lists.get(i);

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(con, R.layout.list2_item, R.id.app_package, values);
                            setListAdapter(adapter);
                            if (values[0]=="") values[0]="not found of versions :(";
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
        );

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);

        if (values[0]!="not found of versions :(") Toast.makeText(this,  " ожидание скачивания " + values[position], Toast.LENGTH_LONG).show();
        //вызываем асинтаск 3, где скачивает файл
        try {
            values[position] = values[position].replace(' ', '$');
            String response1 = new RequestTask().execute("https://amarketproject.000webhostapp.com/urls.php?name2=" + values[position]).get();
            RequestTask3 req3 = new RequestTask3();
            req3.setContext(this);

            req3.execute(response1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
