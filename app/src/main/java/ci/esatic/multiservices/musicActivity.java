package ci.esatic.multiservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class musicActivity extends AppCompatActivity {
    MediaPlayer player;
    LinearLayout backtomenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        backtomenu=findViewById(R.id.backtomenu);

        backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(musicActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void stop(View view) {
        stopPlayer();
    }
    private void stopPlayer(){
        if(player !=null){
            player.release();
            player=null;
        }
    }
    public void play(View view) {
        if (player ==null){
            player=MediaPlayer.create(this,R.raw.yoga);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    public void pause(View view) {
        if(player !=null){
            player.pause();
        }
    }
    protected void onstop(){
        super.onStop();
        stopPlayer();
    }

}