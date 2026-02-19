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

public class BroSisActivity extends AppCompatActivity {

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
        songArrayList.add(0,R.raw.o_behna_raksha_bandhan);
        songArrayList.add(1,R.raw.meri_behna);
        songArrayList.add(2,R.raw.sunladli);
        songArrayList.add(3,R.raw.papamerepapa);
        songArrayList.add(4,R.raw.grandparents);
        songArrayList.add(5,R.raw.poojahaintuzkomaa);
        songArrayList.add(6,R.raw.bahinabaai);
        songArrayList.add(7,R.raw.bestfriend);
        songArrayList.add(8,R.raw.bhaibehenkapyar);
        songArrayList.add(9,R.raw.dostjesibehen);
        songArrayList.add(10,R.raw.kharibiscuit);
        songArrayList.add(11,R.raw.kyuchodkepapa);
        songArrayList.add(12,R.raw.dostjesibehen);
        songArrayList.add(13,R.raw.paai_fufat);
        songArrayList.add(14,R.raw.maazabhauraya);
        songArrayList.add(15,R.raw.mereaasmanhainpapa);
        songArrayList.add(16,R.raw.merabhaitu);
        songArrayList.add(17,R.raw.mummypapaaapkoshukriya);
        songArrayList.add(18,R.raw.maapapa);
        songArrayList.add(19,R.raw.pyaredadaji);
        songArrayList.add(20,R.raw.terayaarhumahi);
        songArrayList.add(21,R.raw.teriunglipakadke);
        songArrayList.add(22,R.raw.teriyaari);
        songArrayList.add(23,R.raw.yaarateriyaari);
        songArrayList.add(24,R.raw.yehdostihumnahi);
        songArrayList.add(25,R.raw.aaji);
        songArrayList.add(26,R.raw.behenpesabse);



        mMediaPlayer = MediaPlayer.create(BroSisActivity.this,songArrayList.get(currentIndex));

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
                mMediaPlayer = MediaPlayer.create(BroSisActivity.this,songArrayList.get(currentIndex));

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
                mMediaPlayer = MediaPlayer.create(BroSisActivity.this,songArrayList.get(currentIndex));

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
                    Toast.makeText(BroSisActivity.this,"Cannot jump backward for 5 seconds",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(BroSisActivity.this, "Cannot jump forward for 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void songName() {
        if(currentIndex == 0)
        {
            tv_songname.setText("O Behna Meri Raksha Kar Bhai Ka Farz Nibhati Hain");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 1)
        {
            tv_songname.setText("Brother Sister Meri Behna");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 2)
        {
            tv_songname.setText("Sun Ladli Main Hu tera Papa");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 3)
        {
            tv_songname.setText("Papa Mere Papa");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 4)
        {
            tv_songname.setText("Grandparents Kavitechi Ool");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 5)
        {
            tv_songname.setText("Pooja Hain Tuzko Maa");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 6)
        {
            tv_songname.setText("Bahinabaai Full Song");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 7)
        {
            tv_songname.setText("Best Friend Song");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 8)
        {
            tv_songname.setText("Bhai Behen Ka Pyar");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 9)
        {
            tv_songname.setText("Dost Jaisi Behna");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 10)
        {
            tv_songname.setText("Khari Biscuit");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 11)
        {
            tv_songname.setText("Kyu Chod Ke Papa Hum Ko Chale Gaye");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 12)
        {
            tv_songname.setText("Dost Jaisi Behna");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 13)
        {
            tv_songname.setText("Paayi Fufat Rutal Kaata");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 14)
        {
            tv_songname.setText("Maaza Bhau Raya");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 15)
        {
            tv_songname.setText("Mera Aasman Hain Papa");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 16)
        {
            tv_songname.setText("Mera Bhai Tu Meri Jaan Hain");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 17)
        {
            tv_songname.setText("Mummy Papa aapko Shukriya");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 18)
        {
            tv_songname.setText("Maa - Papa");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 19)
        {
            tv_songname.setText("Pyare Dadaji Hain");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 20)
        {
            tv_songname.setText("Tera Yaar Hu Main");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 21)
        {
            tv_songname.setText("Teri Ungli Pakad Ke...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 22)
        {
            tv_songname.setText("Teri Yaari...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 23)
        {
            tv_songname.setText("Yaara Teri Yaari");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 24)
        {
            tv_songname.setText("Yeh Dosti Hum Nahi Todenge");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 25)
        {
            tv_songname.setText("Aaji Marathi Poem");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 26)
        {
            tv_songname.setText("Best Song On sister");
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