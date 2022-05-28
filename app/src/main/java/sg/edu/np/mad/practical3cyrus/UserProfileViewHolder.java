package sg.edu.np.mad.practical3cyrus;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserProfileViewHolder
    extends RecyclerView.ViewHolder{

    TextView username;
    TextView descrption;
    ImageView profilepicture;
    View viewItem;

    public UserProfileViewHolder(View item){
        super(item);
        viewItem = item;
        username = item.findViewById(R.id.textViewusername);
        descrption = item.findViewById(R.id.textViewdescription);
        profilepicture = item.findViewById(R.id.imageViewProfilePicture);
    }

}
