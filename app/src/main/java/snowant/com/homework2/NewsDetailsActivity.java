package snowant.com.homework2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NewsDetailsActivity extends AppCompatActivity {

    private static final String ITEM_IMAGE = "ITEM_IMAGE";
    private static final String ITEM_HEADER = "ITEM_HEADER";
    private static final String ITEM_PUBLISH_DATE = "ITEM_PUBLISH_DATE";
    private static final String ITEM_FULL_TEXT = "ITEM_FULL_TEXT";
    private static final String ITEM_CATEGORY_TEXT = "ITEM_CATEGORY_TEXT";

    public static void start(Activity activity,String itemCategory,String itemImage, String itemHeader, String itemPublishDate, String itemFullText) {
        Intent intent = new Intent(activity, NewsDetailsActivity.class);
        intent.putExtra(ITEM_CATEGORY_TEXT, itemCategory);
        intent.putExtra(ITEM_IMAGE, itemImage);
        intent.putExtra(ITEM_HEADER, itemHeader);
        intent.putExtra(ITEM_PUBLISH_DATE, itemPublishDate);
        intent.putExtra(ITEM_FULL_TEXT, itemFullText);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ImageView imageNewsDetailImg=findViewById(R.id.image_news_detail_img);
        TextView headerNewsDetailTxt=findViewById(R.id.header_news_detail_txt);
        TextView publishDateNewsDetailTxt=findViewById(R.id.publish_date_news_detail_txt);
        TextView fullTextNewsDetailTxt=findViewById(R.id.full_text_news_detail_txt);

        setTitle(getIntent().getStringExtra(ITEM_CATEGORY_TEXT));

        Glide.with(this).load(getIntent().getStringExtra(ITEM_IMAGE)).into(imageNewsDetailImg);
        headerNewsDetailTxt.setText(getIntent().getStringExtra(ITEM_HEADER));
        publishDateNewsDetailTxt.setText(getIntent().getStringExtra(ITEM_PUBLISH_DATE));
        fullTextNewsDetailTxt.setText(getIntent().getStringExtra(ITEM_FULL_TEXT));
    }
}
