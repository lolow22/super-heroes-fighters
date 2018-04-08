package fr.toulousewcs.hackathon1;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by wilder on 05/04/18.
 */

public class FightActivity  extends AppCompatActivity {

    ImageView boutonAtt;
    ImageView boutonAttSpé;
    ImageView boutonAtt2;
    ImageView boutonAttSpé2;
    ImageView imageperso1;
    ImageView imageperso2;
    ImageView icon1;
    ImageView icon2;
    TextView name1;
    TextView name2;
    ProgressBar life1;
    ProgressBar life2;
    int damage = 0;
    TextView résultat;
    ImageView boutonRevive;
    ImageView boutonSelection;
    TextView textFinGame;
    ImageView impact1;
    ImageView impact2;
    ImageView effectBlanc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight);
        boutonAtt = findViewById(R.id.button_Att);
        boutonAttSpé = findViewById(R.id.button_attSpe);
        imageperso1 = findViewById(R.id.imageView_perso1);
        imageperso2 = findViewById(R.id.imageView_perso2);
        icon1 = findViewById(R.id.imageView_icon1);
        icon2 = findViewById(R.id.imageView_icon2);
        name1 = findViewById(R.id.textView_name1);
        name2 = findViewById(R.id.textView_name2);
        life1 = findViewById(R.id.progressBar_1);
        life2 = findViewById(R.id.progressBar2);
        résultat = findViewById(R.id.textView_result);
        boutonRevive = findViewById(R.id.butto_rematch);
        boutonSelection = findViewById(R.id.button_selection);
        textFinGame = findViewById(R.id.textView_fingame);
        impact1 = findViewById(R.id.imageView_impact1);
        impact2 = findViewById(R.id.imageView_impact2);
        boutonAtt2 = findViewById(R.id.imageView_att2);
        boutonAttSpé2 = findViewById(R.id.imageView_spé2);
        effectBlanc = findViewById(R.id.imageView_effectBlanc);

        // préparation combat :

        Intent intent = getIntent();
        final int type = intent.getIntExtra("valfinal", 0);
        final HeroModel hero1 = getIntent().getExtras().getParcelable("intenthero1");
        final int realLife1 = hero1.getDurability();
        final HeroModel hero2 = getIntent().getExtras().getParcelable("intenthero2");
        final int realLife2 = hero2.getDurability();
        imageperso1.setImageResource(hero1.getImage1());
        imageperso2.setImageResource(hero2.getImage1());
        icon1.setImageResource(hero1.getIcon());
        icon2.setImageResource(hero2.getIcon());
        name1.setText(hero1.getName());
        name2.setText(hero2.getName());
        life1.setMax(hero1.getDurability());
        life1.setProgress(hero1.getDurability());
        life1.setSecondaryProgress(hero1.getDurability());
        life2.setMax(hero2.getDurability());
        life2.setProgress(hero2.getDurability());
        life2.setSecondaryProgress(hero2.getDurability());
        final MediaPlayer media1 = MediaPlayer.create(getBaseContext(), R.raw.musiquecombat);
        media1.start();
        media1.setLooping(true);
        rota(imageperso1);
        rota2(imageperso2);






        // Préparation Arene
        final int[] photos={R.drawable.gifview1firehouse, R.drawable.rock,R.drawable.gifview3cascadegif, R.drawable.dark_temple};
        final GifImageView gifImageView = findViewById(R.id.gif_iv);
        final Random ran =new Random();
        final int i = ran.nextInt(photos.length);
        gifImageView.setImageResource(photos[i]);
        gifImageView.setOnClickListener(new View.OnClickListener()
                                 {
                                     public void onClick(View v)
                                     {
                                         int k = ran.nextInt(photos.length);
                                         gifImageView.setImageResource(photos[k]);
                                     }
                                 }
        );
        //No clickable gif :
        gifImageView.setEnabled(false);


        // combat :

        if (hero1.getId() == 176 || hero2.getId()== 176){
            if((hero1.getId() == 176 || hero1.getId() == 502) &&(hero2.getId() == 176 || hero2.getId() == 502)){
                boutonAtt.setEnabled(false);
                boutonAttSpé.setEnabled(false);
                textFinGame.setText("égalité");
                endgame2();
            }
            else if (hero1.getId() == 176){
                boutonAtt.setEnabled(false);
                boutonAttSpé.setEnabled(false);
                textFinGame.setText(hero1.getName() + " Vainqueur !");
                endgame2();
            }
            else if (hero2.getId() == 176){
                textFinGame.setText(hero2.getName() + " Vainqueur !");
                endgame2();
            }

            boutonSelection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(FightActivity.this, SelectPersoActivity.class);
                    intent.putExtra("val", type);
                    startActivity(intent);
                    media1.stop();
                }
            });

            boutonRevive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hero1.setDurability(realLife1);
                    life1.setProgress(realLife1);
                    hero2.setDurability(realLife2);
                    life2.setProgress(realLife2);
                    rematch();
                    rota(imageperso1);
                    rota2(imageperso2);
                }
            });

        }

        else {
            if (type == 0) {
                // P1 VS CPU

                //attaque physique
                boutonAtt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        damage = hero1.getStrength() - hero2.getCombat();
                        if (damage < 0) {
                            damage = 0;
                        }
                        hero2.setDurability(hero2.getDurability() - damage);
                        life2.setProgress(hero2.getDurability());
                        impact(impact2, imageperso2);
                        boutonAtt.setEnabled(false);
                        boutonAttSpé.setEnabled(false);
                        moveViewToScreenCenter(imageperso1);


                        // KO
                        if (hero2.IsKO()) {
                            endgame();
                            textFinGame.setText(hero1.getName() + " Vainqueur !");
                        }

                        CountDownTimer contreAttaque = new CountDownTimer(1000, 1000) {
                            @Override
                            public void onTick(long l) {
                            }

                            @Override
                            public void onFinish() {

                                // contre attaque
                                life2.setSecondaryProgress(hero2.getDurability());
                                boutonAtt.setEnabled(true);
                                boutonAttSpé.setEnabled(true);
                                Random random = new Random();
                                int r = random.nextInt(2);
                                if (r == 0) {
                                    damage = hero2.getStrength() - hero1.getCombat();
                                    if (damage < 0) {
                                        damage = 0;
                                    }
                                    hero1.setDurability(hero1.getDurability() - damage);
                                    life1.setProgress(hero1.getDurability());
                                    impact(impact1, imageperso1);
                                    moveViewToScreenCenter2(imageperso2);

                                    // KO
                                    if (hero1.IsKO()) {
                                        endgame();
                                        textFinGame.setText(hero2.getName() + " Vainqueur !");
                                    }
                                } else {

                                    damage = hero2.getIntelligence() - hero1.getPower();
                                    if (damage < 0) {
                                        damage = 0;
                                    }
                                    hero1.setDurability(hero1.getDurability() - damage);
                                    life1.setProgress(hero1.getDurability());
                                    impact(impact1, imageperso1);
                                    animeffect(effectBlanc);

                                    // KO
                                    if (hero1.IsKO()) {
                                        endgame();
                                        textFinGame.setText(hero2.getName() + " Vainqueur !");
                                    }

                                }
                            }
                        };
                        contreAttaque.start();
                    }
                });

                // attaque spécial
                boutonAttSpé.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        damage = hero1.getIntelligence() - hero2.getPower();
                        if (damage < 0) {
                            damage = 0;
                        }
                        hero2.setDurability(hero2.getDurability() - damage);
                        life2.setProgress(hero2.getDurability());
                        impact(impact2, imageperso2);
                        animeffect(effectBlanc);
                        boutonAtt.setEnabled(false);
                        boutonAttSpé.setEnabled(false);

                        // KO
                        if (hero2.IsKO()) {
                            endgame();
                            textFinGame.setText(hero1.getName() + " Vainqueur !");
                        }

                        //contre attaque spécial

                        CountDownTimer contreAttaque = new CountDownTimer(1000, 1000) {
                            @Override
                            public void onTick(long l) {
                            }

                            @Override
                            public void onFinish() {

                                // contre attaque
                                life2.setSecondaryProgress(hero2.getDurability());
                                boutonAtt.setEnabled(true);
                                boutonAttSpé.setEnabled(true);
                                Random random = new Random();
                                int r = random.nextInt(2);
                                if (r == 0) {
                                    damage = hero2.getStrength() - hero1.getCombat();
                                    if (damage < 0) {
                                        damage = 0;
                                    }
                                    hero1.setDurability(hero1.getDurability() - damage);
                                    life1.setProgress(hero1.getDurability());
                                    impact(impact1, imageperso1);
                                    moveViewToScreenCenter2(imageperso2);

                                    // KO
                                    if (hero1.IsKO()) {
                                        endgame();
                                        textFinGame.setText(hero2.getName() + " Vainqueur !");
                                    }
                                } else {

                                    damage = hero2.getIntelligence() - hero1.getPower();
                                    if (damage < 0) {
                                        damage = 0;
                                    }
                                    hero1.setDurability(hero1.getDurability() - damage);
                                    life1.setProgress(hero1.getDurability());
                                    animeffect(effectBlanc);
                                    impact(impact1, imageperso1);

                                    // KO
                                    if (hero1.IsKO()) {
                                        endgame();
                                        textFinGame.setText(hero2.getName() + " Vainqueur !");
                                    }

                                }
                            }
                        };
                        contreAttaque.start();

                    }
                });

            }


            // P1 VS P2
            else {
                boutonAtt2.setVisibility(View.VISIBLE);
                boutonAttSpé2.setVisibility(View.VISIBLE);
                boutonAtt2.setEnabled(false);
                boutonAttSpé2.setEnabled(false);

                boutonAtt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        damage = hero1.getStrength() - hero2.getCombat();
                        if (damage < 0) {
                            damage = 0;
                        }
                        hero2.setDurability(hero2.getDurability() - damage);
                        life2.setProgress(hero2.getDurability());
                        impact(impact2, imageperso2);
                        moveViewToScreenCenter(imageperso1);
                        boutonAtt.setEnabled(false);
                        boutonAttSpé.setEnabled(false);

                        // KO
                        if (hero2.IsKO()) {
                            endgame();
                            textFinGame.setText(hero1.getName() + " Vainqueur !");

                        }
                        CountDownTimer contreAttaque = new CountDownTimer(1000, 1000) {
                            @Override
                            public void onTick(long l) {
                            }

                            @Override
                            public void onFinish() {
                                boutonAtt2.setEnabled(true);
                                boutonAttSpé2.setEnabled(true);
                                life2.setSecondaryProgress(hero2.getDurability());

                                boutonAtt2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        damage = hero2.getStrength() - hero1.getCombat();
                                        if (damage < 0) {
                                            damage = 0;
                                        }
                                        hero1.setDurability(hero1.getDurability() - damage);
                                        life1.setProgress(hero1.getDurability());
                                        impact(impact1, imageperso1);
                                        moveViewToScreenCenter2(imageperso2);
                                        boutonAtt2.setEnabled(false);
                                        boutonAttSpé2.setEnabled(false);

                                        // KO
                                        if (hero1.IsKO()) {
                                            endgame();
                                            textFinGame.setText(hero2.getName() + " Vainqueur !");
                                        }
                                        CountDownTimer attaqueP1 = new CountDownTimer(1000, 1000) {
                                            @Override
                                            public void onTick(long l) {
                                            }

                                            @Override
                                            public void onFinish() {
                                                boutonAtt.setEnabled(true);
                                                boutonAttSpé.setEnabled(true);
                                                life1.setSecondaryProgress(hero1.getDurability());
                                            }
                                        };
                                        attaqueP1.start();
                                    }
                                });
                                boutonAttSpé2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        damage = hero2.getIntelligence() - hero1.getPower();
                                        if (damage < 0) {
                                            damage = 0;
                                        }
                                        hero1.setDurability(hero1.getDurability() - damage);
                                        life1.setProgress(hero1.getDurability());
                                        impact(impact1, imageperso1);
                                        animeffect(effectBlanc);
                                        boutonAtt2.setEnabled(false);
                                        boutonAttSpé2.setEnabled(false);

                                        // KO
                                        if (hero1.IsKO()) {
                                            endgame();
                                            textFinGame.setText(hero2.getName() + " Vainqueur !");
                                        }
                                        CountDownTimer attaqueP1 = new CountDownTimer(1000, 1000) {
                                            @Override
                                            public void onTick(long l) {
                                            }

                                            @Override
                                            public void onFinish() {
                                                boutonAtt.setEnabled(true);
                                                boutonAttSpé.setEnabled(true);
                                                life1.setSecondaryProgress(hero1.getDurability());
                                            }
                                        };
                                        attaqueP1.start();

                                    }
                                });

                            }
                        };
                        contreAttaque.start();
                    }
                });

                boutonAttSpé.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        damage = hero1.getIntelligence() - hero2.getPower();
                        if (damage < 0) {
                            damage = 0;
                        }
                        hero2.setDurability(hero2.getDurability() - damage);
                        life2.setProgress(hero2.getDurability());
                        impact(impact2, imageperso2);
                        animeffect(effectBlanc);
                        boutonAtt.setEnabled(false);
                        boutonAttSpé.setEnabled(false);

                        // KO
                        if (hero2.IsKO()) {
                            endgame();
                            textFinGame.setText(hero1.getName() + " Vainqueur !");
                        }
                        CountDownTimer contreAttaque = new CountDownTimer(1000, 1000) {
                            @Override
                            public void onTick(long l) {
                            }

                            @Override
                            public void onFinish() {
                                boutonAtt2.setEnabled(true);
                                boutonAttSpé2.setEnabled(true);
                                life2.setSecondaryProgress(hero2.getDurability());

                                boutonAtt2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        damage = hero2.getStrength() - hero1.getCombat();
                                        if (damage < 0) {
                                            damage = 0;
                                        }
                                        hero1.setDurability(hero1.getDurability() - damage);
                                        life1.setProgress(hero1.getDurability());
                                        impact(impact1, imageperso1);
                                        moveViewToScreenCenter2(imageperso2);
                                        boutonAtt2.setEnabled(false);
                                        boutonAttSpé2.setEnabled(false);

                                        // KO
                                        if (hero1.IsKO()) {
                                            endgame();
                                            textFinGame.setText(hero2.getName() + " Vainqueur !");
                                        }
                                        CountDownTimer attaqueP1 = new CountDownTimer(1000, 1000) {
                                            @Override
                                            public void onTick(long l) {
                                            }

                                            @Override
                                            public void onFinish() {
                                                boutonAtt.setEnabled(true);
                                                boutonAttSpé.setEnabled(true);
                                                life1.setSecondaryProgress(hero1.getDurability());
                                            }
                                        };
                                        attaqueP1.start();
                                    }
                                });
                                boutonAttSpé2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        damage = hero2.getIntelligence() - hero1.getPower();
                                        if (damage < 0) {
                                            damage = 0;
                                        }
                                        hero1.setDurability(hero1.getDurability() - damage);
                                        life1.setProgress(hero1.getDurability());
                                        impact(impact1, imageperso1);
                                        animeffect(effectBlanc);
                                        boutonAtt2.setEnabled(false);
                                        boutonAttSpé2.setEnabled(false);

                                        // KO
                                        if (hero1.IsKO()) {
                                            endgame();
                                            textFinGame.setText(hero2.getName() + " Vainqueur !");
                                        }
                                        CountDownTimer attaqueP1 = new CountDownTimer(1000, 1000) {
                                            @Override
                                            public void onTick(long l) {
                                            }

                                            @Override
                                            public void onFinish() {
                                                boutonAtt.setEnabled(true);
                                                boutonAttSpé.setEnabled(true);
                                                life1.setSecondaryProgress(hero1.getDurability());
                                            }
                                        };
                                        attaqueP1.start();

                                    }
                                });

                            }
                        };
                        contreAttaque.start();

                    }
                });


            }


            // Fin de Match :

            boutonSelection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(FightActivity.this, SelectPersoActivity.class);
                    intent.putExtra("val", type);
                    startActivity(intent);
                    media1.stop();
                }
            });

            boutonRevive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hero1.setDurability(realLife1);
                    life1.setProgress(realLife1);
                    hero2.setDurability(realLife2);
                    life2.setProgress(realLife2);
                    rematch();
                }
            });
        }

    }


    // Methode :

    public void endgame() {
        boutonAtt.setEnabled(false);
        boutonAttSpé.setEnabled(false);
        boutonRevive.setVisibility(View.VISIBLE);
        boutonSelection.setVisibility(View.VISIBLE);
        textFinGame.setVisibility(View.VISIBLE);
        MediaPlayer mediadead = MediaPlayer.create(getBaseContext(), R.raw.coupphysique);
        mediadead.start();

    }
    public void endgame2() {
        boutonAtt.setEnabled(false);
        boutonAttSpé.setEnabled(false);
        boutonSelection.setVisibility(View.VISIBLE);
        textFinGame.setVisibility(View.VISIBLE);

    }

    public void rematch() {
        boutonAtt.setEnabled(true);
        boutonAttSpé.setEnabled(true);
        boutonRevive.setVisibility(View.GONE);
        boutonSelection.setVisibility(View.GONE);
        textFinGame.setVisibility(View.GONE);
    }


    public void impact(final ImageView impact, ImageView hero){
        impact.setVisibility(View.VISIBLE);
        Random r = new Random();
        int i2 = r.nextInt( 100) - 50;
        Random t = new Random();
        int i1 = t.nextInt(100) - 50;
        impact.setTranslationX(i2);
        impact.setTranslationY(i1);
        CountDownTimer count = new CountDownTimer(700, 100) {
            @Override
            public void onTick(long l) {
            }
            @Override
            public void onFinish() {
                impact.setVisibility(View.INVISIBLE);
            }
        };
        count.start();
        animsimple(hero);
    }

    public void animsimple(ImageView imagehero){
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(5);
        imagehero.startAnimation(anim);

    }

    private void moveViewToScreenCenter( View view )
    {

        MediaPlayer mediapunch = MediaPlayer.create(getBaseContext(), R.raw.coupphysique);
        mediapunch.start();
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics( dm );
        int originalPos[] = new int[2];
        view.getLocationOnScreen( originalPos );
        int xDest = dm.widthPixels/2;
        xDest -= (view.getMeasuredWidth()/2);

        TranslateAnimation anim = new TranslateAnimation( 0, xDest, 0, 0 );
        anim.setDuration(1000);
        anim.setFillAfter( true );
        view.startAnimation(anim);
        TranslateAnimation anim2 = new TranslateAnimation( xDest, 0, 0, 0 );
        anim2.setDuration(1000);
        anim2.setFillAfter( true );
        view.startAnimation(anim2);
        CountDownTimer countanim = new CountDownTimer(1000, 100) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                rota(imageperso1);
                rota2(imageperso2);
            }
        };
        countanim.start();
    }

    private void moveViewToScreenCenter2( View view )
    {

        MediaPlayer mediapunch = MediaPlayer.create(getBaseContext(), R.raw.coupphysique);
        mediapunch.start();
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics( dm );
        int originalPos[] = new int[2];
        view.getLocationOnScreen( originalPos );
        int xDest = -dm.widthPixels/2;
        xDest -= (view.getMeasuredWidth()/2);

        TranslateAnimation anim = new TranslateAnimation( 0, xDest, 0, 0 );
        anim.setDuration(1000);
        anim.setFillAfter( true );
        view.startAnimation(anim);
        TranslateAnimation anim2 = new TranslateAnimation( xDest, 0, 0, 0 );
        anim2.setDuration(1000);
        anim2.setFillAfter( true );
        view.startAnimation(anim2);
        CountDownTimer countanim = new CountDownTimer(1000, 100) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                rota(imageperso1);
                rota2(imageperso2);
            }
        };
        countanim.start();
    }
    public void animeffect(final ImageView imageeffect){
        MediaPlayer mediapunch = MediaPlayer.create(getBaseContext(), R.raw.coupphysique);
        mediapunch.start();
        imageeffect.setVisibility(View.VISIBLE);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(5);
        imageeffect.startAnimation(anim);
        CountDownTimer count = new CountDownTimer(300, 100) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                imageeffect.setVisibility(View.INVISIBLE);
            }
        };
        count.start();
        CountDownTimer countanim = new CountDownTimer(1000, 100) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                rota(imageperso1);
                rota2(imageperso2);
            }
        };
        countanim.start();

    }


    private void rota ( View view )
    {

        RotateAnimation anim = new RotateAnimation( 2, -2, 0, 0 );
        int originalPos[] = new int[2];
        view.getLocationOnScreen( originalPos );
        anim.setDuration(500);
        anim.setFillAfter( true );
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(100);
        view.startAnimation(anim);

    }

    private void rota2 ( View view )
    {

        RotateAnimation anim = new RotateAnimation( -2, 2, 0, 0 );
        int originalPos[] = new int[2];
        view.getLocationOnScreen( originalPos );
        anim.setDuration(500);
        anim.setFillAfter( true );
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(100);
        view.startAnimation(anim);

    }





}
