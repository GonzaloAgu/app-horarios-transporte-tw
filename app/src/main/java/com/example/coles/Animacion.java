package com.example.coles;

import android.view.View;
public class Animacion {
    public static void clickBoton(View view){
        view.setAlpha(0.2f);
        view.animate()
                .alpha(1f)
                .setDuration(200);
    }
}
