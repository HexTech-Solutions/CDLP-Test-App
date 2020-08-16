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
import com.hextech.cdlpreptest.QuestionStatsActivity;
import com.hextech.cdlpreptest.R;
import com.hextech.cdlpreptest.ReviewViewAdapter;
import com.hextech.cdlpreptest.util.DBHelper;

public class ReviewFragment extends Fragment implements ReviewViewAdapter.OnReviweListener{

    final int WEAK_COUNT_LEVEL = 7;
    final int MEDIUM_COUNT_LEVEL = 4;
    final int STRONG_COUNT_LEVEL = 8;
    final int FAMILIAR_COUNT_LEVEL = 15;
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
        context = container.getContext();

        ReviewViewAdapter reviewViewAdapter = new ReviewViewAdapter(context, reviewTitle, icons, this);
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

        String whereClause = "";
        switch (position){
            case 0:
                whereClause = "(" + DBHelper.DatabaseTableColumns.WRONG_COUNT.toString() + "-" + DBHelper.DatabaseTableColumns.CORRECT_COUNT.toString()
                        + ") >" + WEAK_COUNT_LEVEL;
                break;
            case 1:
                whereClause = "(" + DBHelper.DatabaseTableColumns.CORRECT_COUNT.toString() + "-" + DBHelper.DatabaseTableColumns.WRONG_COUNT.toString()
                        + ") BETWEEN " + MEDIUM_COUNT_LEVEL + " AND " + STRONG_COUNT_LEVEL;
                break;
            case 2:
                whereClause = "(" + DBHelper.DatabaseTableColumns.CORRECT_COUNT.toString() + "-" + DBHelper.DatabaseTableColumns.WRONG_COUNT.toString()
                        + ") >=" + STRONG_COUNT_LEVEL;
                break;
            case 3:
                whereClause = "(" + DBHelper.DatabaseTableColumns.WRONG_COUNT.toString() + "+" + DBHelper.DatabaseTableColumns.CORRECT_COUNT.toString()
                        + ") >" + FAMILIAR_COUNT_LEVEL;
                break;
            case 4:
                whereClause = DBHelper.DatabaseTableColumns.FAVORITE.toString() + "=" + 1;
                break;
        }

        Intent intent = new Intent(context, QuestionStatsActivity.class);
        intent.putExtra("q_stat_where_clause", whereClause);
        startActivity(intent);
    }
}
