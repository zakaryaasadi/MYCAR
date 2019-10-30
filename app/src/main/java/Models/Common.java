package Models;

import android.bluetooth.BluetoothDevice;
import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.view.Display;

public class Common {

    public static BluetoothDevice device;

    public static int getScreenHeight(FragmentActivity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }
}
