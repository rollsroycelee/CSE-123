import java.util.*;
import java.text.SimpleDateFormat;
//Royce Lee
//10/29/2025
//CSE123
//P1 MiniGit
//TA: Jonah
//This class is a repository that can get the head of the repository, history of commits, 
//add commits to the repository, remove commits, and merge two repositories together into one
//repoistory, all while tracking all the changes that are made.
public class Repository {


    /**
     * TODO: Implement your code here.
     */
    private String name;
    private Commit repoHead;


    //Behavior: 
    // - This method is the constructor of the repository and initializes an instance of the name
    //   and the head of the repository
    //Parameters:
    // - String name: name of the repository
    //Returns: nothing
    //Exceptions:
    // - If the given name is null, or empty, a new IllegalArgumentException is thrown
    public Repository(String name){
        if(name == null || name.equals("")){
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    //Behavior:
    // - This method is displays the most recent commit of the repository
    //Parameter:
    // - nothing
    //Returns: 
    // - String: returns the ID of the current head of the repository or null if the
    // -        repository head is null
    //Exception:
    // - nothing
    public String getRepoHead(){
        if(this.repoHead == null){
            return null;
        }
        return this.repoHead.id;
    }

    //Behavior:
    // - This method gets the size of the repository
    //Parameter:
    // - nothing
    //Returns: 
    // - int: the amount of commits inside the repository
    //Exception:
    // - nothing
    public int getRepoSize(){
        Commit current = this.repoHead;
        int counter = 0;
        while(current != null){
            counter ++;
            current = current.past;
            }
        return counter;
    }

    //Behavior:
    // - This mehtod returns information of the repository
    //Parameter:
    // - nothing
    //Returns: 
    // - String: returns the name of the repository followed by the current head commit 
    //           or if there is none, "No Commits" is returned with the name of the repository
    //Exception:
    // - nothing
    public String toString(){
        if(this.repoHead == null){
            return this.name + " - No commits";
        }
        return this.name + " - Current head: " + this.repoHead.toString();
    }

    //Behavior:
    // - This method checks whether or not a given id is in the repository
    //Parameter:
    // - String targetId: user inputted ID of a commit
    //Returns: 
    // - boolean: if the given id is contained in the repository, true is returned
    //            if not, then false is returned
    //Exception:
    // - if the given id is equal to null, a new IllegalArgumentException is thrown
    public boolean contains(String targetId){
        if(targetId == null){
            throw new IllegalArgumentException();
        }
        Commit current = this.repoHead;
        while(current != null) {
            if(current.id.equals(targetId)){
                return true;
            }
            current = current.past;
        }
        return false;
    }
    
    //Behavior:
    // - this method displays a given amount of the previous commit history within the repository
    //   if the given amount is greater than the available commits, then all of the commits are
    //   displayed
    //Parameter:
    // - int n: amount of previous commits that is displayed
    //Returns: 
    // - String: a string of previous commits
    //Exception:
    // -If the chosen value integer is non positive or zero, an IllegalArgumentException is thrown
    public String getHistory(int n){
        if(n <= 0){
            throw new IllegalArgumentException();
        }
        String history = "";
        Commit current = this.repoHead;
        int counter = 0;
        while(counter < n && current != null){
            if(counter > 0){
                history += "\n";
            }
            history += current.toString();
            counter ++;
            current = current.past;
        }
        return history;
    }

    //Behavior:
    // - This method creates a new commit in the chosen repository along with a message
    //Parameter:
    // - String message: a user inputted message for the commit
    //Returns: 
    // - String: the ID of the new commit created
    //Exception:
    // - If the given message to input is null, then an IllegalArgumentException is thrown
    public String commit(String message){
        if(message == null){
            throw new IllegalArgumentException();
        }
        this.repoHead = new Commit(message, this.repoHead);
        return this.repoHead.id;
    }

    //Behavior:
    // - an inputted ID will remove a commit from the repository
    //Parameter:
    // - String targetId: user given ID of a commit
    //Returns: 
    // - boolean: returns true if the chosen id of the commit is removed and
    //            false if the ID is not found in the repository
    //Exception:
    // -if the ID given is equal to null, an IllegalArgumentException is thrown
    public boolean drop(String targetId){
        if(targetId == null){
            throw new IllegalArgumentException();
        }
        if(this.repoHead == null){
            return false;
        }
        if(this.repoHead.id.equals(targetId)){
            this.repoHead = this.repoHead.past;
            return true;
        }
        Commit current = this.repoHead;
        while(current.past != null){
            if(current.past.id.equals(targetId)){
                current.past = current.past.past;
                return true;
            }
            current = current.past;
        }
        return false;
    }

    //Behavior:
    // - This method weaves in a user chosen repository and adds the commits of the other
    //   repository to the current repository. The other repository is emptied after 
    //   synchronize finishes
    //Parameter:
    // - Repository other: another repository of commits that has been created
    //Returns: 
    // - nothing
    //Exception:
    // - If the other repository that is being weaved in is null, an IllegalArgumentException is
    //   thrown
    public void synchronize(Repository other){
        if(other == null){
            throw new IllegalArgumentException();
        }
        Commit thisCurrent = this.repoHead;
        Commit otherCurrent = other.repoHead;
        if(otherCurrent != null && 
            (thisCurrent == null || otherCurrent.timeStamp > thisCurrent.timeStamp)){
            this.repoHead = otherCurrent;
            otherCurrent = otherCurrent.past;
            this.repoHead.past = thisCurrent;
            thisCurrent = this.repoHead;
        }
        while(otherCurrent != null){
            if(thisCurrent.past == null || otherCurrent.timeStamp > thisCurrent.past.timeStamp){
                Commit oldOtherCurrent = otherCurrent.past;
                otherCurrent.past = thisCurrent.past;
                thisCurrent.past = otherCurrent;
                otherCurrent = oldOtherCurrent;
            }
            //else {
                //thisCurrent.past = otherCurrent;
                //otherCurrent = otherCurrent.past;
                //thisCurrent = thisCurrent.past;
            //}
            thisCurrent = thisCurrent.past;
        }
        other.repoHead = null;
    }

    /**
     * DO NOT MODIFY
     * A class that represents a single commit in the repository.
     * Commits are characterized by an identifier, a commit message,
     * and the time that the commit was made. A commit also stores
     * a reference to the immediately previous commit if it exists.
     *
     * Staff Note: You may notice that the comments in this 
     * class openly mention the fields of the class. This is fine 
     * because the fields of the Commit class are public. In general, 
     * be careful about revealing implementation details!
     */
    public static class Commit {

        private static int currentCommitID;

        /**
         * The time, in milliseconds, at which this commit was created.
         */
        public final long timeStamp;

        /**
         * A unique identifier for this commit.
         */
        public final String id;

        /**
         * A message describing the changes made in this commit.
         */
        public final String message;

        /**
         * A reference to the previous commit, if it exists. Otherwise, null.
         */
        public Commit past;

        /**
         * Constructs a commit object. The unique identifier and timestamp
         * are automatically generated.
         * @param message A message describing the changes made in this commit. Should be non-null.
         * @param past A reference to the commit made immediately before this
         *             commit.
         */
        public Commit(String message, Commit past) {
            this.id = "" + currentCommitID++;
            this.message = message;
            this.timeStamp = System.currentTimeMillis();
            this.past = past;
        }

        /**
         * Constructs a commit object with no previous commit. The unique
         * identifier and timestamp are automatically generated.
         * @param message A message describing the changes made in this commit. Should be non-null.
         */
        public Commit(String message) {
            this(message, null);
        }

        /**
         * Returns a string representation of this commit. The string
         * representation consists of this commit's unique identifier,
         * timestamp, and message, in the following form:
         *      "[identifier] at [timestamp]: [message]"
         * @return The string representation of this collection.
         */
        @Override
        public String toString() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(timeStamp);

            return id + " at " + formatter.format(date) + ": " + message;
        }

        /**
        * Resets the IDs of the commit nodes such that they reset to 0.
        * Primarily for testing purposes.
        */
        public static void resetIds() {
            Commit.currentCommitID = 0;
        }
    }
}
