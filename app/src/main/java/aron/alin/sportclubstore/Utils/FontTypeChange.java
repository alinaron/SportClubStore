package aron.alin.sportclubstore.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;

import aron.alin.sportclubstore.R;

public class FontTypeChange {
    private Context c;

    public FontTypeChange(Context s) {
        this.c=s;
    }

    public Typeface get_fontface(int n)
    {
        Typeface tf;
        if(n==1)
            tf=ResourcesCompat.getFont(c, R.font.kreditback);
        else if(n==2)
            tf=ResourcesCompat.getFont(c, R.font.kreditfront);
        else if(n==3)
            tf=ResourcesCompat.getFont(c, R.font.ocramedium);
        else
            tf=ResourcesCompat.getFont(c, R.font.kreditfront);
        return tf;
    }
}
