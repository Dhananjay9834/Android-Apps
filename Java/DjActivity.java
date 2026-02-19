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

public class DjActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_dj);

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
        songArrayList.add(0,R.raw.jarichya_cholila);
        songArrayList.add(1,R.raw.ratriardhyaratri);
        songArrayList.add(2,R.raw.baaptobaap);
        songArrayList.add(3,R.raw.patlachabailgada);
        songArrayList.add(4,R.raw.sutlamazapadar);
        songArrayList.add(5,R.raw.tumhabaghun);
        songArrayList.add(6,R.raw.gridayivasant);
        songArrayList.add(7,R.raw.januvinarangachnaay);
        songArrayList.add(8,R.raw.dhagalalaglikal);
        songArrayList.add(9,R.raw.arediwano);
        songArrayList.add(10,R.raw.navinpopatha);
        songArrayList.add(11,R.raw.yejoteripayaloki);
        songArrayList.add(12,R.raw.haizumkawali);
        songArrayList.add(13,R.raw.khudagawah);
        songArrayList.add(14,R.raw.koimilgaya);
        songArrayList.add(15,R.raw.dekhnewalone);
        songArrayList.add(16,R.raw.trendingsong);



        mMediaPlayer = MediaPlayer.create(DjActivity.this,songArrayList.get(currentIndex));

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
                mMediaPlayer = MediaPlayer.create(DjActivity.this,songArrayList.get(currentIndex));

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
                mMediaPlayer = MediaPlayer.create(DjActivity.this,songArrayList.get(currentIndex));

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
                    Toast.makeText(DjActivity.this,"Cannot jump backward for 5 seconds",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(DjActivity.this, "Cannot jump forward for 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void songName() {
        if(currentIndex == 0)
        {
            tv_songname.setText("Jarichya Cholila Sonyach Batan");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 1)
        {
            tv_songname.setText("Ratri Ardhya Ratri Dj Song");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 2)
        {
            tv_songname.setText("Baap To Baap Rahega");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 3)
        {
            tv_songname.setText("Patlacha Bailgada");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 4)
        {
            tv_songname.setText("Sutla Maza Padar");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 5)
        {
            tv_songname.setText("Tumha Baghun Tol Maza Gela");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 6)
        {
            tv_songname.setText("Hridayi Vasant Fultana");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 7)
        {
            tv_songname.setText("Janu Vina Rangach Nay");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 8)
        {
            tv_songname.setText("Dhagala Lagli Kala");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 9)
        {
            tv_songname.setText("Are Diwano Muze Pehchano");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 10)
        {
            tv_songname.setText("Navin Popat Ha Dj Song");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 11)
        {
            tv_songname.setText("Yeh Jo Teri Paylo ki Chan...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 12)
        {
            tv_songname.setText("Hai Zumka Wali Por");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 13)
        {
            tv_songname.setText("Khuda Gawah");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 14)
        {
            tv_songname.setText("Koi Mil Gaya Dj Song");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 15)
        {
            tv_songname.setText("Dekhne Walo Ne Kya..");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 16)
        {
            tv_songname.setText("Trending Song Dj Mashup");
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
