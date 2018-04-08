package fr.toulousewcs.hackathon1;

/**
 * Created by wilder on 05/04/18.
 */

public class SelectPersoModel {

    private int id;
    private int imagePerso;


    public SelectPersoModel(int id, int imagePerso) {
        this.id = id;
        this.imagePerso = imagePerso;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getImagePerso() {
        return imagePerso;
    }

    public void setImagePerso(int imagePerso) {
        this.imagePerso = imagePerso;
    }
}
