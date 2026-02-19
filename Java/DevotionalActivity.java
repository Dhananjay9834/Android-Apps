package com.example.mymusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DevotionalActivity extends AppCompatActivity {

    TextView tv_songname,tv_starttime,tv_totaltime;
    ImageView iv_songcover,ib_previous,ib_backward,ib_play,ib_forward,ib_next,iv_share,iv_repeat,iv_like;
    SeekBar sb_songprogress;
    MediaPlayer mMediaPlayer;
    int currentIndex = 0;
    private static int sTime = 0, eTime = 0, oTime = 0, bTime = 5000, fTime = 5000;
    Handler h = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devotional);

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
        iv_share = findViewById(R.id.ib_music_share);
        iv_repeat = findViewById(R.id.ib_music_repeat);
        iv_like = findViewById(R.id.ib_music_like);

        ArrayList<Integer> songArrayList = new ArrayList<>();

        songArrayList.add(0,R.raw.yegathamahabalihanumatki);
        songArrayList.add(1,R.raw.achyutamkeshavamkrushndamodharam);
        songArrayList.add(2,R.raw.vyarth_chinta_hain_jeevan_ki_mp3);
        songArrayList.add(3,R.raw.hanuman_chalisa);
        songArrayList.add(4,R.raw.haripath);
        songArrayList.add(5,R.raw.hum_katha_sunate);
        songArrayList.add(6,R.raw.mauli_abhang);
        songArrayList.add(7,R.raw.radha_krishn_serial_all_songs);
        songArrayList.add(8,R.raw.radhakrishna_soundtrack);
        songArrayList.add(9,R.raw.ram_bhajan_by_shree_hanuman);
        songArrayList.add(10,R.raw.ram_siya_ram_mp3);
        songArrayList.add(11,R.raw.ramayan_ringtone);
        songArrayList.add(12,R.raw.shree_hanuman_chalisa);
        songArrayList.add(13,R.raw.shree_ram_seeta_ram_bhajan);
        songArrayList.add(14,R.raw.aigiri_nandini);
        songArrayList.add(15,R.raw.dev_malhari);
        songArrayList.add(16,R.raw.ekdantay_vakratundaya);
        songArrayList.add(17,R.raw.gau_nako_re_gau_nako_kisna);
        songArrayList.add(18,R.raw.gurucharitrache_kara_parayan);
        songArrayList.add(19,R.raw.jaganyache_deva);
        songArrayList.add(20,R.raw.majhi_pandhirichi_maay);
        songArrayList.add(21,R.raw.mauli_mauli_lyrix);
        songArrayList.add(22,R.raw.new_ganpati_song_2020);
        songArrayList.add(23,R.raw.vaari_chukaychi_nahi);
        songArrayList.add(24,R.raw.vaat_disu_de);
        songArrayList.add(25,R.raw.awghe_garje_pandharpur);
        songArrayList.add(26,R.raw.tulsi_maal_hi_swashachi);
        songArrayList.add(27,R.raw.mulich_navhe_re_kanha);



        mMediaPlayer = MediaPlayer.create(DevotionalActivity.this,songArrayList.get(currentIndex));

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
                mMediaPlayer = MediaPlayer.create(DevotionalActivity.this,songArrayList.get(currentIndex));

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
                mMediaPlayer = MediaPlayer.create(DevotionalActivity.this,songArrayList.get(currentIndex));

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
                    Toast.makeText(DevotionalActivity.this,"Cannot jump backward for 5 seconds",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(DevotionalActivity.this, "Cannot jump forward for 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void songName() {
        if(currentIndex == 0)
        {
            tv_songname.setText("Ye Gatha Mahabali Hanumat Ki");
            iv_songcover.setImageResource(R.drawable.status5);
        }
        if (currentIndex == 1)
        {
            tv_songname.setText("Achutyam Keshavam Krushn Damodharam");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 2)
        {
            tv_songname.setText("Vyarth Chinta Hain Jeevan Ki...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 3)
        {
            tv_songname.setText("Hanuman Chalisa");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 4)
        {
            tv_songname.setText("Haripath");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 5)
        {
            tv_songname.setText("Hum Katha Sunate Ram Sakal...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 6)
        {
            tv_songname.setText("Mauli Abhang");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 7)
        {
            tv_songname.setText("Radha Krishn Serial All Songs");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 8)
        {
            tv_songname.setText("Radha Krishn Soundtrack");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 9)
        {
            tv_songname.setText("Ram Bhajan By Shree Hanuman");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 10)
        {
            tv_songname.setText("Ram Siya Ram");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 11)
        {
            tv_songname.setText("Ramayan Ringtone");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 12)
        {
            tv_songname.setText("Shree Hanuman Chalisa");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 13)
        {
            tv_songname.setText("Shree Ram Sita Ram");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if(currentIndex == 14)
        {
            tv_songname.setText("Aigiri Nandini...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 15)
        {
            tv_songname.setText("Dev Malhari...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 16)
        {
            tv_songname.setText("Ekdantaya Vaktrtundaya...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 17)
        {
            tv_songname.setText("Gau Nako Re Gau Nako Kisna");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 18)
        {
            tv_songname.setText("Guru Charitrache Kara Parayan");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 19)
        {
            tv_songname.setText("Jagnyache Deva...");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 20)
        {
            tv_songname.setText("Maajhi Pandhirichi Maay");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 21)
        {
            tv_songname.setText("Mauli Mauli Lyrix");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 22)
        {
            tv_songname.setText("New Ganpati Song");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 23)
        {
            tv_songname.setText("Vari Chukaychi Naay");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 24)
        {
            tv_songname.setText("Waat Disu De Ga Deva");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 25)
        {
            tv_songname.setText("Awghe Garje Pandharpur");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 26)
        {
            tv_songname.setText("Tulsi Maal Hi Swasanchi");
            iv_songcover.setImageResource(R.drawable.baseline_music_note_24);
        }
        if (currentIndex == 27)
        {
            tv_songname.setText("Mulich Nawhe Re Kanha");
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
