package ci.esatic.multiservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class messageActivity extends AppCompatActivity {
    //initialisation
    EditText numero,message;
    Button send;
    LinearLayout backtomenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        //assignment
        backtomenu=findViewById(R.id.backtomenu);
        numero=findViewById(R.id.numero);
        message=findViewById(R.id.message);
        send=findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //condition de permission
                if (ContextCompat.checkSelfPermission(messageActivity.this,Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                    sendsms();

                }else{
                    //quand la permission n'est pas accordé
                    //
                    ActivityCompat.requestPermissions(messageActivity.this,new String[]{Manifest.permission.SEND_SMS},100);
                }
            }
        });
        //boutton de retour
        backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(messageActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100 && grantResults.length>0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
            //PERMISSION EST ACCORDE
            //APPEL METHOD
            sendsms();

        }else {
            //QUAND LA PERMISSION N'EST PAS ACCORDE
            Toast.makeText(this,"Permission non accordé!!!",Toast.LENGTH_SHORT).show();
        }
    }

    private void sendsms() {
        String phone=numero.getText().toString();
        String sms=message.getText().toString();
        //condition qui verifie si le string n'est pas vide
        if (!phone.isEmpty() && !sms.isEmpty()){
            //initialisation du sms manager
            SmsManager smsManager=SmsManager.getDefault();
            //envoi du message
            smsManager.sendTextMessage(phone,null,sms,null,null);
            Toast.makeText(this,"SMS envoyé",Toast.LENGTH_SHORT).show();
        }else{
            //quand les strin sont vides
            Toast.makeText(this,"Ajouter un numero et message",Toast.LENGTH_SHORT).show();
        }
    }
}