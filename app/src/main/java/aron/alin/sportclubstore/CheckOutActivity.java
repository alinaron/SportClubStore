package aron.alin.sportclubstore;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import aron.alin.sportclubstore.CCFragment.CCNameFragment;
import aron.alin.sportclubstore.CCFragment.CCNumberFragment;
import aron.alin.sportclubstore.CCFragment.CCSecureCodeFragment;
import aron.alin.sportclubstore.CCFragment.CCValidityFragment;
import aron.alin.sportclubstore.Clase.Utils;
import aron.alin.sportclubstore.Utils.CreditCardUtils;
import aron.alin.sportclubstore.Utils.ViewPagerAdapter;

public class CheckOutActivity extends FragmentActivity implements FragmentManager.OnBackStackChangedListener, Utils {

    private Button btnNext;

    private Double valoare;
    public CardFrontFragment cardFrontFragment;
    public CardBackFragment cardBackFragment;

    private ViewPager viewPager;

    CCNumberFragment numberFragment;
    CCNameFragment nameFragment;
    CCValidityFragment validityFragment;
    CCSecureCodeFragment secureCodeFragment;

    int total_item;
    boolean backTrack = false;

    private boolean mShowingBack = false;

    String cardNumber, cardCVV, cardValidity, cardName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        btnNext = findViewById(R.id.btnNext);
        cardFrontFragment = new CardFrontFragment();
        cardBackFragment = new CardBackFragment();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            valoare = bundle.getDouble(KEY_VALOARE_PLATA);
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, cardFrontFragment).commit();

        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }

        viewPager =  findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(4);
        setupViewPager(viewPager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == total_item)
                    btnNext.setText("PLĂTEȘTE");
                else
                    btnNext.setText("URMĂTORUL PAS");


                if (position == total_item) {
                    flipCard();
                    backTrack = true;
                } else if (position == total_item - 1 && backTrack) {
                    flipCard();
                    backTrack = false;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = viewPager.getCurrentItem();
                if (pos < total_item) {
                    viewPager.setCurrentItem(pos + 1);
                } else {
                    checkEntries();
                }

            }
        });


    }

    public void checkEntries() {
        cardName = nameFragment.getName();
        cardNumber = numberFragment.getCardNumber();
        cardValidity = validityFragment.getValidity();
        cardCVV = secureCodeFragment.getValue();

        if (TextUtils.isEmpty(cardName)) {
            Toast.makeText(CheckOutActivity.this, "Introduceți un nume valid", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardNumber) || !CreditCardUtils.isValid(cardNumber.replace(" ",""))) {
            Toast.makeText(CheckOutActivity.this, "Introduceți un număr de card valid", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardValidity)||!CreditCardUtils.isValidDate(cardValidity)) {
            Toast.makeText(CheckOutActivity.this, "Introduceți o dată a expirării validă", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardCVV)||cardCVV.length()<3) {
            Toast.makeText(CheckOutActivity.this, "Introduceți un cod de securitate valid", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CheckOutActivity.this, "Plata dumneavoastră în valoare de " + String.format("%.2f", valoare) + " lei a fost efectuată!", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        numberFragment = new CCNumberFragment();
        nameFragment = new CCNameFragment();
        validityFragment = new CCValidityFragment();
        secureCodeFragment = new CCSecureCodeFragment();
        adapter.addFragment(numberFragment);
        adapter.addFragment(nameFragment);
        adapter.addFragment(validityFragment);
        adapter.addFragment(secureCodeFragment);

        total_item = adapter.getCount() - 1;
        viewPager.setAdapter(adapter);

    }

    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }
        // Flip to the back.
        //setCustomAnimations(int enter, int exit, int popEnter, int popExit)

        mShowingBack = true;

        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.fragment_container, cardBackFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        int pos = viewPager.getCurrentItem();
        if (pos > 0) {
            viewPager.setCurrentItem(pos - 1);
        } else
            super.onBackPressed();
    }

    public void nextClick() {
        btnNext.performClick();
    }
}
