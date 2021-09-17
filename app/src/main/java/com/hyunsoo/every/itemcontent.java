package com.hyunsoo.every;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class itemcontent extends AppCompatActivity {
    private String str2;
    DBHelper dbHelper=new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemcontent);

        //뒤로가기
        ImageButton back=(ImageButton)findViewById(R.id.imageButton10);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //홈버튼
        ImageButton homebutton = (ImageButton)findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(itemcontent.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //상품추가버튼
        ImageButton addbutton = (ImageButton)findViewById(R.id.addbutton);
        addbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(itemcontent.this, additem.class);
                startActivity(intent);
            }
        });

        //커뮤니티버튼
        ImageView communitybtn=(ImageButton)findViewById(R.id.communitybtn);
        communitybtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(itemcontent.this, community.class);
                startActivity(intent);
            }
        });

        //알람버튼
        ImageView alrambtn=(ImageButton)findViewById(R.id.alarmbtn);
        alrambtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(itemcontent.this, setAlarm.class);
                startActivity(intent);
            }
        });

        //화면에 표시
        ImageView itemimg = (ImageView)findViewById(R.id.imageView3);
        TextView nametext=(TextView)findViewById(R.id.textView26);
        TextView pricetext=(TextView)findViewById(R.id.textView27);
        TextView contexttext=(TextView)findViewById(R.id.textView28);
        Intent intent =  getIntent();
        str2=intent.getStringExtra("str2");
        nametext.setText(str2);
        nametext.setText(dbHelper.getname(str2));
        pricetext.setText(dbHelper.getprice(str2)+"원");
        contexttext.setText(dbHelper.getcontext(str2));


        itemimg.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(str2)));
    }
}