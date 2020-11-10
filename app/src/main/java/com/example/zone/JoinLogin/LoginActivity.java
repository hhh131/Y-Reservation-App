package com.example.zone.JoinLogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.zone.MainActivity;
import com.example.zone.R;
import com.example.zone.Vo.UserVO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class LoginActivity extends Activity {
    private FirebaseAuth mAuth;
    private static final String TAG = "login";
    EditText id,pwd;
    Button loginbtn,joinbtn;
    Intent intent;
    String token;
    public static Boolean loginStatus = false;
    public static String loginId = "";
    String loginInfo;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        loginbtn = (Button) findViewById(R.id.loginbtn);
        joinbtn = (Button) findViewById(R.id.joinbtn);
        id = (EditText) findViewById(R.id.id);
        pwd = (EditText) findViewById(R.id.pwd);

        mAuth = FirebaseAuth.getInstance();
        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });




        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.getText().toString().equals("") && pwd.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "아이디 비밀번호를 입력하세요", Toast.LENGTH_SHORT);
                    toast.show();
                } else {

                    loginCheck(id.getText().toString(),pwd.getText().toString());


                  /*  mAuth.signInWithEmailAndPassword(id.getText().toString(), pwd.getText().toString())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });*/
                }
            }
        });

    }
    public void loginCheck(final String id, final String pwd) {




        final Query query = myRef.child("User");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot datasnapshot) {

                if (datasnapshot.hasChild(id)) {
                    if (datasnapshot.child(id).child("pwd").getValue().equals(pwd)) {
                        final int report=Integer.parseInt(datasnapshot.child(id).child("report").getValue().toString());
                        // showToast(report);
                        showToast("로그인 되었습니다.");
                        Log.e("loginCheck : ", id + "로그인되었습니다.");
                        loginStatus = true;
                        loginId = id;


                        FirebaseMessaging.getInstance().getToken()
                                .addOnCompleteListener(new OnCompleteListener<String>() {
                                    @Override
                                    public void onComplete(@NonNull Task<String> task) {
                                        if (!task.isSuccessful()) {
                                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                            return;
                                        }

                                        // Get new FCM registration token
                                        token = task.getResult();

                                        // Log and toast
                                        //String msg = getString(R.string.msg_token_fmt, token);
                                        //Log.d(TAG, msg);
                                        // Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();



                                        UserVO userVO = new UserVO(id,pwd,report,token);
                                        myRef.child("User").child(id).setValue(userVO)//User아래에 userVO객체 정보로 DB에 정보 삽입
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.e(TAG,"토큰 성공");
                                                        //showToast("회원가입 성공");
                                                        //finish();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.e(TAG,"토큰 실패");
                                                        //showToast("회원가입 실패");

                                                    }
                                                });
                                    }
                                });




                          finish();
                            intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);




                    } else {
                        Log.e("loginCheck : ", "비밀번호가 틀립니다.");
                        showToast("비밀번호가 틀립니다.");
                    }
                } else {
                    Log.e("loginCheck : ", "해당 아이디가 존재하지 않습니다.");
                    showToast("해당아이디가 존재하지 않습니다.");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
            }
        });
    }
    public void showToast(String msg)
    {
        Toast toast = Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
        toast.show();
    }






}

