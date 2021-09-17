package com.hyunsoo.every;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;

public class community extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        ListView listView = findViewById(R.id.listView);
        communityAdapter adapter = new communityAdapter();
        adapter.addItem(new communityitem("닌텐도 스위치 일주일동안 빌려봅니다!", "올라와있는것보다 조금 싸게 구해봅니다. 연락주세요"));
        adapter.addItem(new communityitem("광운대역 근처 전동드릴 급하게 필요해요", "지금 당장 필요한데 한시간정도 사용할 예정입니다. 제발요"));
        adapter.addItem(new communityitem("빔프로젝터 싸게 빌릴 수 있을까요?", "종강기념 영화좀 큰 화면으로 보고싶어요.."));
        adapter.addItem(new communityitem("배드민턴 세트 구합니다", "같이 치실분도 연락주세요 ㅎㅎㅎ"));
        adapter.addItem(new communityitem("못좀 박아주실분 계신가요", "망치도 없고 혼자서는 못하겠어요 ㅠㅠㅠ"));

        listView.setAdapter(adapter);

        //뒤로가기
        ImageButton back=(ImageButton)findViewById(R.id.imageButton9);
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
                Intent intent = new Intent(community.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //상품추가버튼
        ImageButton addbutton = (ImageButton)findViewById(R.id.addbutton);
        addbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(community.this, additem.class);
                startActivity(intent);
            }
        });
        //알람버튼
        ImageView alrambtn=(ImageButton)findViewById(R.id.alarmbtn);
        alrambtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(community.this, setAlarm.class);
                startActivity(intent);
            }
        });
    }
    class communityAdapter extends BaseAdapter

    {
        ArrayList<communityitem> items = new ArrayList<communityitem>();
        @Override
        public int getCount() {
            return items.size();
        }
        public void addItem(communityitem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // 데이터 관리 및 뷰 생성
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            communityView communityView = null;

            if(convertView == null) {
                communityView = new communityView(getApplicationContext());
            } else {
                communityView = (communityView)convertView;
            }
            communityitem item = items.get(position);
            communityView.setTitle(item.getTitle());
            communityView.setContent(item.getContent());

            return communityView;
        }
    }
}