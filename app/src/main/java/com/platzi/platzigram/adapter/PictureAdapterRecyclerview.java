package com.platzi.platzigram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.platzi.platzigram.R;
import com.platzi.platzigram.model.Picture;
import com.platzi.platzigram.view.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nathaly on 14/12/2016.
 */

public class PictureAdapterRecyclerview extends RecyclerView.Adapter<PictureAdapterRecyclerview.PictureViewHolder> {

    private ArrayList<Picture> pictures;// arreglo de datos
    private int resource; //cardview que hicimos en el layout por separado
    private Activity activity; // actividad desde donde se llama la clase

    public PictureAdapterRecyclerview(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inicializa la clase PictureViewHolder, necesito pasarle el layout, vaya y encuentre el card
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);//convirtiendo un xml a un view

        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        //Se trabaja con toda la lista de elementos (paso de datos al picture)
        Picture picture = pictures.get(position); //recorre la lista y llena tarjetas con los datos existentes
        holder.userNameCard.setText(picture.getUserName());
        holder.timeCard.setText(picture.getTime());
        holder.likeNumberCard.setText(picture.getLikeNumber());
        Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCard);
        // Log.d("IMAGE", "dfgd" + picture.getPicture());// prueba picasso
        //clickListener
        holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PictureDetailActivity.class);
                //Hacemos la validadaciòn de version
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();//creamos objeto
                    explode.setDuration(1000); // duraciòn del efecto explode
                    activity.getWindow().setExitTransition(explode); //asignamos la transiciòn a la actividad
                    activity.startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v, activity.getString(R.string.transitionname_picture)).toBundle());
                } else {
                    activity.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        //Devuelve un conteo de los elementos
        return pictures.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder {
        //Definimos views que componen al cardview
        private ImageView pictureCard;
        private TextView userNameCard;
        private TextView timeCard;
        private TextView likeNumberCard;

        public PictureViewHolder(View itemView) {
            super(itemView);
            pictureCard = (ImageView) itemView.findViewById(R.id.img_pictureCard);
            userNameCard = (TextView) itemView.findViewById(R.id.userNameCard);
            timeCard = (TextView) itemView.findViewById(R.id.timeCard);
            likeNumberCard = (TextView) itemView.findViewById(R.id.likeNumberCard);
        }
    }
}
