package th.ac.mahidol.ict.model;

public class ScienceObserver extends User
{
    private int ScienceObserverId;
    private String position;

    public int getScienceObserverId() {
        return ScienceObserverId;
    }

    public String getPosition() {
        return position;
    }

    public void setScienceObserverId(int scienceObserverId) {
        ScienceObserverId = scienceObserverId;
    }

    public boolean checkPosition(String pos){
        if(this.position.equals(pos)){
            return true;
        }
        else{
            return false;
        }
    }
}
