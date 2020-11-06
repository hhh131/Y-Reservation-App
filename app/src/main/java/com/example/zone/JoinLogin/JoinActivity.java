package com.example.zone.JoinLogin;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.zone.R;
import com.example.zone.Vo.UserVO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class JoinActivity extends Activity {
    private static final String TAG = "Join";
    private FirebaseAuth mAuth;
    EditText id,pwd;
    Button Signbtn;
    String data;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String sId,sPwd,sName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        id = (EditText)findViewById(R.id.id);
        pwd = (EditText)findViewById(R.id.pwd);

        Signbtn = (Button) findViewById(R.id.Signbtn);
        //back = (Button) findViewById(R.id.back);
        mAuth = FirebaseAuth.getInstance();
         sId=id.getText().toString();
          sPwd= pwd.getText().toString();






        Signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



    if(!(id.getText().toString().equals("")&&pwd.getText().toString().equals(""))){

        final Query query = myRef.child("User");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(id.getText().toString()))
                {
                    ShowToast("이미 존재하는 아이디입니다.");
                }
                else
                {
                    UserVO userVO = new UserVO(id.getText().toString(),pwd.getText().toString(),0,null);
                    myRef.child("User").child(id.getText().toString()).setValue(userVO)//User아래에 userVO객체 정보로 DB에 정보 삽입
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.e(TAG,"회원가입 성공");
                                    ShowToast("회원가입 성공");
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e(TAG,"회원가입 실패");
                                    ShowToast("회원가입 실패");

                                }
                            });
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



                }
            }
        });



    }
    public void ShowToast(String msg)
    {
        Toast toast = Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
        toast.show();
    }










    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

    }

    public void showMsg()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("회원가입 하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mAuth.createUserWithEmailAndPassword(id.getText().toString(), pwd.getText().toString())
                        .addOnCompleteListener(JoinActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "회원가입 완료");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    finish();
                                  //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(JoinActivity.this, "회원가입에 실패했습니다..",
                                            Toast.LENGTH_SHORT).show();
                                   // updateUI(null);
                                }

                                // ...
                            }
                        });








            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
