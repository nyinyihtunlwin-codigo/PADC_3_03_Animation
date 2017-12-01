package projects.nyinyihtunlwin.animation.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.animation.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.parent)
    CoordinatorLayout parentLayout;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Animation animMainStart, animMainReset;
    private AnimatorSet animSetXY;
    private int slideState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        animMainStart = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.main_screen_move);
        animMainReset = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.main_screen_reset);


        ObjectAnimator animX = ObjectAnimator.ofFloat(fab, "x", 40f);
        ObjectAnimator animY = ObjectAnimator.ofFloat(fab, "y", 260f);
        animSetXY = new AnimatorSet();
        animSetXY.playTogether(animX, animY);


        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                parentLayout.startAnimation(animMainReset);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animSetXY.start();
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_show_user_data) {
            parentLayout.startAnimation(animMainStart);
            drawerLayout.openDrawer(Gravity.END);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
