package com.example.vadim.vkmessenger;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.vk.sdk.VKScope;
import com.vk.sdk.api.*;
import com.vk.sdk.api.model.VKList;


public class Friends_Screen extends Activity{

    private  ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends__screen);

        listView = (ListView) findViewById(R.id.listView);
        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,
                "first_name", "last_name"));
        request.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKList list = (VKList) response.parsedModel;
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Friends_Screen.this,
                        android.R.layout.simple_expandable_list_item_1, list);
                listView.setAdapter(arrayAdapter);
            }
        });
    }


}

