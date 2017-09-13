package com.example.xi.xiancheng;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;


import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiemian);

        final TextView textView = (TextView)findViewById(R.id.jindu);
        final SeekBar seekBar=(SeekBar)findViewById(R.id.seekBar);
        final Handler handler= new Handler(){
            @Override
            public void handleMessage(Message msg) {
                textView.setText(msg.arg1+"");
                seekBar.setMax(100);
                seekBar.setProgress(msg.arg1);
            }
        };

        final Runnable myWorker = new Runnable() {
                    public void run() {
                        int progress = 0;
                        while(progress <= 100){
                            Message msg = new Message();
                            msg.arg1 = progress;
                            handler.sendMessage(msg);
                            progress += 5;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
        };


        Button button = (Button) findViewById(R.id.s);
        button.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View view) {
                       Thread workThread = new Thread(null, myWorker, "WorkThread");
                       workThread.start();
                   }
        });



    }


}
