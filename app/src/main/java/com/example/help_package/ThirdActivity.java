package com.example.help_package;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
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
                            String response3 = new RequestTask().execute("https://amarketproject.000webhostapp.com/search.php?key=" + your_choice.getText()).get(); //your_choice.getText()
                            Vector<String> lists = new Vector<String>();
                            //Toast.makeText(con, response3,Toast.LENGTH_LONG).show();

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
        //ListView countriesList = (ListView) findViewById(R.id.list);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);

        if (values[0]!="not found of versions :(") Toast.makeText(this,  " ожидание скачивания " + values[position], Toast.LENGTH_LONG).show();
        //вызываем асинтаск 2, где скачивает файл
        try {
            values[position] = values[position].replace(' ', '$');
            String response1 = new RequestTask().execute("https://amarketproject.000webhostapp.com/urls.php?name2=" + values[position]).get();
             //Toast.makeText(this, response1,Toast.LENGTH_LONG).show();
            /*!!!!!!!RequestTask2 req2 = new RequestTask2();
            req2.setContext(this);

            Void response2 = req2.execute(response1).get(); //"https://amarketproject.000webhostapp.com/uploads/" + values[position]
            !!!!!!!!*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
          /* // EditText your_choice =(EditText)findViewById(R.id.search);
            String response3 = new RequestTask().execute("https://amarketproject.000webhostapp.com/search.php?key=" + "calculator").get(); //your_choice.getText()
            Vector<String> lists2 = new Vector<String>();

            String[] Lists2 = response3.split("\\$\\$\\$");
            for (int i=0;i < Lists2.length;i++) lists2.add(Lists2[i]);

            values = new String[lists2.size()];
            for (int i = 0; i < lists2.size(); i++)
                values[i] = lists2.get(i);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.list2_item, R.id.app_package, values);
            setListAdapter(adapter);
        }  catch (Exception e) {
            e.printStackTrace();
        } */

}
