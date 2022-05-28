package sg.edu.np.mad.practical3cyrus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<User> userData = UserProfileAdapter.userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int position = getIntent().getIntExtra("position",0);


        TextView textViewName = findViewById(R.id.textViewName);
        textViewName.setText(userData.get(position).name);

        TextView textViewDescription = findViewById(R.id.textViewDescription);
        textViewDescription.setText(userData.get(position).description);

        Button buttonFollow = findViewById(R.id.buttonFollow);
        DBHandler dbHandler = new DBHandler(this, null,null,1);
        buttonFollow.setText(userData.get(position).followed ? "UnFollow" : "Follow");

        buttonFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonFollow.setText(userData.get(position).followed ? "Follow" : "UnFollow");

                Toast.makeText(getApplicationContext(), userData.get(position).followed? "UnFollowed" : "Followed",Toast.LENGTH_SHORT).show();
                userData.get(position).followed= !userData.get(position).followed;
                dbHandler.updateUser(userData.get(position));
                dbHandler.close();
            }
        });

        Button buttonMessage = findViewById(R.id.buttonMessage);
        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadMessageGroup = new Intent(MainActivity.this,MessageGroup.class);
                startActivity(loadMessageGroup);
            }
        });

    }
}