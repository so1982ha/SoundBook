package com.example.soundbook;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.XmlRow> {

    List<OneBook> listOfBooks = new ArrayList();

    public void setListOfBooks(List<OneBook> listOfBooks) {
        this.listOfBooks = listOfBooks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public XmlRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View xml = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_xml_design, parent, false);
        XmlRow viewHolderObject = new XmlRow(xml);
        return viewHolderObject;
    }

    private Thread playThread;

    @Override
    public void onBindViewHolder(@NonNull XmlRow xmlRow, int position) {
        OneBook book = listOfBooks.get(position);

        xmlRow.viewBookName.setText(book.bookTitle);


        long booTtimeInSconed = book.mediaPlayer.getDuration() / 1000;   //  ( (x/1000) /60 )

        long bookTimeInMinut = booTtimeInSconed / 60;

        booTtimeInSconed = (book.mediaPlayer.getDuration() / 1000) % 60;

        xmlRow.time.setText(bookTimeInMinut + ":" + booTtimeInSconed);

        View.OnClickListener playClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (OneBook oldBook : listOfBooks) {
                    if (oldBook.mediaPlayer.isPlaying()) {
                        oldBook.mediaPlayer.pause();
                    }
                }
                book.mediaPlayer.start();
                playThread = null;
                try {
                    playThread = new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            int cPosation = book.mediaPlayer.getCurrentPosition();   //  الوقت الحالى للكتاب
                            int totalTime = book.mediaPlayer.getDuration();
                            while (book.mediaPlayer.isPlaying() && (cPosation < totalTime)) {
                                try {
                                    sleep(1000);
                                    cPosation = book.mediaPlayer.getCurrentPosition();

                                    xmlRow.seekBarView.setProgress(cPosation);

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    Log.e("Adapter", e.getMessage() + "");
                                }
                            }
                        }
                    };

                    playThread.start();

                } catch (Exception e) {
                    Log.e("Adapter", e.getMessage() + "");
                }
//                for (int i = 0; i < listOfBooks.size(); i++) {
//                    OneBook oldBook=listOfBooks.get(i);
//                    if (oldBook.mediaPlayer.isPlaying()) {
//                        oldBook.mediaPlayer.pause();
//                    }
//                }

            }
        };
        xmlRow.play.setOnClickListener(playClick);
        View.OnClickListener stopClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (book.mediaPlayer.isPlaying()) {
                    book.mediaPlayer.pause();
                }
                playThread = null;
            }
        };
        xmlRow.stop.setOnClickListener(stopClick);


        View.OnClickListener skipClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = book.mediaPlayer.getCurrentPosition() + (10 * 1000);
                // الوقت الحال + 10 ثوانى
                // هل الوقت اكبر من وقت الملف ككل
                if (time < book.mediaPlayer.getDuration()) {
                    book.mediaPlayer.seekTo(time);

                } else {
                    book.mediaPlayer.seekTo(0);
                }
//                bookSeek.setProgress(book.mediaPlayer.getCurrentPosition());
            }
        };
        xmlRow.skip.setOnClickListener(skipClick);


        View.OnClickListener backClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = book.mediaPlayer.getCurrentPosition() - (10 * 1000);
                if (time >= 0) {
                    book.mediaPlayer.seekTo(time);
                } else {
                    book.mediaPlayer.seekTo(0);
                }
                xmlRow.seekBarView.setProgress(book.mediaPlayer.getCurrentPosition());

            }
        };
        xmlRow.back.setOnClickListener(backClick);


        xmlRow.seekBarView.setMax(book.mediaPlayer.getDuration());

        SeekBar.OnSeekBarChangeListener seekMove = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//            Log.e("onProgressChanged ",seekBar.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("onStartTrackingTouch ",seekBar.getProgress()+"");

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("onStopTrackingTouch ",seekBar.getProgress()+"");

                book.mediaPlayer.seekTo(seekBar.getProgress());
            }
        };
        xmlRow.seekBarView.setOnSeekBarChangeListener(seekMove);


    }

    @Override
    public int getItemCount() {
        return listOfBooks.size();
    }

    class XmlRow extends RecyclerView.ViewHolder {
        TextView viewBookName;
        TextView play;
        TextView stop;
        TextView skip;
        TextView back;
        TextView time;
        SeekBar seekBarView;

        public XmlRow(@NonNull View xmlFile) {
            super(xmlFile);
            viewBookName = xmlFile.findViewById(R.id.BookName);
            play = xmlFile.findViewById(R.id.play);
            stop = xmlFile.findViewById(R.id.stop);
            skip = xmlFile.findViewById(R.id.Skip10);
            back = xmlFile.findViewById(R.id.back10);
            time = xmlFile.findViewById(R.id.bookTime);
            seekBarView = xmlFile.findViewById(R.id.bookSeek);
        }
    }
}
