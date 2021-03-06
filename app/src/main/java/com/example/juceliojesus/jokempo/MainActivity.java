package com.example.juceliojesus.jokempo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    ImageView jogador1, jogador2;
    ImageButton botaoPedra,botaoPapel,botaoTesoura;
    Animation some,aparece;
    int jogada1 = 0;
    int jogada2 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jogador1 = findViewById(R.id.jogador1);
        jogador2 = findViewById(R.id.jogador2);
        botaoPapel = findViewById(R.id.botaoPapel);
        botaoPedra = findViewById(R.id.botaoPedra);
        botaoTesoura = findViewById(R.id.botaoTesoura);

        some = new AlphaAnimation(1,0);
        aparece = new AlphaAnimation(0,1);
        some.setDuration(1500);
        aparece.setDuration(100);

        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                jogador2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jogador2.setVisibility(View.INVISIBLE);
                jogador2.startAnimation(aparece);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                sorteiaJogadaInimigo();
                jogador2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                VerificaJogada();
                jogador2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }



    private void sorteiaJogadaInimigo() {

        Random r = new Random();
        int numRandom = r.nextInt(3);
        switch (numRandom){
            case 0:jogador2.setImageResource(R.drawable.pedra);
            jogada2 = 1;
            break;
            case 1:jogador2.setImageResource(R.drawable.papel);
            jogada2 = 2;
            break;
            case 2:jogador2.setImageResource(R.drawable.tesoura);
            jogada2 = 3;
            break;
        }
    }

    private void VerificaJogada() {

        if(jogada1==jogada2){
            Toast.makeText(this,"Empate!",Toast.LENGTH_SHORT).show();
        }
        if((jogada1==1&&jogada2==3)||(jogada1==2&&jogada2==1)||(jogada1==3&&jogada2==2)){
            Toast.makeText(this,"Ganhei!",Toast.LENGTH_SHORT).show();
        }
        if((jogada2==1&&jogada1==3)||(jogada2==2&&jogada1==1)||(jogada2==1&&jogada1==2)){
            Toast.makeText(this,"Perdi!",Toast.LENGTH_SHORT).show();
        }


    }

    public void tocouBotao(View view){
        jogador1.setScaleX(-1);
        switch (view.getId()){
            case(R.id.botaoPedra) : jogador1.setImageResource(R.drawable.pedra);
                jogada1 = 1;
            break;
            case(R.id.botaoPapel) : jogador1.setImageResource(R.drawable.papel);
                jogada1 = 2;
            break;
            case(R.id.botaoTesoura) : jogador1.setImageResource(R.drawable.tesoura);
                jogada1 = 3;
            break;
        }
        jogador2.setImageResource(R.drawable.interrogacao);
        jogador2.startAnimation(some);
    }
}
