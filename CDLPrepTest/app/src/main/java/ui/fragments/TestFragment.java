package ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.hextech.cdlpreptest.CustomizeTestActivity;
import com.hextech.cdlpreptest.R;
import com.hextech.cdlpreptest.TestQuestionsActivity;

public class TestFragment extends Fragment {
    View view;
    AdView adView;
    Button btnStartTest, btnCustomizeTest;
    TextView textViewTestInfo2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test,container,false);

        textViewTestInfo2 = view.findViewById(R.id.textViewTestInfo2);
        btnStartTest = view.findViewById(R.id.btnStartTest);
        btnCustomizeTest = view.findViewById(R.id.btnCustomizeTest);

        addMobAd();

        //Gets the number of questions and the number of mistakes allowed defined in test customization.
        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("cdlpref", Context.MODE_PRIVATE); // 0 - for private mode
        int numQuestions = pref.getInt("numQuestions", 50);
        int numMistakes = pref.getInt("numMistakes", 10);

        setTestInfoValues(numQuestions, numMistakes);

        btnStartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TestQuestionsActivity.class);
                startActivity(intent);
            }
        });

        btnCustomizeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CustomizeTestActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    //adding google ads
    public void addMobAd(){

        adView = view.findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);
    }

    /**
     * This updates the number of questions and the number of mistakes values in the test information
     * @param numQuestions - number of questions
     * @param numMistakes - number of mistakes allowed
     */
    private void setTestInfoValues(int numQuestions, int numMistakes){
        Resources res = getResources();
        String text = res.getString(R.string.test_info_2, numQuestions, numMistakes);
        textViewTestInfo2.setText(text);
    }
}
