package fr.toulousewcs.hackathon1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wilder on 05/04/18.
 */

public class HeroModel implements Parcelable{
    private int id;
    private String name;
    private int intelligence;
    private int strength;
    private int speed;
    private int durability;
    private int power;
    private int combat;
    private int image1;
    private int image2;
    private int image3;
    private int icon;

    public HeroModel(int id, String name, int intelligence, int strength, int speed, int durability, int power, int combat, int image1, int image2, int image3, int icon) {
        this.id = id;
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.speed = speed;
        this.durability = durability;
        this.power = power;
        this.combat = combat;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.icon = icon;
    }

    protected HeroModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        intelligence = in.readInt();
        strength = in.readInt();
        speed = in.readInt();
        durability = in.readInt();
        power = in.readInt();
        combat = in.readInt();
        image1 = in.readInt();
        image2 = in.readInt();
        image3 = in.readInt();
        icon = in.readInt();
    }

    public static final Creator<HeroModel> CREATOR = new Creator<HeroModel>() {
        @Override
        public HeroModel createFromParcel(Parcel in) {
            return new HeroModel(in);
        }

        @Override
        public HeroModel[] newArray(int size) {
            return new HeroModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCombat() {
        return combat;
    }

    public void setCombat(int combat) {
        this.combat = combat;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }

    public int getImage3() {
        return image3;
    }

    public void setImage3(int image3) {
        this.image3 = image3;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean IsKO () {
        if (durability <= 0){
            return true; }
        return false;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(intelligence);
        parcel.writeInt(strength);
        parcel.writeInt(speed);
        parcel.writeInt(durability);
        parcel.writeInt(power);
        parcel.writeInt(combat);
        parcel.writeInt(image1);
        parcel.writeInt(image2);
        parcel.writeInt(image3);
        parcel.writeInt(icon);
    }
}
