package com.juan.retrousermanagerapp;

import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.juan.retrousermanagerapp.model.MyResponse;
import com.juan.retrousermanagerapp.model.User;
import com.juan.retrousermanagerapp.sqlite.DBHelper;

import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnCreateUser;
    EditText etUserName;
    EditText etUserAge;
    Dao dao;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCreateUser = (Button) findViewById(R.id.buttonCrear);
        etUserName = (EditText) findViewById(R.id.editText);
        etUserAge = (EditText) findViewById(R.id.editText2);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dao = getHelper().getUserDao();
                    User u = new User();
                    u.setName(etUserName.getText().toString());
                    u.setAge(Integer.parseInt(etUserAge.getText().toString()));
                    u.setSync(false);

                    dao.create(u);
                } catch (SQLException e) {
                    Log.e("Error", e.getMessage());
                }

            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestClient rc = RestClient.getInstance();
                Call<MyResponse> user = rc.getUserService().getUser("56db3ae9abd8aceb72651058");
                user.enqueue(new Callback<MyResponse>() {
                    @Override
                    public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                        String status = response.body().getStatusCode() + "";
                        try {
                            Gson gson = new Gson();
                            User u = gson.fromJson(response.body().getData().get(0).toString(), User.class);
                            Toast.makeText(MainActivity.this, ":o " + u.toString(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e("Error :(", e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<MyResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private DBHelper getHelper() {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return dbHelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }
}
