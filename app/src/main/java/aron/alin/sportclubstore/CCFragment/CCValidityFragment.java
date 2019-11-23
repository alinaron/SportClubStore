package aron.alin.sportclubstore.CCFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import aron.alin.sportclubstore.CardFrontFragment;
import aron.alin.sportclubstore.CheckOutActivity;
import aron.alin.sportclubstore.R;
import aron.alin.sportclubstore.Utils.CreditCardEditText;
import aron.alin.sportclubstore.Utils.CreditCardExpiryTextWatcher;

public class CCValidityFragment extends Fragment {
    CreditCardEditText et_validity;
    TextView tv_validity;

    CheckOutActivity activity;
    CardFrontFragment cardFrontFragment;

    public CCValidityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ccvalidity, container, false);
        et_validity = view.findViewById(R.id.et_validity);

        activity = (CheckOutActivity) getActivity();
        cardFrontFragment = activity.cardFrontFragment;


        tv_validity = cardFrontFragment.getValidity();
        et_validity.addTextChangedListener(new CreditCardExpiryTextWatcher(et_validity, tv_validity));

        et_validity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    if (activity != null) {
                        activity.nextClick();
                        return true;
                    }

                }
                return false;
            }
        });

        et_validity.setOnBackButtonListener(new CreditCardEditText.BackButtonListener() {
            @Override
            public void onBackClick() {
                if(activity!=null)
                    activity.onBackPressed();
            }
        });


        return view;
    }

    public String getValidity()
    {
        if(et_validity!=null)
            return et_validity.getText().toString().trim();

        return null;
    }
}
