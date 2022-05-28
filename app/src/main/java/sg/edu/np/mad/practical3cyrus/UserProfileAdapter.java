package sg.edu.np.mad.practical3cyrus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserProfileAdapter
    extends RecyclerView.Adapter<UserProfileViewHolder>{

    public static ArrayList<User> userData;
    Context c;
    public UserProfileAdapter(Context c,ArrayList<User> userData){
        this.c = c;
        this.userData = userData;
    }

    @Override
    public int getItemViewType(int position) {
        String username = userData.get(position).name;
        String lastCharacter = username.substring(username.length()-1);
        return (lastCharacter.equals("7")) ? 0 :1;
    }

    @NonNull
    @Override
    public UserProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = null;
        if (viewType == 1){
            item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_userprofile, null, false);
        }

        else{
            item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_seventh, null, false);
        }

        return new UserProfileViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserProfileViewHolder holder, int position) {
        User userContent = userData.get(position);
        int userProfilePosition = position;
        holder.username.setText(userContent.name);
        holder.descrption.setText(userContent.description);
        holder.profilepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Profile");
                builder.setMessage(userContent.name);
                builder.setCancelable(true);
                builder.setPositiveButton("view", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent loadProfile = new Intent(c, MainActivity.class);

                        loadProfile.putExtra("position",userProfilePosition);
                        c.startActivity(loadProfile);

                    }
                });
                builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }
}
