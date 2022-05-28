package sg.edu.np.mad.practical3cyrus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DBHandler dbHandler = new DBHandler(this, null,null,1);

        ArrayList<User> userData = dbHandler.getUsers();
        /*for (int i = 1 ; i<21 ; i++){
            int min = -999999999;
            int max = 999999999;
            int int_random_username = (int)Math.floor(Math.random()*(max-min+1)+min);
            int int_random_description = (int)Math.floor(Math.random()*(max-min+1)+min);
            String newUsername = "Name" + int_random_username;
            String newDescription = "Description " + int_random_description;
            User newUser = new User(newUsername, newDescription, i, false);
            userData.add(newUser);
        }*/

        RecyclerView rv = findViewById(R.id.recyclerviewUser);
        UserProfileAdapter adapter = new UserProfileAdapter(ListActivity.this, userData);
        LinearLayoutManager layout = new LinearLayoutManager(this);

        rv.setAdapter(adapter);
        rv.setLayoutManager(layout);

        /*ImageView imageCenter = findViewById(R.id.imageView);

        imageCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                builder.setTitle("Profile");
                builder.setMessage("MADness");
                builder.setCancelable(true);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int min = 0;
                        int max = 999999999;
                        int int_random = (int)Math.floor(Math.random()*(max-min+1)+min);
                        Intent loadProfile = new Intent(ListActivity.this, MainActivity.class);
                        loadProfile.putExtra("randomInteger",int_random);
                        startActivity(loadProfile);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){}
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });*/
    }
}