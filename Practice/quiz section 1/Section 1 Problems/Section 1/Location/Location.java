public class Location {
    private String name;
    private double latitude;
    private double longitude;

    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    // TODO: Implement compareTo here:


    @Override
    public int compareTo(Location other){
        if (Math.abs(this.latitude) !=  Math.abs(this.latitude)){
            if (Math.abs(this.latitude) > Math.abs(this.latitude)){
                return 1;
            } else if (Math.abs(this.latitude) < Math.abs(this.latitude)) { 
                return -1;
            }
        } else {
            if (Math.abs(this.longitude) > Math.abs(other.longitude)){
                return 1;
            } else { 
                return 0;
            }

        }
        
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String toString() {
        String result = name + " " + Math.abs(latitude);
        if (latitude < 0) {
            result += "S";
        } else {
            result += "N";
        }
        result += " " + Math.abs(longitude);
        if (longitude < 0) {
            result += "E";
        } else {
            result += "W";
        }
        return result;
    }
}
