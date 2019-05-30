package vyomchandra.com.firebasecompleteproject;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

import vyomchandra.com.firebasecompleteproject.modal.Data;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //toolbaar
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recyler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        //firebase
        mAuth=FirebaseAuth.getInstance();

        FirebaseUser mUser=mAuth.getCurrentUser();
        String uid=mUser.getUid();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("AllData").child(uid);


        //floating button
        floatingActionButton=findViewById(R.id.flotingbtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();

            }
        });


    }


    public void addData(){
        AlertDialog.Builder mydialoge=new AlertDialog.Builder(this);
        LayoutInflater inflater=LayoutInflater.from(this);
        View myview=inflater.inflate(R.layout.inputlayout,null);
        mydialoge.setView(myview);
        final AlertDialog dialog=mydialoge.create();

        final EditText mTitle=myview.findViewById(R.id.title);
        final EditText mDescription=myview.findViewById(R.id.description);
        final EditText mBudget=myview.findViewById(R.id.budget);
        final Button mSave=myview.findViewById(R.id.save);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title=mTitle.getText().toString().trim();
                String description=mDescription.getText().toString().trim();
                String budget=mBudget.getText().toString().trim();

                String mDate= DateFormat.getDateInstance().format(new Date());
                String id=mDatabase.push().getKey();

                Data data=new Data(title,description,id,budget,mDate);
                mDatabase.child(id).setValue(data);

                Toast.makeText(HomeActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
