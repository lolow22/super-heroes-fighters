package fr.toulousewcs.hackathon1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.ArrayList;

/**
 * Created by wilder on 05/04/18.
 */

public class SelectPersoActivity extends AppCompatActivity {

    ImageView imgPlayer1;
    ImageView imgPlayer2;
    ImageView boutonSelect;
    ImageView boutonFight;
    int status = 0;
    ImageView boutonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_perso);

        imgPlayer1 = findViewById(R.id.iv_player1);
        imgPlayer2 = findViewById(R.id.iv_player2);
        boutonSelect = findViewById(R.id.button_selection);
        boutonFight = findViewById(R.id.button_fight);
        boutonReturn = findViewById(R.id.button_return_menu);

        Intent intentRecep = getIntent();
        final int val = intentRecep.getIntExtra("val", 0);


        final GridView gridview = (GridView) findViewById(R.id.grid_select_perso);
        ArrayList<HeroModel> results = new ArrayList<>();

        //TODO : faire les résultats

        results.add(new HeroModel(70, "Batman", 80, 55, 27, 50, 47, 50, R.drawable.img70_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img70_icon));
        results.add(new HeroModel(149, "Captain America", 69, 55, 38, 55, 60, 50,R.drawable.img149_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img149_icon));
        results.add(new HeroModel(176, "Chuck Norris", 50, 80, 47, 56, 42, 99 ,R.drawable.img176_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img176_icon));
        results.add(new HeroModel(208, "Dark Vador", 69, 48, 33, 35, 60, 55,R.drawable.img208_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img208_icon));
        results.add(new HeroModel(213, "Dead Pool",69, 52, 50, 100, 40, 100, R.drawable.img213_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img213_icon));
        results.add(new HeroModel(263, "Flash", 63, 50, 100, 50, 68, 32,R.drawable.img263_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img263_icon));
        results.add(new HeroModel(289, "Goku", 80, 100, 75, 90, 80, 90,R.drawable.img289_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img289_icon));
        results.add(new HeroModel(310, "Harry Potter", 75, 70, 21, 10, 45, 50, R.drawable.img310_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img310_icon));
        results.add(new HeroModel(332, "Hulk", 100, 100, 63, 100, 80, 80, R.drawable.img332_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img332_icon));
        results.add(new HeroModel(346, "Iron Man", 100, 85, 58, 85, 40, 45, R.drawable.img346_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img346_icon));
        results.add(new HeroModel(352, "James Bond", 88, 30, 17, 35, 25, 90, R.drawable.img352_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img352_icon));
        results.add(new HeroModel(370, "Joker", 55, 45, 12, 60, 43, 55, R.drawable.img370_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img370_icon));
        results.add(new HeroModel(373, "Judge Dredd", 75, 45, 38, 50, 52, 95, R.drawable.img373_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img373_icon));
        results.add(new HeroModel(404, "Leonardo", 75, 55, 50, 65, 45, 45, R.drawable.img404_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img404_icon));
        results.add(new HeroModel(502, "Saïtama", 38, 100000, 83, 95, 1000, 1000, R.drawable.img502_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img502_icon));
        results.add(new HeroModel(540, "Rambo", 63, 45, 25, 30, 30, 100, R.drawable.img540_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img540_icon));
        results.add(new HeroModel(574, "Sauron", 88, 85, 33, 100, 33, 70, R.drawable.img574_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img574_icon));
        results.add(new HeroModel(644, "SuperMan", 94, 100, 100, 100, 80, 100, R.drawable.img644_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img644_icon));
        results.add(new HeroModel(650, "T800", 75, 45, 17, 60, 73, 65 ,R.drawable.img650_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img650_icon));
        results.add(new HeroModel(720, "Wonder Woman", 88, 100, 79, 100, 67, 52, R.drawable.img720_1,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, R.drawable.img720_icon));
       

        SelectPersoAdapter adapter = new SelectPersoAdapter(this, results);
        gridview.setAdapter(adapter);
        final Intent intent = new Intent(SelectPersoActivity.this, FightActivity.class);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                MediaPlayer media2 = MediaPlayer.create(getBaseContext(), R.raw.persoselect);
                media2.start();

                if (status == 0) {
                    HeroModel item = (HeroModel) gridview.getItemAtPosition(position);
                    imgPlayer1.setImageResource(item.getImage1());
                    status++;
                    Parcelable hero1 = new HeroModel(item.getId(),item.getName(), item.getIntelligence(), item.getStrength(), item.getSpeed(), item.getDurability(), item.getPower(), item.getCombat(), item.getImage1(), item.getImage2(), item.getImage3(), item.getIcon());
                    intent.putExtra("intenthero1", hero1);
                    intent.putExtra("valfinal", val);


                } else if (status == 1) {
                    HeroModel item = (HeroModel) gridview.getItemAtPosition(position);
                    imgPlayer2.setImageResource(item.getImage1());
                    status++;
                    Parcelable hero2 = new HeroModel(item.getId(),item.getName(), item.getIntelligence(), item.getStrength(), item.getSpeed(), item.getDurability(), item.getPower(), item.getCombat(), item.getImage1(), item.getImage2(), item.getImage3(), item.getIcon());
                    gridview.setVisibility(View.GONE);
                    boutonFight.setVisibility(View.VISIBLE);
                    intent.putExtra("intenthero2", hero2);
                    intent.putExtra("valfinal", val);
                }
            }

        });

        boutonFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer media3 = MediaPlayer.create(getBaseContext(), R.raw.menuselect);
                media3.start();
                startActivity(intent);
            }
        });

        boutonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer media3 = MediaPlayer.create(getBaseContext(), R.raw.persoselect);
                media3.start();
                status = 0;
                gridview.setVisibility(View.VISIBLE);
                boutonFight.setVisibility(View.GONE);
            }
        });

        boutonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnMenu = new Intent(SelectPersoActivity.this, SelectModeFightActivity.class);
                SelectPersoActivity.this.startActivity(returnMenu);
            }
        });





    }









}
