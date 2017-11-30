package dev.uublabs.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import dev.uublabs.fragments.Fragment.BlueFragment;
import dev.uublabs.fragments.Fragment.YellowFragment;

public class MainActivity extends AppCompatActivity implements YellowFragment.OnFragmentInteractionListener
{

    private static final String BLUE_FRAGMENT_TAG = "Blue Fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addingFragments(View view)
    {
        String value1 = "value1";
        String value2 = "value2";
        switch (view.getId())
        {
            case R.id.btnAddBlue:


                addBlueFragment(value1, value2);

                break;
            case R.id.btnAddYellow:

                addYellowFragment(value1, value2);

                break;
        }
    }

    private void addYellowFragment(String value1, String value2) {
        YellowFragment yellowFragment =
                YellowFragment.newInstance(value1, value2);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_yellow, yellowFragment, "Yellow Fragment")
                .addToBackStack("Yellow Fragment")
                .commit();
    }

    private void addBlueFragment(String value1, String value2) {
        BlueFragment blueFragment =
                BlueFragment.newInstance(value1, value2);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_blue, blueFragment, BLUE_FRAGMENT_TAG)
                .addToBackStack(BLUE_FRAGMENT_TAG)
                .commit();
    }

    public void removingFragments(View view)
    {
        BlueFragment blueFragment = (BlueFragment)getSupportFragmentManager()
                .findFragmentByTag(BLUE_FRAGMENT_TAG);
        switch (view.getId())
        {
            case R.id.btnRemoveBlue:
                if(blueFragment != null)
                {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove(blueFragment)
                            .commit();
                }
        }
    }

    @Override
    public void onFragmentInteraction(String s)
    {
        //update activity
        Log.d("MainActivity", "onFragmentInteraction: ");
        //let activity update other fragments
        addBlueFragment("Data from yellow: ", s);
    }
}
