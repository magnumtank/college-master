package com.example.dashmesh.sliderfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Btech_list extends AppCompatActivity {
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btech_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createGroupList();

        createCollection();

        expListView = (ExpandableListView) findViewById(R.id.expanding_list);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, laptopCollection);
        expListView.setAdapter(expListAdapter);

        setGroupIndicatorToRight();

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                // Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                //       .show();
                // getActivity().finish();
                // }
                if (groupPosition==0){

                    Intent i = new Intent(Btech_list.this, User.class);
                    startActivity(i);
                }
                return true;
            }
        });
    }

    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Search by City");
        groupList.add("Search by College");
        groupList.add("Search by Exam");
    }

    private void createCollection() {
        // preparing laptops collection(child)
        String[] citycollege = { "HP1", "HP2", "HP3" };
        String[] college = { "HCL1", "HCL2", "HCL3" };
        String[] exam = { "s1", "s2", "s3", "s4" };
        laptopCollection = new LinkedHashMap<String, List<String>>();

        for (String laptop : groupList) {
            if (laptop.equals("Search by City")) {
                loadChild(citycollege);
            } else if (laptop.equals("Search by College"))
                loadChild(college);
            else if (laptop.equals("Search by Exam"))
                loadChild(exam);

            laptopCollection.put(laptop, childList);
        }
    }

    private void loadChild(String[] laptopModels) {
        childList = new ArrayList<String>();
        for (String model : laptopModels)
            childList.add(model);
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }
}
