package main.classes;

import java.util.ArrayList;

public class MefStep {

    private String name;
    private ArrayList<MefPart> parts;

    public MefStep(String name, ArrayList<MefPart> parts) {
        this.name = name;
        this.parts = parts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MefPart> getParts() {
        return parts;
    }

    public void setParts(ArrayList<MefPart> parts) {
        this.parts = parts;
    }
}
