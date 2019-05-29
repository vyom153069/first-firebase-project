package vyomchandra.com.firebasecompleteproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signup extends AppCompatActivity {

    private EditText email,pass;
    private Button signin,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup=findViewById(R.id.sign_up_in);
       signin=findViewById(R.id.sign_in);
       email=findViewById(R.id.email_signup);
       pass=findViewById(R.id.password_signup);
       signin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(signup.this,MainActivity.class);
               startActivity(i);
           }
       });
       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String emails=email.getText().toString().trim();
               String passs=pass.getText().toString().trim();
               if(TextUtils.isEmpty(emails)){
                   email.setError("required");

               }
               if(TextUtils.isEmpty(passs)){
                   pass.setError("required");
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
