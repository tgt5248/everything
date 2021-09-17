package com.hyunsoo.every;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String str,str2;
    private Button button2;
    private ImageButton addbutton,communitybtn;
    private EditText et1;
    private ImageView im1,im2,im3,im4,im5,im6;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,more1,more2;

    DBHelper dbHelper=new DBHelper(this);
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        button2 = findViewById(R.id.button2);
        addbutton = findViewById(R.id.addbutton);
        communitybtn = findViewById(R.id.communitybtn);

        more1=(TextView)findViewById(R.id.textView6);
        more2=(TextView)findViewById(R.id.textView8);
        im1= (ImageView)findViewById(R.id.imageView8);
        im2= (ImageView)findViewById(R.id.imageView9);
        im3= (ImageView)findViewById(R.id.imageView10);
        im4= (ImageView)findViewById(R.id.imageView52);
        im5= (ImageView)findViewById(R.id.imageView50);
        im6= (ImageView)findViewById(R.id.imageView51);
        tv1=(TextView)findViewById(R.id.textView39);
        tv2=(TextView)findViewById(R.id.textView40);
        tv3=(TextView)findViewById(R.id.textView41);
        tv4=(TextView)findViewById(R.id.textView52);
        tv5=(TextView)findViewById(R.id.textView50);
        tv6=(TextView)findViewById(R.id.textView51);
        LinearLayout layout1=(LinearLayout)findViewById(R.id.layout1);
        LinearLayout layout2=(LinearLayout)findViewById(R.id.layout2);
        LinearLayout layout3=(LinearLayout)findViewById(R.id.layout3);
        LinearLayout layout4=(LinearLayout)findViewById(R.id.layout4);
        LinearLayout layout5=(LinearLayout)findViewById(R.id.layout5);
        LinearLayout layout6=(LinearLayout)findViewById(R.id.layout6);

        tv1.setText(dbHelper.getname_new(1,""));
        tv2.setText(dbHelper.getname_new(2,""));
        tv3.setText(dbHelper.getname_new(3,""));
        tv4.setText(dbHelper.getname_new(4,""));
        tv5.setText(dbHelper.getname_new(5,""));
        tv6.setText(dbHelper.getname_new(6,""));
        im1.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(tv1.getText().toString())));
        im2.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(tv2.getText().toString())));
        im3.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(tv3.getText().toString())));
        im4.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(tv4.getText().toString())));
        im5.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(tv5.getText().toString())));
        im6.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(tv6.getText().toString())));


        layout1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, itemcontent.class);
                str2=tv1.getText().toString();
                intent.putExtra("str2",str2);
                startActivity(intent);
            }
        });
        layout2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, itemcontent.class);
                str2=tv2.getText().toString();
                intent.putExtra("str2",str2);
                startActivity(intent);
            }
        });
        layout3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, itemcontent.class);
                str2=tv3.getText().toString();
                intent.putExtra("str2",str2);
                startActivity(intent);
            }
        });
        layout4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, itemcontent.class);
                str2=tv4.getText().toString();
                intent.putExtra("str2",str2);
                startActivity(intent);
            }
        });
        layout5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, itemcontent.class);
                str2=tv5.getText().toString();
                intent.putExtra("str2",str2);
                startActivity(intent);
            }
        });
        layout6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, itemcontent.class);
                str2=tv6.getText().toString();
                intent.putExtra("str2",str2);
                startActivity(intent);
            }
        });
        more1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, find2.class);
                intent.putExtra("str","");
                startActivity(intent);
            }
        });

        more2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, community.class);
                startActivity(intent);
            }
        });

        //검색버튼
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, find2.class);
                str=et1.getText().toString().trim();
                intent.putExtra("str",str); //edittext에 있는 문자열을 intent에 포함시켜 보냄.
                startActivity(intent);
            }
        });

        //에디트텍스트 엔터 이벤트
        et1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Intent intent = new Intent(MainActivity.this, find2.class);
                    str=et1.getText().toString().trim();
                    intent.putExtra("str",str);
                    startActivity(intent);
                }
                return false;
            }
        });

        //상품추가버튼
        addbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, additem.class);
                startActivity(intent);
            }
        });

        //커뮤니티버튼
        communitybtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, community.class);
                startActivity(intent);
            }
        });
        //알람버튼
        ImageView alrambtn=(ImageButton)findViewById(R.id.alarmbtn);
        alrambtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, setAlarm.class);
                startActivity(intent);
            }
        });

    }

    @Override
    //뒤로가기시 대화상자 경고 후 앱 종료
    public void onBackPressed() {
        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("종료");
        dlg.setMessage("확인을 누르면 앱이 종료됩니다.");
        dlg.setIcon(R.mipmap.ic_launcher);
        dlg.setNegativeButton("취소", null);
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.finishAffinity(MainActivity.this);
                System.exit(0);
            }
        });
        dlg.show();
    }
}