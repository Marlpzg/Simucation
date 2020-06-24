package main.classes;

public class MefPart {

    private String descrption;
    private String img;

    public MefPart(String descrption, String img) {
        this.descrption = descrption;
        this.img = img;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
