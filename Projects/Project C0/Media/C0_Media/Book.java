import java.util.*;


public class Book implements Media, Comparable<Book>{
    
    private String title;
    private List<String> authors;
    private List<String> content;
    private List<Integer> ratings;

    public Book(String title, List<String> authors, Scanner contentScanner){
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.content = new ArrayList<>();
        this.ratings = new ArrayList<>();


        while (contentScanner.hasNext()){
            this.content.add(contentScanner.next());
        }
    }


    public String getTitle(){
        return title;
    }


    public List<String> getArtists(){
        return new ArrayList<>(authors);
    }


    public void addRating(int score){
        if (score < 0){
            throw new IllegalArgumentException("Rating must be non-negative.");
        }
        ratings.add(score);
    }


    public int getNumRatings(){
        return ratings.size();
    }


    public double getAverageRating(){
        if (ratings.isEmpty()){
            return 0.0;
        }
        double sum = 0.0;
        for (int r : ratings){
            sum += r;
        }
        return sum / ratings.size();
    }


    public List<String> getContent(){
        return new ArrayList<>(content);
    }


    @Override
    public String toString(){
        String authorsString = "";
        for (int i = 0; i < authors.size(); i++){
            authorsString += authors.get(i);
            if (i < authors.size()-1){
                authorsString += ", ";
            }
        }

        if (ratings.isEmpty()){
            return title + "by [" + authorsString + "]";
        } else {
            double avg = getAverageRating();

            double rounded = Math.round(avg * 100.0) / 100.0;

            return title + " by [" + authorsString + "]: " + rounded + " (" + getNumRatings() + 
                    " ratings)";
        } 
    }



    @Override
    public int compareTo(Book other){
        if (this.getAverageRating() > other.getAverageRating()){
            return 1;
        } else if (this.getAverageRating() < other.getAverageRating()){
            return -1;
        } else {
            //if rating is equal, we compare the title
        } if (this.title.compareToIgnoreCase(other.title) > 0){
            return 1;
        } else if (this.title.compareToIgnoreCase(other.title) < 0){
            return -1;
        } else {
            return 0;
        }
    }
}



