package com.hyunsoo.every;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;



public class find2 extends AppCompatActivity {
    private String str;
    private String str2;
    private Button button2;
    private EditText et1;
    private Button pb;
    TextView in1;
    TextView in2;
    TextView in3;
    TextView ip1;
    TextView ip2;
    TextView ip3;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;

    DBHelper dbHelper=new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find2);


        pb = (Button)findViewById(R.id.popup);
        et1=(EditText)findViewById(R.id.notext);
        in1=(TextView)findViewById(R.id.textView18);
        in2=(TextView)findViewById(R.id.textView30);
        in3=(TextView)findViewById(R.id.textView32);
        ip1=(TextView)findViewById(R.id.textView20);
        ip2=(TextView)findViewById(R.id.textView22);
        ip3=(TextView)findViewById(R.id.textView34);
        iv1=(ImageView)findViewById(R.id.imageView4);
        iv2=(ImageView)findViewById(R.id.imageView5);
        iv3=(ImageView)findViewById(R.id.imageView6);


        ImageButton back=(ImageButton)findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(find2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //????????? ??????
        TextView textview=(TextView)findViewById(R.id.textView3);
        TextView textview2=(TextView)findViewById(R.id.textView4);
        Intent intent2 =  getIntent();
        str=intent2.getStringExtra("str");
        et1.setText(str);
        LinearLayout layout=(LinearLayout)findViewById(R.id.resultLayout);
        LinearLayout layout2=(LinearLayout)findViewById(R.id.notresult);
        if (!(dbHelper.find(str).equals(""))){
            textview.setText("'" + str + "' ????????????");
            textview2.setText("     ??????????????? '"+str+"'");
            in1.setText(dbHelper.getname_new(1,str));
            in2.setText(dbHelper.getname_new(2,str));
            in3.setText(dbHelper.getname_new(3,str));
            ip1.setText(dbHelper.getprice_new(1,str)+"???");
            ip2.setText(dbHelper.getprice_new(2,str)+"???");
            ip3.setText(dbHelper.getprice_new(3,str)+"???");
            iv1.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(in1.getText().toString())));
            iv2.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(in2.getText().toString())));
            iv3.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(in3.getText().toString())));

            if(in2.getText().toString().equals("")){
                TableRow tb2=(TableRow)findViewById(R.id.table2);
                tb2.setVisibility(View.INVISIBLE);
            }
            if(in3.getText().toString().equals("")){
                TableRow tb3=(TableRow)findViewById(R.id.table3);
                tb3.setVisibility(View.INVISIBLE);
            }
            layout.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
        }
        else {
            textview.setText("'" + str + "'??? ?????? ??????????????? ????????????.");
            layout.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
        }

        //???????????? ?????????
        button2 = findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(find2.this, find2.class);
                str=et1.getText().toString().trim();
                intent.putExtra("str",str);
                startActivity(intent);
            }
        });

        //?????????????????? ?????? ?????????
        et1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Intent intent = new Intent(find2.this, find2.class);
                    str=et1.getText().toString().trim();
                    intent.putExtra("str",str);
                    startActivity(intent);
                }
                return false;
            }
        });

        TableRow table1=(TableRow)findViewById(R.id.table1);
        TableRow table2=(TableRow)findViewById(R.id.table2);
        TableRow table3=(TableRow)findViewById(R.id.table3);

        //??????????????? ???????????????
        table1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(find2.this, itemcontent.class);
                str2=in1.getText().toString();
                if(!str2.equals("")) {
                    intent.putExtra("str2", str2);
                    startActivity(intent);
                }
            }
        });
        table2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(find2.this, itemcontent.class);
                str2=in2.getText().toString();
                if(!str2.equals("")) {
                    intent.putExtra("str2", str2);
                    startActivity(intent);
                }
            }
        });
        table3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(find2.this, itemcontent.class);
                str2=in3.getText().toString();
                if(!str2.equals("")) {
                    intent.putExtra("str2", str2);
                    startActivity(intent);
                }
            }
        });



        //?????????
        ImageButton homebutton = (ImageButton)findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(find2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //??????????????????
        ImageButton addbutton = (ImageButton)findViewById(R.id.addbutton);
        addbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(find2.this, additem.class);
                startActivity(intent);
            }
        });

        //??????????????????
        ImageView communitybtn=(ImageButton)findViewById(R.id.communitybtn);
        communitybtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(find2.this, community.class);
                startActivity(intent);
            }
        });

        //????????????
        ImageView alrambtn=(ImageButton)findViewById(R.id.alarmbtn);
        alrambtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(find2.this, setAlarm.class);
                startActivity(intent);
            }
        });
    }

    //?????? ?????? ????????????
    public void onClickpopup(View button){
        PopupMenu popup=new PopupMenu(this,pb);
        popup.getMenuInflater().inflate(R.menu.popup,popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch(item.getItemId()){
                //????????? ??????????????? ??????
                case R.id.sort_new:
                    Toast.makeText(find2.this, "?????????", Toast.LENGTH_SHORT).show();
                    pb.setText("????????? ???");
                    in1.setText(dbHelper.getname_new(1,str));
                    in2.setText(dbHelper.getname_new(2,str));
                    in3.setText(dbHelper.getname_new(3,str));
                    ip1.setText(dbHelper.getprice_new(1,str)+"???");
                    ip2.setText(dbHelper.getprice_new(2,str)+"???");
                    ip3.setText(dbHelper.getprice_new(3,str)+"???");
                    iv1.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(in1.getText().toString())));
                    iv2.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(in2.getText().toString())));
                    iv3.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(in3.getText().toString())));
                    break;
                //????????????????????? ??????
                case R.id.sort_price:
                    Toast.makeText(find2.this, "???????????????", Toast.LENGTH_SHORT).show();
                    pb.setText("??????????????? ???");
                    in1.setText(dbHelper.getname_price(1,str));
                    in2.setText(dbHelper.getname_price(2,str));
                    in3.setText(dbHelper.getname_price(3,str));
                    ip1.setText(dbHelper.getprice_price(1,str)+"???");
                    ip2.setText(dbHelper.getprice_price(2,str)+"???");
                    ip3.setText(dbHelper.getprice_price(3,str)+"???");
                    iv1.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(in1.getText().toString())));
                    iv2.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(in2.getText().toString())));
                    iv3.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(in3.getText().toString())));
                    break;
                //????????????
                case R.id.sort_review:
                    Toast.makeText(find2.this, "????????????", Toast.LENGTH_SHORT).show();
                    pb.setText("???????????? ???");
                    in1.setText(dbHelper.getname_ko(1,str));
                    in2.setText(dbHelper.getname_ko(2,str));
                    in3.setText(dbHelper.getname_ko(3,str));
                    ip1.setText(dbHelper.getprice_ko(1,str)+"???");
                    ip2.setText(dbHelper.getprice_ko(2,str)+"???");
                    ip3.setText(dbHelper.getprice_ko(3,str)+"???");
                    iv1.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(in1.getText().toString())));
                    iv2.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(in2.getText().toString())));
                    iv3.setImageBitmap(dbHelper.getimage(dbHelper.getbyte(in3.getText().toString())));
                    break;
            }
            return true;
        }
        });
        popup.show();
    }
}