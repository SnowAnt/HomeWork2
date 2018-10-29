package snowant.com.homework2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.text.SimpleDateFormat;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snowant.com.homework2.data.DataUtils;

public class NewsListActivity extends AppCompatActivity {

    private static final int SPAN_COUNT = 2;
    private  ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Disposable disposable;

    private final RecyclerAdapter.OnItemClickListener clickListener = newsItem -> {
        NewsDetailsActivity.start(this, newsItem.getCategory().getName(), newsItem.getImageUrl(), newsItem.getTitle(), new SimpleDateFormat(getResources().getString(R.string.date_format)).format(newsItem.getPublishDate()), newsItem.getFullText());

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        setTitle(getString(R.string.news));
        progressBar=findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);



    }

    @Override
    protected void onStart() {
        super.onStart();
        long testTime=2000L;
        progressBar.setVisibility(View.VISIBLE);
       // recyclerView.setVisibility(View.GONE);
        disposable=Observable.fromCallable(()->DataUtils.generateNews(testTime)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::updateUI);


    }

    private void updateUI(List<NewsItem> list) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(new RecyclerAdapter(this,list,
                clickListener));
        recyclerView.setLayoutManager(getScreenOrientation());
        recyclerView.addItemDecoration(new LinerItemDecorator((int) getResources().getDimension(R.dimen.margin_between_card_view)));
      //  recyclerView.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        disposable.dispose();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_author:
                AboutActivity.start(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private RecyclerView.LayoutManager getScreenOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return new LinearLayoutManager(this);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return new GridLayoutManager(this, SPAN_COUNT);
        } else return null;
    }
}
