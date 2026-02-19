package com.example.mymusicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RomanticActivity extends AppCompatActivity {

    TextView tv_songname,tv_starttime,tv_totaltime;
    ImageView iv_songcover,ib_previous,ib_backward,ib_play,ib_forward,ib_next;
    SeekBar sb_songprogress;
    MediaPlayer mMediaPlayer;
    private int currentIndex = 0;
    private static int sTime = 0, eTime = 0, oTime = 0, bTime = 5000, fTime = 5000;
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romantic);

        tv_songname = findViewById(R.id.tv_music_songname);
        tv_starttime = findViewById(R.id.tv_music_starttime);
        tv_totaltime = findViewById(R.id.tv_music_totaltime);
        iv_songcover = findViewById(R.id.iv_music_songcover);
        sb_songprogress = findViewById(R.id.sb_music_songprogress);
        ib_previous = findViewById(R.id.ib_music_previous);
        ib_backward = findViewById(R.id.ib_music_backward);
        ib_play = findViewById(R.id.ib_music_play);
        ib_forward = findViewById(R.id.ib_music_forward);
        ib_next = findViewById(R.id.ib_music_next);

        ArrayList<Integer> songArrayList = new ArrayList<>();
        songArrayList.add(0,R.raw.gerua);
        songArrayList.add(1,R.raw.aabhas_ha_new_song);
        songArrayList.add(2,R.raw.aadhir_man);
        songArrayList.add(3,R.raw.dekhnewalone);
        songArrayList.add(4,R.raw.dil_mang_raha);
        songArrayList.add(5,R.raw.falls_in_love);
        songArrayList.add(6,R.raw.gand_ajunhi);
        songArrayList.add(7,R.raw.girlfriend_nastanna);
        songArrayList.add(8,R.raw.is_kadar);
        songArrayList.add(9,R.raw.jee_bhar_ke);
        songArrayList.add(10,R.raw.jhumka);
        songArrayList.add(11,R.raw.kevdyach_paan);
        songArrayList.add(12,R.raw.koli);
        songArrayList.add(13,R.raw.majhi_baai_go);
        songArrayList.add(14,R.raw.majhi_janu_mi_single);
        songArrayList.add(15,R.raw.mla_ved_lagle);
        songArrayList.add(16,R.raw.mi_nand_khula);
        songArrayList.add(17,R.raw.paul_takl_nhi);
        songArrayList.add(18,R.raw.midnight);
        songArrayList.add(19,R.raw.pirmachi_lagan);
        songArrayList.add(20,R.raw.tere_hawale);
        songArrayList.add(21,R.raw.tumse_hi);
        songArrayList.add(22,R.raw.vibesoflove);


        mMediaPlayer = MediaPlayer.create(RomanticActivity.this,songArrayList.get(currentIndex));

        ib_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMediaPlayer!=null&&mMediaPlayer.isPlaying())
                {
                    mMediaPlayer.pause();
                    ib_play.setImageResource(R.drawable.icon_play);
                }
                else
                {
                    mMediaPlayer.start();
                    ib_play.setImageResource(R.drawable.icon_pause);
                }

                eTime = mMediaPlayer.getDuration();
                sTime = mMediaPlayer.getCurrentPosition();

                if(oTime == 0)
                {
                    sb_songprogress.setMax(eTime);
                    oTime = 1;
                }

                tv_starttime.setText(String.format("%d : %d",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))
                ));

                tv_totaltime.setText(String.format("%d : %d",
                        TimeUnit.MILLISECONDS.toMinutes(eTime),
                        TimeUnit.MILLISECONDS.toSeconds(eTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(eTime))
                ));

                sb_songprogress.setProgress(sTime);
                h.postDelayed(UpdateSongTime,1000);
                songName();
            }


        });
        ib_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndex>0)
                {
                    currentIndex--;
                }
                else
                {
                    currentIndex = songArrayList.size()-1;
                }

                if(mMediaPlayer.isPlaying())
                {
                    mMediaPlayer.stop();
                }
                if(mMediaPlayer!=null)
                {
                    ib_play.setImageResource(R.drawable.icon_pause);
                }
                mMediaPlayer = MediaPlayer.create(RomanticActivity.this,songArrayList.get(currentIndex));

                eTime = mMediaPlayer.getDuration();
                sTime = mMediaPlayer.getCurrentPosition();
                oTime = 0;
                if(oTime == 0)
                {
                    sb_songprogress.setMax(eTime);
                    oTime = 1;
                }

                tv_starttime.setText(String.format("%d : %d",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))
                ));

                tv_totaltime.setText(String.format("%d : %d",
                        TimeUnit.MILLISECONDS.toMinutes(eTime),
                        TimeUnit.MILLISECONDS.toSeconds(eTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(eTime))
                ));

                sb_songprogress.setProgress(sTime);
                h.postDelayed(UpdateSongTime,1000);
                mMediaPlayer.start();
                songName();
            }
        });

        ib_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndex<songArrayList.size()-1)
                {
                    currentIndex++;
                }
                else
                {
                    currentIndex = 0;
                }
                if(mMediaPlayer.isPlaying())
                {
                    mMediaPlayer.stop();
                }
                if(mMediaPlayer!=null)
                {
                    ib_play.setImageResource(R.drawable.icon_pause);
                }
                mMediaPlayer = MediaPlayer.create(RomanticActivity.this,songArrayList.get(currentIndex));

                eTime = mMediaPlayer.getDuration();
                sTime = mMediaPlayer.getCurrentPosition();
                oTime = 0;
                if(oTime == 0)
                {
                    sb_songprogress.setMax(eTime);
                    oTime = 1;
                }

                tv_starttime.setText(String.format("%d : %d",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))
                ));

                tv_totaltime.setText(String.format("%d : %d",
                        TimeUnit.MILLISECONDS.toMinutes(eTime),
                        TimeUnit.MILLISECONDS.toSeconds(eTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(eTime))
                ));

                sb_songprogress.setProgress(sTime);
                h.postDelayed(UpdateSongTime,1000);
                mMediaPlayer.start();
                songName();
            }
        });

        ib_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((sTime-bTime)>0)
                {
                    sTime = sTime - bTime;
                    mMediaPlayer.seekTo(sTime);
                }
                else
                {
                    Toast.makeText(RomanticActivity.this,"Cannot jump backward for 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });
        ib_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((sTime+fTime) < eTime)
                {
                    sTime = sTime+fTime;
                    mMediaPlayer.seekTo(sTime);
                }
                else
                {
                    Toast.makeText(RomanticActivity.this, "Cannot jump forward for 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void songName() {
        if(currentIndex == 0)
        {
            tv_songname.setText("DilWale...");
            iv_songcover.setImageResource(R.drawable.gerua);
        }
        if (currentIndex == 1)
        {
            tv_songname.setText("Aabhas Haa...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 2)
        {
            tv_songname.setText("Aadhir Man Zale");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 3)
        {
            tv_songname.setText("Dekhne Walo ne Kya Kya nahi...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 4)
        {
            tv_songname.setText("Dil Mang Raha Hain Molat");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 5)
        {
            tv_songname.setText("Fall In Love");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 6)
        {
            tv_songname.setText("Gandh Ajunhi...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 7)
        {
            tv_songname.setText("Girlfriend Nastanna");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 8)
        {
            tv_songname.setText("Is Kadar Tumdse Hame Pyar...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 9)
        {
            tv_songname.setText("Jee Bharke Tumko Dekha Karu");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 10)
        {
            tv_songname.setText("Jhumka new Song");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 11)
        {
            tv_songname.setText("Kevdyach Paan Tu");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 12)
        {
            tv_songname.setText("Govyachya Kinaryavar");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 13)
        {
            tv_songname.setText("Maazi Baay Go");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 14)
        {
            tv_songname.setText("Maajhi Janu Mi Single Haay");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 15)
        {
            tv_songname.setText("Mala Ved Lagle");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 16)
        {
            tv_songname.setText("Mi Nand Khula");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 17)
        {
            tv_songname.setText("Paul Takla Nahi");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 18)
        {
            tv_songname.setText("Midnight Relaxing Song");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 19)
        {
            tv_songname.setText("Pirmachi Lagan");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 20)
        {
            tv_songname.setText("Tere Hawale...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 21)
        {
            tv_songname.setText("Tum Se Hi");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 22)
        {
            tv_songname.setText("Vibes Of Love Mashup");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }

        sb_songprogress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                {
                    mMediaPlayer.seekTo(progress);
                    sb_songprogress.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            sTime = mMediaPlayer.getCurrentPosition();
            tv_starttime.setText(String.format("%d : %d",
                    TimeUnit.MILLISECONDS.toMinutes(sTime),
                    TimeUnit.MILLISECONDS.toSeconds(sTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))
            ));
            sb_songprogress.setProgress(sTime);
            h.postDelayed(this,1000);
        }
    };
}
