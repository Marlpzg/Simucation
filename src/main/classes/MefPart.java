package main.classes;

public class MefPart {
    private String descrption;
    private String image;

    public MefPart(String descrption, String image) {
        this.descrption = descrption;
        this.image = image;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
