## Problem Description

Consider the `MovieRating` class that keeps track of the ratings for a movie. Modify the class to be `Comparable` by adding an appropriate compareTo method.

In general, movies with lower `averageRating()` are considered "less" than other movies. Thus, movies with lower average ratings should appear at the beginning of a sorted list. Movies that have no ratings are considered "less" than all rated movies. If two movies both have not been rated, they are considered "equal". If both movies have been rated and have exactly the same average rating, break ties by considering the one with more `numRatings` to be "greater". If these are still the same, lastly break ties by comparing the `movieTitle()` with movie titles that come alphabetically first being considered "less"/sorted before.

Your method should not modify any movie's state. You may assume the parameter passed is not null.

## Testing Your Code

Press `Run` to run the given `MovieRatingClient.java` code's `main` method. Press Mark to run our JUnit tests. `LocationTest.java` is also available to be seen/edited - simply add test cases to the given code and hit 'Check' (not Mark) to see your code run on your new test suite!

