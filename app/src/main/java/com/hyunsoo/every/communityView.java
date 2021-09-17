package com.hyunsoo.every;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;

//레이아웃에 커뮤니티에 저장된 리스트 출력
public class communityView extends LinearLayout {

    TextView textView, textView2;


    public communityView(Context context) {
        super(context);
        init(context);
    }

    public communityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.community_list, this, true);

        textView = findViewById(R.id.Ctitle);
        textView2 = findViewById(R.id.Ccontent);
    }

    public void setTitle(String title) {
        textView.setText(title);
    }

    public void setContent(String content) {
        textView2.setText(content);
    }
}