package com.example.gymrat.Muistutukset;

/**
 * @author Jonne
 * Activity-luokka joka pitää listViewn ei scrollattavana
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

/*Tuturialit SQLiten käyttöön ja ohjelman pöyrittämiseen:
https://www.youtube.com/watch?v=312RhjfetP8&ab_channel=freeCodeCamp.org
https://www.youtube.com/watch?v=hJPk50p7xwA&t=17s&ab_channel=Stevdza-San
https://www.youtube.com/watch?v=9t8VVWebRFM&ab_channel=AllCodingTutorials
https://www.youtube.com/watch?v=d-vdKSbXT4E&t=4517s
https://www.youtube.com/watch?v=or_pH92l-IQ&ab_channel=EasyTuto
https://www.youtube.com/watch?v=ASQIvPwQffg&ab_channel=PenguinCoders
https://www.youtube.com/watch?v=Udk6iaR-RXA&list=PLrnPJCHvNZuCfAe7QK2BoMPkv2TGM_b0E&ab_channel=CodinginFlow
*/
/**
 * This class is a subclass of ListView that overrides the onMeasure() method to prevent the ListView
 * from scrolling
 */
public class NoScrollListView extends ListView {

    public NoScrollListView(Context context) {
        super(context);
    }
    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public NoScrollListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    /**
     * A function that is used to make the listview scrollable.
     *
     * @param widthMeasureSpec The width requirements imposed by the parent.
     * @param heightMeasureSpec This is the height that the parent has specified for the child.
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }
}