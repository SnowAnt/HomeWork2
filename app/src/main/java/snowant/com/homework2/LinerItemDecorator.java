package snowant.com.homework2;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LinerItemDecorator extends RecyclerView.ItemDecoration {

    private final int space;

    public LinerItemDecorator(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {

        outRect.bottom = space;
        outRect.left = space;

    }


}
