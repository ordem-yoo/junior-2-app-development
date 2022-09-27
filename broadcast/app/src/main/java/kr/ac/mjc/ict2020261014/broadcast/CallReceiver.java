package kr.ac.mjc.ict2020261014.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "calling Someone",Toast.LENGTH_SHORT).show();
    }
}
