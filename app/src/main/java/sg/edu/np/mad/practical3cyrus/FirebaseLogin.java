package sg.edu.np.mad.practical3cyrus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseLogin extends AppCompatActivity {
    String dbpassword;
    String dbusername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_login);
        Button buttonLogin = findViewById(R.id.buttonLogin);



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://practical5cyrus-default-rtdb.asia-southeast1.firebasedatabase.app/");
                DatabaseReference usernameRef = database.getReference().child("Users").child("mad");
                usernameRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String username = dataSnapshot.child("username").getValue().toString();
                        dbusername = username;
                        String password = dataSnapshot.child("password").getValue().toString();
                        dbpassword = password;
                        EditText editUserNameLogin = (EditText)findViewById(R.id.editTextUsername);
                        String userNameLogin = editUserNameLogin.getText().toString();

                        EditText editPasswordLogin = (EditText)findViewById(R.id.editTextPassword);
                        String passwordLogin = editPasswordLogin.getText().toString();
                        Log.i("Username", userNameLogin+dbusername);
                        Log.i("Password", passwordLogin+dbpassword);
                        if (dbusername.equals(userNameLogin) && dbpassword.equals(passwordLogin)){
                            Log.i("Login","yes");
                            Intent ListActivtiy = new Intent(FirebaseLogin.this, ListActivity.class);
                            startActivity(ListActivtiy);
                        }
                        else{
                            Toast.makeText(FirebaseLogin.this, "Login Unsuccessfull", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(FirebaseLogin.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });
    }
}