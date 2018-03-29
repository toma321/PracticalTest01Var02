package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var02SecondaryActivity extends AppCompatActivity {


    private BroadcastReceiver broadcastReceiver;

    public PracticalTest01Var02SecondaryActivity () {



    }

    TextView textView;
    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_secondary);

        button = findViewById(R.id.button3);
        button2 = findViewById(R.id.button5);
        textView = findViewById(R.id.textView10);

        Intent intent = getIntent();

        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras.containsKey("result"))

                textView.setText(extras.getString("result"));

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_OK, null);
                finish();
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED, null);
                finish();
            }
        });


    }


}
