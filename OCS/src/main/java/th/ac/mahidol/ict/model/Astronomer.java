package th.ac.mahidol.ict.model;

public class Astronomer extends User{
    private static int AstronomerId = 1;

    public Astronomer(String fname, String lname){
        super.setFname(fname);
        super.setLname(lname);
    }

    public  Astronomer(){

    }

    public void setAstronomerId(int astronomerId) {
        AstronomerId = astronomerId;
    }

    public int getAstronomerId() {
        return AstronomerId;
    }
}
