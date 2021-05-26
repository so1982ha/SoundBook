package com.example.soundbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Thread playThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView booksPlyer = findViewById(R.id.booksPlyer);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        booksPlyer.setLayoutManager(lm);
        BooksAdapter adapter = new BooksAdapter();
//        ChatAdapterAdapter adapter = new ChatAdapterAdapter();
        booksPlyer.setAdapter(adapter);
//        List<Message> listOfChat = new ArrayList<>();
//        listOfChat.add(new TextMessage());
//        listOfChat.add(new PhotoMessage());
//        listOfChat.add(new TextMessage());
//        listOfChat.add(new PhotoMessage());
//        listOfChat.add(new TextMessage());
//        adapter.setListOfMessage(listOfChat);
        List<OneBook> listOfBooks = new ArrayList<>();
        OneBook a1 = new OneBook();

        a1.bookTitle = "العنوان الاول";
        a1.mediaPlayer = MediaPlayer.create(this, R.raw.a1);
        OneBook a2 = new OneBook();

        a2.bookTitle = "العنوان الثانى";
        a2.mediaPlayer = MediaPlayer.create(this, R.raw.a2);
        OneBook a3 = new OneBook();
        a3.bookTitle = "العنوان الثالث";
        a3.mediaPlayer = MediaPlayer.create(this, R.raw.a3);
        OneBook a5 = new OneBook();
        a5.bookTitle = "الفاتحه";
        a5.mediaPlayer = MediaPlayer.create(this, R.raw.a5);
        listOfBooks.add(a5);
        listOfBooks.add(a1);
        listOfBooks.add(a2);
        listOfBooks.add(a3);
        adapter.setListOfBooks(listOfBooks);



//        TextView BookName = findViewById(R.id.BookName);
//        TextView play = findViewById(R.id.play);
//        TextView stop = findViewById(R.id.stop);
//        TextView skip = findViewById(R.id.Skip10);
//        TextView back = findViewById(R.id.back10);
//        TextView time = findViewById(R.id.bookTime);
//        SeekBar bookSeek = findViewById(R.id.bookSeek);
//        // 2.5     first part = 0         - > 45*60*1000
//        // part2 = ((45*60*1000)+1)       - > ((40*60)+20)*1000
//        // part3 = (((45+40)*60)+20)*1000 - > نهايه الملف
//
//
//        BookName.setText("فصل :-");
//        BookName.append("الحق في المعلومات والمعرفة وتقنياتها");
//
//        // ما بين الاقواس
//        // قسمه او ضرب
//        // جمع اوطرح
//        MediaPlayer bookPlayer = MediaPlayer.create(MainActivity.this, R.raw.a);
//        bookSeek.setMax(bookPlayer.getDuration());
//        playThread = new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                int cPosation = bookPlayer.getCurrentPosition();   //  الوقت الحالى للكتاب
//                int totalTime = bookPlayer.getDuration();
//                while (bookPlayer.isPlaying() && (cPosation < totalTime)) {
//                    try {
//                        sleep(1000);
//                        cPosation = bookPlayer.getCurrentPosition();
//                        bookSeek.setProgress(cPosation);
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                }
//            }
//        };
//        // 10 * 60 * 1000
//        bookPlayer.setLooping(true);
//        View.OnClickListener playClick = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bookPlayer.start();
//
//                playThread = new Thread() {
//                    @Override
//                    public void run() {
//                        super.run();
//                        int cPosation = bookPlayer.getCurrentPosition();   //  الوقت الحالى للكتاب
//                        int totalTime = bookPlayer.getDuration();
//                        while (bookPlayer.isPlaying() && (cPosation < totalTime)) {
//                            try {
//                                sleep(1000);
//                                cPosation = bookPlayer.getCurrentPosition();
//                                bookSeek.setProgress(cPosation);
//                            } catch (Exception e) {
//                                System.out.println(e.getMessage());
//                            }
//                        }
//                    }
//                };
//                playThread.start();
//            }
//        };
//        play.setOnClickListener(playClick);
//
//        View.OnClickListener stopClick = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bookPlayer.pause();
//                Log.e("playerStats", bookPlayer.isPlaying() + "");
//                playThread=null;
//            }
//        };
//
//
//        stop.setOnClickListener(stopClick);
//
//        View.OnClickListener skipClick = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int time = bookPlayer.getCurrentPosition() + (10 * 1000);
//                // الوقت الحال + 10 ثوانى
//                // هل الوقت اكبر من وقت الملف ككل
//                if (time < bookPlayer.getDuration()) {
//                    bookPlayer.seekTo(time);
//
//                } else {
//                    bookPlayer.seekTo(0);
//                }
//                bookSeek.setProgress(bookPlayer.getCurrentPosition());
//
//
//            }
//        };
//        skip.setOnClickListener(skipClick);
//
//        View.OnClickListener backClick = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int time = bookPlayer.getCurrentPosition() - (10 * 1000);
//                if (time >= 0) {
//                    bookPlayer.seekTo(time);
//                } else {
//                    bookPlayer.seekTo(0);
//                }
//                bookSeek.setProgress(bookPlayer.getCurrentPosition());
//
//            }
//        };
//        back.setOnClickListener(backClick);
//
//
//
//        SeekBar.OnSeekBarChangeListener seekMove= new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                  Log.e("seekBar",seekBar.getProgress()+"");
//                    bookPlayer.seekTo(seekBar.getProgress());
//            }
//        };
//        bookSeek.setOnSeekBarChangeListener(seekMove);

    }
}