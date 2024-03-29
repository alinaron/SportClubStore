package aron.alin.sportclubstore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import aron.alin.sportclubstore.Utils.FontTypeChange;
import static  aron.alin.sportclubstore.Utils.CreditCardUtils.AMEX;
import static aron.alin.sportclubstore.Utils.CreditCardUtils.DISCOVER;
import static aron.alin.sportclubstore.Utils.CreditCardUtils.MASTERCARD;
import static aron.alin.sportclubstore.Utils.CreditCardUtils.NONE;
import static aron.alin.sportclubstore.Utils.CreditCardUtils.VISA;
public class CardFrontFragment extends Fragment {

    private TextView tvNumber, tvValidity, tvName;
    private ImageView ivType;
    FontTypeChange fontTypeChange;

    public CardFrontFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_card_front, container, false);
        tvNumber = view.findViewById(R.id.tv_card_number);
        tvName = view.findViewById(R.id.tv_member_name);
        tvValidity = view.findViewById(R.id.tv_validity);
        ivType = view.findViewById(R.id.ivType);
        fontTypeChange=new FontTypeChange(getActivity());
        tvNumber.setTypeface(fontTypeChange.get_fontface(3));
        tvName.setTypeface(fontTypeChange.get_fontface(3));

        return view;
    }

    public TextView getNumber()
    {
        return tvNumber;
    }
    public TextView getName()
    {
        return tvName;
    }
    public TextView getValidity()
    {
        return tvValidity;
    }

    public ImageView getCardType()
    {
        return ivType;
    }


    public void setCardType(int type)
    {
        switch(type)
        {
            case VISA:
                ivType.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_visa));
                break;
            case MASTERCARD:
                ivType.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_mastercard));
                break;
            case AMEX:
                ivType.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_amex));
                break;
            case DISCOVER:
                ivType.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_discover));
                break;
            case NONE:
                ivType.setImageResource(android.R.color.transparent);
                break;

        }


    }

}
