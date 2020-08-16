package ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.hextech.cdlpreptest.LearnViewAdapter;
import com.hextech.cdlpreptest.LevelActivity;
import com.hextech.cdlpreptest.R;

public class LearnFragment extends Fragment implements LearnViewAdapter.OnLearnViewListner {
    View view;
    AdView adView;
    RecyclerView recyclerView;
    String learnTitle[];
    String learnProgress;
    Context context;
    int topic;
    int qCount = 100;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_learn,container,false);
        addMobAd();

        recyclerView = view.findViewById(R.id.learnView);
        learnTitle = getResources().getStringArray(R.array.learn_titles);
        context = container.getContext();

        LearnViewAdapter learnViewAdapter = new LearnViewAdapter(context, learnTitle, this);
        recyclerView.setAdapter(learnViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    //adding google ads
    public void addMobAd(){
        adView = view.findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);
    }

    @Override
    public void onLearnViewClick(int position) {
        String selectedTitle = learnTitle[position];

        switch (selectedTitle){
            case "Weak questions":
                topic = 1;
                break;
            case "Medium questions":
                topic = 1;
                break;
            case "Strong questions":
                topic = 1;
                break;
            case "All familiar questions":
                topic = 1;
                break;
            case "Your favourite questions":
                topic = 1;
                break;
        }

        Intent intent = new Intent(context, LevelActivity.class);
        intent.putExtra("SelectedTest",topic);
        intent.putExtra("QuestionCount1", qCount);
        startActivity(intent);
    }
}
