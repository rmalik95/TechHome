package com.techHome.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techHome.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerContactUsFragment extends android.support.v4.app.Fragment {

    private View parentView;



    public NavigationDrawerContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView = inflater.inflate(R.layout.contact_recycler_custom_row, container, false);

      //  populate();
        return parentView;
    }

//    private void populate() {
//        adapter = new ContactRecyclerAdapter(getActivity(), getData());
//        contactRecyclerView.setAdapter(adapter);
//        contactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//    }

//    public static List<ContactRecyclerInformation> getData() {
//        List<ContactRecyclerInformation> data = new ArrayList<>();
//        String[] Name = {};
//        String[] Address = {};
//
//
//        for (int i = 0; i < Name.length && i < Address.length; i++) {
//            ContactRecyclerInformation current = new ContactRecyclerInformation();
//            current.Name = Name[i];
//            current.Address = Address[i];
//            data.add(current);
//        }
//        return data;
//    }
}
