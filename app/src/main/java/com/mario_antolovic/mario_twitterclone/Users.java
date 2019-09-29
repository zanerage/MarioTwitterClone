package com.mario_antolovic.mario_twitterclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Users extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        listView = findViewById(R.id.listView);
        arrayList = new ArrayList();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        try {
            ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();

            parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());

            parseQuery.findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> users, ParseException e) {
                    if (e == null) {
                        if (users.size() > 0) {
                            for (ParseUser user : users) {
                                arrayList.add(user.getUsername());
                            }
                            listView.setAdapter(arrayAdapter);
                          //  txtLoading.animate().alpha(0).setDuration(2000);
                         //   listView.setVisibility(View.VISIBLE);

                        }
                    }
                }
            });

        } catch (Exception e) {
            e.getMessage();
        }


}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.logout_item:
                ParseUser.getCurrentUser().logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        Intent intent = new Intent(Users.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                break;

            case R.id.sendTweetItem:

                Intent intent = new Intent(Users.this, HomeActivity.class);
                startActivity(intent);

                break;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
