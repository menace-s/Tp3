package ci.esatic.multiservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView card1,card2,card3,card4,card5,card6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        card1=findViewById(R.id.card1);
        card2=findViewById(R.id.card2);
        card3=findViewById(R.id.card3);
        card4=findViewById(R.id.card4);
        card5=findViewById(R.id.card5);
        card6=findViewById(R.id.card6);

        //ajout d'evenement sur card1
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, musicActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //ajout d'evenement sur card2
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, videoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //ajout d'evenement sur card3
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, appelActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //ajout d'evenement sur card4
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, messageActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //ajout d'evenement sur card5 pour la map
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.google.com/maps/place/Ecole+Sup%C3%A9rieure+Africaine+Tic/@5.290714,-4.0014209,17z/data=!3m1!4b1!4m6!3m5!1s0xfc1e95a7230b815:0xb42441cd6ea4939c!8m2!3d5.290714!4d-3.998846!16s%2Fg%2F11h0r0235?entry=ttu&g_ep=EgoyMDI1MDkyMS4wIKXMDSoASAFQAw%3D%3D");
            }
        });
        //ajout d'evenement sur card6
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://esatic.ci/");
            }
        });
    }

    private void gotoUrl(String s) {
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}