package com.lm2a.backstackexperiments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements FragmentManager.OnBackStackChangedListener, FragmentA.OnFragmentInteractionListener, FragmentB.OnFragmentInteractionListener{

    FragmentManager mFragmentManager;
    TextView mBackStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        mBackStack = (TextView)findViewById(R.id.backstack);
        mFragmentManager = getFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);

    }

    public void addA(View v){
        FragmentA fa = new FragmentA();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragments, fa, "A");
        fragmentTransaction.addToBackStack("addA");
        fragmentTransaction.commit();
    }
    public void addB(View v){
        FragmentB fb = new FragmentB();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragments, fb, "B");
        fragmentTransaction.addToBackStack("addB");
        fragmentTransaction.commit();
    }
    public void removeA(View v) {
        FragmentA fa = (FragmentA) mFragmentManager.findFragmentByTag("A");
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (fa != null) {
            fragmentTransaction.remove(fa);
            fragmentTransaction.addToBackStack("removeA");
            fragmentTransaction.commit();
       } else {
            Toast.makeText(this, "Fragment A not added", Toast.LENGTH_LONG).show();
        }
    }
    public void removeB(View v) {
        FragmentB fb = (FragmentB) mFragmentManager.findFragmentByTag("B");
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (fb != null) {
            fragmentTransaction.remove(fb);
            fragmentTransaction.addToBackStack("removeB");
            fragmentTransaction.commit();
        } else {
            Toast.makeText(this, "Fragment B not added", Toast.LENGTH_LONG).show();
        }
    }
    public void replaceA(View v){
        FragmentA fa = new FragmentA();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragments, fa, "A");
        fragmentTransaction.addToBackStack("replaceWithA");
        fragmentTransaction.commit();
    }
    public void replaceB(View v){
        FragmentB fb = new FragmentB();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragments, fb, "B");
        fragmentTransaction.addToBackStack("replaceWithB");
        fragmentTransaction.commit();
    }
    public void attachB(View v){
        FragmentB fb = (FragmentB) mFragmentManager.findFragmentByTag("B");
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if(fb != null){
            fragmentTransaction.attach(fb);
            fragmentTransaction.addToBackStack("attachB");
            fragmentTransaction.commit();
        }else{
            Toast.makeText(this, "Can't attach Fragment B because does not exist", Toast.LENGTH_LONG).show();
        }

    }
    public void detachB(View v){
        FragmentB fb = (FragmentB) mFragmentManager.findFragmentByTag("B");
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if(fb != null){
            fragmentTransaction.detach(fb);
            fragmentTransaction.addToBackStack("detachB");
            fragmentTransaction.commit();
        }else{
            Toast.makeText(this, "Can' detach Fragment B because does not exist", Toast.LENGTH_LONG).show();
        }
    }
    public void pop_add_B(View v){
        mFragmentManager.popBackStack("addB", 0);
    }

    public void back(View v){
        mFragmentManager.popBackStack();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    @Override
    public void onBackStackChanged() {
        mBackStack.setText(mBackStack.getText()+"\n");
        mBackStack.setText("BackStack Status:\n");
        mBackStack.setText("-----------------\n");
        int count = mFragmentManager.getBackStackEntryCount();
        for(int i=count-1; i>= 0 ; i--){
            FragmentManager.BackStackEntry entry = mFragmentManager.getBackStackEntryAt(i);
            mBackStack.setText(mBackStack.getText()+" "+entry.getName()+" \n");
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.i("MainActivity", uri.toString());
    }
}
