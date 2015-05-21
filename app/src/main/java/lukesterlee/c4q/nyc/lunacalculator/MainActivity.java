package lukesterlee.c4q.nyc.lunacalculator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks {



    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        boolean isInLandscapeMode = getResources().getBoolean(R.bool.isInLandscape);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        if (isInLandscapeMode) {
            getSupportActionBar().hide();
        }

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fragment = new Fragment();
        Bundle args;

        switch (position) {
            case 0 :
                fragment = new CalculatorFragment();
                args = new Bundle();
                args.putInt("position", 0);
                fragment.setArguments(args);
                break;
            case 1 :
                fragment = new ConvertFragment();
                args = new Bundle();
                args.putInt("position", 1);
                fragment.setArguments(args);
                break;
            case 2 :

                break;
            case 3 :

                break;
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void restoreActionBar() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    public void onSectionAttached(int number) {


        switch (number) {
            case 0:
                mTitle = getString(R.string.menu0);
                break;
            case 1:
                mTitle = getString(R.string.menu1);
                break;

        }
    }


}
