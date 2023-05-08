package hu.unideb.inf.abdullah.chatapplication;

public class Model {
    private Profile profile;


    public Model() {
        this.profile = new Profile();
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
