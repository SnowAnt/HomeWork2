package snowant.com.homework2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    @NonNull
    private List<NewsItem> newsItemList;
    @NonNull
    private final Context context;
    @NonNull
    private final LayoutInflater inflater;
    @Nullable
    private final OnItemClickListener clickListener;

    public RecyclerAdapter(@NonNull Context context, @NonNull List<NewsItem> newsItemList, @NonNull OnItemClickListener clickListener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.newsItemList = newsItemList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                inflater.inflate(R.layout.card_view_news, parent, false), clickListener
        );

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(newsItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsItemList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(NewsItem newsItem);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView categoryTxt;
        private final TextView headerTxt;
        private final TextView previewTxt;
        private final TextView publishDateTxt;
        private final ImageView imageNewsImg;

        public ViewHolder(@NonNull View itemView, @NonNull OnItemClickListener clickListener) {
            super(itemView);

            itemView.setOnClickListener(view -> {

                int position = getAdapterPosition();
                if (clickListener != null && position != RecyclerView.NO_POSITION) {
                    clickListener.onItemClick(newsItemList.get(position));
                }
            });
            categoryTxt = itemView.findViewById(R.id.category_txt);
            headerTxt = itemView.findViewById(R.id.header_txt);
            previewTxt = itemView.findViewById(R.id.preview_txt);
            publishDateTxt = itemView.findViewById(R.id.publish_date_txt);
            imageNewsImg = itemView.findViewById(R.id.image_news_img);
        }

        public void bind(NewsItem newsItem) {
            categoryTxt.setText(newsItem.getCategory().getName());
            headerTxt.setText(newsItem.getTitle());
            previewTxt.setText(newsItem.getPreviewText());
            publishDateTxt.setText(new SimpleDateFormat(context.getResources().getString(R.string.date_format)).format(newsItem.getPublishDate()));
            Glide.with(context).load(newsItem.getImageUrl()).into(imageNewsImg);
        }


    }
}

