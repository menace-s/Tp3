package ci.esatic.multiservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class appelActivity extends AppCompatActivity {
    private static final int REQUEST_CALL_PERMISSION = 123;
    LinearLayout backtomenu;
    EditText number;
    Button call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appel);
        backtomenu=findViewById(R.id.backtomenu);
        backtomenu = findViewById(R.id.backtomenu);
        number = findViewById(R.id.number);
        call = findViewById(R.id.call);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = number.getText().toString();

                // Vérifier si la permission est déjà accordée
                if (ContextCompat.checkSelfPermission(appelActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    // Vous avez la permission, vous pouvez passer un appel ici
                    makePhoneCall(phoneNumber);
                } else {
                    // Demander la permission à l'utilisateur
                    ActivityCompat.requestPermissions(appelActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
                }
            }
        });

        backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(appelActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Gérer la réponse de l'utilisateur à la demande de permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // L'utilisateur a accordé la permission, vous pouvez passer un appel ici
                String phoneNumber = number.getText().toString();
                makePhoneCall(phoneNumber);
            } else {
                // L'utilisateur a refusé la permission, informez-le ou gérez-le de manière appropriée
                Toast.makeText(this, "Permission refusée pour passer un appel.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Méthode pour passer un appel
    private void makePhoneCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
}