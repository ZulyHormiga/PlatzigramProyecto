package com.platzi.platzigram.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.platzi.platzigram.R;
import com.platzi.platzigram.adapter.PictureAdapterRecyclerview;
import com.platzi.platzigram.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        showToolbar("", false, v);
        RecyclerView picturesRecycler = (RecyclerView) v.findViewById(R.id.pictureProfileRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerview pictureAdapterRecyclerview = new PictureAdapterRecyclerview(buidPictures(), R.layout.cardview_picture, getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerview);
        return v;
    }

    public void showToolbar(String title, boolean upButton, View v) {
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton); //hace visiblela flecha de volver
    }

    public ArrayList<Picture> buidPictures() {
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://www.bing.com/images/search?q=imagenes+de+familia&view=detailv2&qft=+filterui%3aimagesize-medium&id=295B4D996237DC7C03F3182790C7F6D0607BDBC0&selectedIndex=1&ccid=n5ln7wj3&simid=608001378503691220&thid=OIP.M9f9967ef08f7ce1a722254acfebcc580H0", "Santiago Hormiga", "10 dìas", "10 Me gusta"));
        pictures.add(new Picture("htttp://www.novalandtours.com/images/guide/guilin.jpg", "Uriel Ramiréz", "4 días", "3 Me gusta"));
        pictures.add(new Picture("htttp://www.novalandtours.com/images/guide/guilin.jpg", "Juan Pablo Gonzales", "3 días", "10 Me gusta"));
        pictures.add(new Picture("htttp://www.novalandtours.com/images/guide/guilin.jpg", "Nathaly Hormiga G", "4 días", "10 M egusta"));
        return pictures;
    }
}
