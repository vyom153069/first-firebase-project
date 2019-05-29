package vyomchandra.com.firebasecompleteproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText email,pass;
    private Button signup,signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.email_signin);
        pass=findViewById(R.id.password_signin);
        signup=findViewById(R.id.sign_up);
        signin=findViewById(R.id.sign_in);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,signup.class);
                startActivity(i);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emails=email.getText().toString().trim();
                String passs=pass.getText().toString().trim();
                if(TextUtils.isEmpty(emails)){
                    email.setError("required");
                    return;

                }
                if(TextUtils.isEmpty(passs)){
                    pass.setError("required");
                    return;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}
