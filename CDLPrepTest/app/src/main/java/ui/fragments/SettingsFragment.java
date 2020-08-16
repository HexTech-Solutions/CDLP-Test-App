package ui.fragments;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.hextech.cdlpreptest.BuildConfig;
import com.hextech.cdlpreptest.R;
import com.hextech.cdlpreptest.StudyPlanActivity;

import java.util.Calendar;

public class SettingsFragment extends Fragment {

    Button chanegStateButton,studyPlanButton,sendLoveButton,privacyPolicyButton,tellaFreindButton,notificationButton;
    View view;
    AdView adView;
    Integer selectedState;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings,container,false);

        chanegStateButton  = (Button) view.findViewById(R.id.changeStateBtn);
        studyPlanButton = (Button) view.findViewById(R.id.studyPlanBtn);
        sendLoveButton  = (Button) view.findViewById(R.id.sendLoveBtn);
        privacyPolicyButton = (Button) view.findViewById(R.id.privacyPolicyBtn);
        tellaFreindButton = (Button) view.findViewById(R.id.tellAFriendBtn);
        notificationButton = (Button) view.findViewById(R.id.notificationsBtn);

        changeState();
        studyPlan();
        sendLove();
        privacyPolicyShow();
        tellafriend();
        notificationsView();
        addMobAd();

        return view;
    }

    //show change state popup
    public void changeState(){

        chanegStateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                final Dialog d = new Dialog(v.getContext(), R.style.CustomDialog);
                d.setTitle("Change State");
                d.setContentView(R.layout.dialog_set_state);
                Button okBtn = (Button) d.findViewById(R.id.okButton);
                Button cancelBtn = (Button) d.findViewById(R.id.cancelButton);
                LayoutInflater l = getLayoutInflater();

                    final NumberPicker np = (NumberPicker) d.findViewById(R.id.statePicker);
                //Initializing a new string array with elements
                final String[] values= getResources().getStringArray(R.array.state_names1);

                //Set min, max, wheel and populate.
                np.setMinValue(0);
                selectedState = 0;
                np.setMaxValue(values.length-1);
                np.setWrapSelectorWheel(true);
                np.setDisplayedValues(values);
                    np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        selectedState = newVal;
                    }
                });

                //OK
                okBtn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putInt("State Name", selectedState);
                        editor.commit();
                        d.dismiss(); //close dialog
                    }
                });
                //Cancel
                cancelBtn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        d.dismiss(); //close dialog
                    }
                });
                //Show Dialog
                d.show();
            }
        });
    }

    //show study plan
    public void studyPlan(){

        studyPlanButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), StudyPlanActivity.class);
                startActivity(intent);
            }
        });

    }

    //redirct to play store page
    public void sendLove() {
        sendLoveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                }
            }
        });
    }

    //redirct to privacy policy web page
    // update link
    public void privacyPolicyShow() {
        privacyPolicyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
            }
        });
    }

    //Update link after uploading to playstore
    public void tellafriend() {
        tellaFreindButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out this awesome app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

    //show add notification popup
    public void notificationsView() {
        notificationButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Dialog d = new Dialog(v.getContext(), R.style.CustomDialog);
                d.setTitle("Set Notifications");
                d.setContentView(R.layout.dialog_set_notification);
                Button b1 = (Button) d.findViewById(R.id.button1);
                Button b2 = (Button) d.findViewById(R.id.button2);
                final TimePicker np = (TimePicker) d.findViewById(R.id.timePicker);
                //Cancel
                b1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        d.dismiss(); //close dialog
                    }
                });
                //Ok
                b2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());

                        Calendar newCalender = Calendar.getInstance();

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                        int day = preferences.getInt("Day", 0);
                        int month = preferences.getInt("Month",0);
                        int year = preferences.getInt("Year",0);
                        if(day != 0 && month != 0 && year != 0)
                        {

                            newCalender.set(Calendar.DAY_OF_MONTH, day);
                            newCalender.set(Calendar.MONTH,month);
                            newCalender.set(Calendar.YEAR,year);
                        }

                        Intent intent = new Intent(getActivity().getApplicationContext(), ReminderBroadcast.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(),0,intent,0);

                        AlarmManager alarmManager =  (AlarmManager) getActivity().getApplicationContext().getSystemService(Context.ALARM_SERVICE);

                        calendar.set(Calendar.HOUR_OF_DAY,np.getHour());
                        calendar.set(Calendar.MINUTE,np.getMinute());
                        calendar.set(Calendar.SECOND,0);

                        System.out.println("New Calender"+newCalender.get(Calendar.DAY_OF_MONTH) + ":"+newCalender.get(Calendar.MONTH) + ":"+newCalender.get(Calendar.YEAR));
                        System.out.println("Old calender"+calendar.get(Calendar.DAY_OF_MONTH) + ":"+calendar.get(Calendar.MONTH) + ":"+calendar.get(Calendar.YEAR));

                        if(calendar.before(newCalender)){
                            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
                        }

                        Toast.makeText(getActivity().getApplicationContext(),
                                "Notification Set at "+np.getHour() + ":"+np.getMinute(),
                                Toast.LENGTH_SHORT)
                                .show();
                        d.dismiss(); //close dialog

                    }
                });
                d.show();

            }
        });

    }
    //adding google ads
    public void addMobAd(){
        adView = view.findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);
    }


}



