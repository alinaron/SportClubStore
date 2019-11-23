package aron.alin.sportclubstore;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import aron.alin.sportclubstore.CCFragment.CCSecureCodeFragment;
import aron.alin.sportclubstore.Utils.FontTypeChange;

public class CardBackFragment extends Fragment {
    private TextView tv_cvv;
    private FontTypeChange fontTypeChange;

    CheckOutActivity activity;
    CCSecureCodeFragment securecode;

    public CardBackFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_card_back, container, false);
        tv_cvv = view.findViewById(R.id.tv_cvv);

        fontTypeChange=new FontTypeChange(getActivity());
        tv_cvv.setTypeface(fontTypeChange.get_fontface(1));

        activity = (CheckOutActivity) getActivity();
        securecode = activity.secureCodeFragment;
        securecode.setCvv(tv_cvv);

        if(!TextUtils.isEmpty(securecode.getValue()))
            tv_cvv.setText(securecode.getValue());

        return view;
    }
}
