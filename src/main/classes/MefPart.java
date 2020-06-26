package main.classes;

public class MefPart {

    private String descrption;
    private String img;
    private String text2;

    public MefPart(String descrption, String img) {
        this.descrption = descrption;
        this.img = img;
        text2 = null;
    }

    public MefPart(String descrption, String img, String text2) {
        this.descrption = descrption;
        this.img = img;
        this.text2 = text2;
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

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
