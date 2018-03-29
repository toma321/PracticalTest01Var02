package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var02MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 123;
    Button button;
    Button button2;
    Button button3;

    EditText editText;
    EditText editText2;
    TextView textView;
    private BroadcastReceiver broadcastReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button4);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText3);
        textView = findViewById(R.id.textView5);


        if (savedInstanceState != null) {
            String a = savedInstanceState.getString("a");
            editText.setText(a);
            String b = savedInstanceState.getString("b");
            editText2.setText(b);
            String result = savedInstanceState.getString("result");
            textView.setText(result);
            Toast.makeText(this, a + " " +  b + " " + result, Toast.LENGTH_SHORT).show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int a = Integer.parseInt(editText.getText().toString());
                    int b = Integer.parseInt(editText2.getText().toString());

                    textView.setText(a + "+" + b + " =" + (a + b) + "");

                } catch (Exception e) {
                    Toast.makeText(PracticalTest01Var02MainActivity.this, "Un numar nu este corect", Toast.LENGTH_SHORT).show();
                }


                Intent service = new Intent(PracticalTest01Var02MainActivity.this, PracticalTest01Var02Service.class);

                service.putExtra("a", editText.getText().toString());
                service.putExtra("b", editText2.getText().toString());

                startService(service);
            }


        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int a = Integer.parseInt(editText.getText().toString());
                    int b = Integer.parseInt(editText2.getText().toString());

                    textView.setText(a + "-" + b + " =" + (a - b) + "");

                } catch (Exception e) {
                    Toast.makeText(PracticalTest01Var02MainActivity.this, "Un numar nu este corect", Toast.LENGTH_SHORT).show();
                }

                Intent service = new Intent(PracticalTest01Var02MainActivity.this, PracticalTest01Var02Service.class);

                service.putExtra("a", editText.getText().toString());
                service.putExtra("b", editText2.getText().toString());

                startService(service);
            }


        });



        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PracticalTest01Var02MainActivity.this, PracticalTest01Var02SecondaryActivity.class);
                intent.putExtra("result", textView.getText().toString());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });




         broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("broadcast", intent.getExtras().getString("mesaj"));
            }
        };


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("sum");

        registerReceiver(broadcastReceiver, intentFilter);


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Toast.makeText(this, "Corect", Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(this, "Incorect", Toast.LENGTH_SHORT).show();
                        break;
                }

            break ;


        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void onSaveInstanceState(Bundle outState) {
        outState.putString("a", editText.getText().toString());
        outState.putString("b", editText2.getText().toString());
        outState.putString("result", textView.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {


        if (savedInstanceState != null) {
            String a = savedInstanceState.getString("a");
            editText.setText(a);
            String b = savedInstanceState.getString("b");
            editText2.setText(b);
            String result = savedInstanceState.getString("result");
            textView.setText(result);
            Toast.makeText(this, a + " " +  b + " " + result, Toast.LENGTH_SHORT).show();
        }


        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        Intent service = new Intent(this, PracticalTest01Var02Service.class);
        stopService(service);

        super.onDestroy();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(broadcastReceiver);
        super.onPause();

    }
}
