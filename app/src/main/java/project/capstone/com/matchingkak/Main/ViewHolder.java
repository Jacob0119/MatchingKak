package project.capstone.com.matchingkak.Main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by amco1 on 2018-03-17.
 */

public abstract class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) { super(itemView); }
    public abstract void bind(Context context, Object item, final int pos, final OnClickListener listener);
}
