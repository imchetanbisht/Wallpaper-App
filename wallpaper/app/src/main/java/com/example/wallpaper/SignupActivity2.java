package com.example.wallpaper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity2 extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword;
    private Button signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup2);
        TextView signin1=findViewById(R.id.login_text);
        signin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go=new Intent(SignupActivity2.this,LoginActivity2.class);
                startActivity(go);
            }
        });
        auth= FirebaseAuth.getInstance();
        signupEmail=findViewById(R.id.signup_email);
        signupPassword=findViewById(R.id.signup_password);
        signupButton=findViewById(R.id.signupbtn);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= signupEmail.getText().toString().trim();
                String pass= signupPassword.getText().toString().trim();
                if(user.isEmpty()){
                    signupEmail.setError("Email cannot be empty");
                }
                if(pass.isEmpty()){
                    signupPassword.setError("Password cannot be empty");
                } else{
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignupActivity2.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity2.this,LoginActivity2.class));
                            } else{
                                Toast.makeText(SignupActivity2.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}