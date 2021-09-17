package com.hyunsoo.every;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;


public class additem extends AppCompatActivity {
    private static int PICK_IMAGE_REQUEST = 1;
    private ImageView addimg;

    DBHelper helper;
    TextView result;
    EditText etname;
    EditText etprice;
    EditText etcontext;
    Bitmap scaled;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        etname = (EditText)findViewById(R.id.etname);
        etprice = (EditText)findViewById(R.id.etprice);
        etcontext = (EditText)findViewById(R.id.etcontext);

        helper = new DBHelper(this);

        addimg = (ImageView) findViewById(R.id.imageView2);
        addimg.setTag(R.drawable.imagesearch);
        Button insert = (Button)findViewById(R.id.button4);


        //데이터베이스에 상품등록
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etname.getText().toString();
                String context = etcontext.getText().toString();

                //작성된것이 있으면 등록안되게
                if ((name.equals("")) || (etprice.getText().toString().equals("")) || context.equals("") || addimg.getTag().equals(R.drawable.imagesearch) ){
                    Toast.makeText(additem.this, "빈칸없이 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                //모두 작성됐으면 데이터베이스에 상품등록
                else {
                    int price = Integer.parseInt(etprice.getText().toString());
                    byte[] byteimg = getByteToBitmap(scaled);
                    helper.onInsert(name, price, context,byteimg);
                    Toast.makeText(additem.this, "상품등록완료!", Toast.LENGTH_SHORT).show();

                    //상품 등록 후 입력된 값들 초기화
                    addimg.setImageResource(R.drawable.imagesearch);
                    addimg.setTag(R.drawable.imagesearch);
                    etname.setText("");
                    etprice.setText("");
                    etcontext.setText("");
                }
            }
        });




        //뒤로가기
        ImageButton back=(ImageButton)findViewById(R.id.imageButton9);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    //뒤로가기 이벤트 재설정
    @Override
    public void onBackPressed() {
        if ((etname.getText().toString().equals("")) && (etprice.getText().toString().equals("")) && (etcontext.getText().toString().equals("")) && (addimg.getTag().equals(R.drawable.imagesearch))) {
            super.onBackPressed();
        }
        //작성되어 있는 상태에서 뒤로가기시 대화상자
        else {
            AlertDialog.Builder dlg = new AlertDialog.Builder(additem.this);
            dlg.setTitle("작성하시던 내용이 있습니다.");
            dlg.setMessage("뒤로가시면 작성하던 내용이 사라집니다. ");
            dlg.setIcon(R.mipmap.ic_launcher);
            dlg.setNegativeButton("취소", null);
            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    additem.super.onBackPressed();
                }
            });
            dlg.show();
        }


    }

    //파일에서 이미지 불러오기
    public void loadImage(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //이미지를 하나 골랐을때
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {
                Uri uri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                //너무크면 사이즈를 줄임
                int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                scaled = Bitmap.createScaledBitmap(bitmap, 1024, nh, true);
                addimg=(ImageView)findViewById(R.id.imageView2);
                addimg.setImageBitmap(scaled);
                addimg.setTag(scaled);

            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Oops! 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
    //비트맵 이미지를 바이트배열로
    public byte[] getByteToBitmap(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] data = stream.toByteArray();
        return data;
    }

}





