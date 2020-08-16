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
import com.hextech.cdlpreptest.QuestionStats;
import com.hextech.cdlpreptest.R;
import com.hextech.cdlpreptest.ReviewViewAdapter;

public class ReviewFragment extends Fragment implements ReviewViewAdapter.OnReviweListener{
    View view;
    AdView adView;
    RecyclerView recyclerView;
    String reviewTitle[], reviewQuestionCount[];
    int icons[] = {R.drawable.weak, R.drawable.medium, R.drawable.strong, R.drawable.familiar, R.drawable.heart};
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_review,container,false);
        addMobAd();

        recyclerView = view.findViewById(R.id.reviewView);
        reviewTitle = getResources().getStringArray(R.array.review_titles);
        reviewQuestionCount = new String[]{"0 questions", "0 questions", "0 questions", "0 questions", "0 questions"};
        context = container.getContext();

        ReviewViewAdapter reviewViewAdapter = new ReviewViewAdapter(context, reviewTitle, reviewQuestionCount, icons, this);
        recyclerView.setAdapter(reviewViewAdapter);
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
    public void onReviewClick(int position) {
        Intent intent = new Intent(context, QuestionStats.class);
        startActivity(intent);
    }
}
