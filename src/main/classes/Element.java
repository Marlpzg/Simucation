package main.classes;

public class Element {

    private String name;
    private String n1;
    private String n2;
    private String n3;
    private String n4;

    public Element(String name, String n1, String n2, String n3, String n4) {
        this.name = name;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getN1() {
        return n1;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public String getN2() {
        return n2;
    }

    public void setN2(String n2) {
        this.n2 = n2;
    }

    public String getN3() {
        return n3;
    }

    public void setN3(String n3) {
        this.n3 = n3;
    }

    public String getN4() {
        return n4;
    }

    public void setN4(String n4) {
        this.n4 = n4;
    }
}
