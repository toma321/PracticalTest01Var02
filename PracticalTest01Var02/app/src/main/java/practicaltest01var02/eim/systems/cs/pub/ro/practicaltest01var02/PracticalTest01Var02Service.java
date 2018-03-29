package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import java.util.Random;

public class PracticalTest01Var02Service extends Service {

    private Integer a = new Integer(0);
    private Integer b = new Integer(0);;

    public PracticalTest01Var02Service() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle extras = intent.getExtras();

        try {
            a = Integer.parseInt(extras.getString("a"));
            b = Integer.parseInt(extras.getString("b"));
        }
        catch (Exception e) {

        }


        Thread thread = new Thread() {


            @Override
            public void run() {
                while (true)
                {
                    sendBroadCast();
                    mySleep();
                }
            }
        };

        thread.start();


        return super.onStartCommand(intent, flags, startId);
    }

    public void sendBroadCast() {




        Intent intentBroadCast = new Intent("sum");
        intentBroadCast.putExtra("mesaj", a + b + "");
        sendBroadcast(intentBroadCast);


        intentBroadCast = new Intent("dif");
        intentBroadCast.putExtra("mesaj", a - b + "");
        sendBroadcast(intentBroadCast);

        stopSelf();


    }

    public void mySleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
