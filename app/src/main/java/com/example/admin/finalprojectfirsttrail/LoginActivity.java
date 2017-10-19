package com.example.admin.finalprojectfirsttrail;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivityTAG";

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    FirebaseAuth mFireBaseAuth;
    private FirebaseUser user;
    private String uid;
    private String phase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFireBaseAuth = FirebaseAuth.getInstance();

        mEmailView = (AutoCompleteTextView) findViewById(R.id.etEmail);
        mPasswordView = (EditText) findViewById(R.id.etPassword);


            Button mEmailSignInButton = (Button) findViewById(R.id.btnLogin);
            mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(! mEmailView.getText().toString().isEmpty() && ! mPasswordView.getText().toString().isEmpty()) {
                        mFireBaseAuth.signInWithEmailAndPassword(mEmailView.getText().toString(), mPasswordView.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user = mFireBaseAuth.getCurrentUser();
                                    if (user != null) {
                                        uid = user.getUid();
                                        showProgress(true);
                                        SelectPhase();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onFailure: "+e.toString());
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Please Make sure you entered Email & Password", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void SelectPhase() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Ref = database.getReference("Consultants_Records").child(uid).child("Phase");
        Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                phase = dataSnapshot.getValue().toString();
                if(phase.equals("Training Phase"))
                {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Project Activity not Created Yet", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onDataChange: "+phase);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showProgress(final Boolean show) {

        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    public void ResetPassword(View view) {

        mFireBaseAuth.sendPasswordResetEmail(mEmailView.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "Mail Sent Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onComplete: "+task.getException().toString());
                }
            }
        });
    }
}
