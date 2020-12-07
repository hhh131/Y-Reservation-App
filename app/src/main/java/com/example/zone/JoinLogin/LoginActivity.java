package com.example.zone.JoinLogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    CheckBox cb_save;
    public static Boolean loginStatus = false;
    public static String loginId = "";
    String loginInfo;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    Context mContext;
    private int login=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cb_save= (CheckBox)findViewById(R.id.cb_save);
            mContext=this;

        loginbtn = (Button) findViewById(R.id.loginbtn);
        joinbtn = (Button) findViewById(R.id.joinbtn);
        id = (EditText) findViewById(R.id.id);
        pwd = (EditText) findViewById(R.id.pwd);
        intent=getIntent();
        login=intent.getIntExtra("login",0);
        mAuth = FirebaseAuth.getInstance();
        boolean boo = PreferenceManager.getBoolean(mContext,"check"); //로그인 정보 기억하기 체크 유무 확인
        if(boo)
        { // 체크가 되어있다면 아래 코드를 수행 //저장된 아이디와 암호를 가져와 셋팅한다.
            id.setText(PreferenceManager.getString(mContext, "id"));
            pwd.setText(PreferenceManager.getString(mContext, "pw"));
            cb_save.setChecked(true); //체크박스는 여전히 체크 표시 하도록 셋팅
            if(login == 0) {
                loginCheck(id.getText().toString(), pwd.getText().toString());
            }else {
                login = 0;
            }
        }

        cb_save.setOnClickListener(new CheckBox.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked())
                {
                    // 체크박스 체크 되어 있으면 editText에서 아이디와 암호 가져와 PreferenceManager에 저장한다.
                    PreferenceManager.setString(mContext, "id", id.getText().toString()); //id 키값으로 저장
                    PreferenceManager.setString(mContext, "pw", pwd.getText().toString()); //pw 키값으로 저장
                    PreferenceManager.setBoolean(mContext, "check", cb_save.isChecked()); //현재 체크박스 상태 값 저장
                } else { //체크박스가 해제되어있으면
                    PreferenceManager.setBoolean(mContext, "check", cb_save.isChecked()); //현재 체크박스 상태 값 저장
                    PreferenceManager.clear(mContext); //로그인 정보를 모두 날림
                }
            }
        }) ;




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

