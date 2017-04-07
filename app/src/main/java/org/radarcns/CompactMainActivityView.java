package org.radarcns;

import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.radarcns.android.MainActivity;
import org.radarcns.android.MainActivityView;
import org.radarcns.android.RadarConfiguration;
import org.radarcns.android.device.DeviceServiceProvider;
import org.radarcns.android.kafka.ServerStatusListener;
import org.radarcns.data.TimedInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import static org.radarcns.android.RadarConfiguration.DEFAULT_GROUP_ID_KEY;

/**
 * Created by Maxim on 07-04-17.
 */

public class CompactMainActivityView implements Runnable, MainActivityView {
        private static final Logger logger = LoggerFactory.getLogger(org.radarcns.DetailMainActivityView.class);
        private static final int MAX_UI_DEVICE_NAME_LENGTH = 25;
        private static final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);

        // Data formats
        private final DecimalFormat singleDecimal = new DecimalFormat("0.0");
        private final DecimalFormat doubleDecimal = new DecimalFormat("0.00");
        private final DecimalFormat noDecimals = new DecimalFormat("0");

        private final MainActivity mainActivity;
        private final RadarConfiguration radarConfiguration;

        private String previousTopic;
        private TimedInt previousNumberOfTopicsSent;
        private ServerStatusListener.Status previousServerStatus;
        private String newServerStatus;
        private String userId;
        private RadarConfiguration.FirebaseStatus previousFirebaseStatus;

        // View elements
        private View mServerStatusIcon;
        private TextView mServerMessage;
        private Button mGroupIdInputButton;
        private View mFirebaseStatusIcon;
        private TextView mFirebaseMessage;

        CompactMainActivityView(MainActivity activity, RadarConfiguration radarConfiguration) {
            this.radarConfiguration = radarConfiguration;
            this.mainActivity = activity;

            userId = radarConfiguration.getString(DEFAULT_GROUP_ID_KEY);

            initializeViews();

        }

        private void initializeViews() {
            mainActivity.setContentView(R.layout.compact_overview);
        }

    public void update() throws RemoteException {
        mainActivity.runOnUiThread(this);
    }

    @Override
    public void run() {
    }
}
