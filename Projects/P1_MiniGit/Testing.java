import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class Testing {
    private Repository repo1;
    private Repository repo2;

    // Occurs before each of the individual test cases
    // (creates new repos and resets commit ids)
    @BeforeEach
    public void setUp() {
        repo1 = new Repository("repo1");
        repo2 = new Repository("repo2");
        Repository.Commit.resetIds();
    }

    // TODO: Write your tests here!


    @Test
    @Timeout(1)
    @DisplayName("EXAMPLE TEST - getHistory()")
    public void getHistory() throws InterruptedException {
        // Initialize commit messages
        String[] commitMessages = new String[]{"Initial commit.",
                                               "Updated method documentation.",
                                               "Removed unnecessary object creation."};
        commitAll(repo1, commitMessages);
        testHistory(repo1, 1, commitMessages);
    }

    @Test
    @Timeout(1)
    @DisplayName("synchronize() - Front case")
    public void testSynchronizeFront() throws InterruptedException {
        // Initialize commit messages - repo2 has newer commits
        commitAll(repo1, new String[]{"Old 1", "Old 2"});
        commitAll(repo2, new String[]{"New 1", "New 2"});
        
        // Make sure both repos got exactly 2 commits each
        assertEquals(2, repo1.getRepoSize());
        assertEquals(2, repo2.getRepoSize());

        // Synchronize repo2 into repo1
        repo1.synchronize(repo2);
        assertEquals(4, repo1.getRepoSize());
        assertEquals(0, repo2.getRepoSize());

        // Make sure the history of repo1 is correctly synchronized
        testHistory(repo1, 4, new String[]{"Old 1", "Old 2", "New 1", "New 2"});
    }

    @Test
    @Timeout(1)
    @DisplayName("synchronize() - Middle case")
    public void testSynchronizeMiddle() throws InterruptedException {
        // Initialize commit messages - repo1 has first and last, repo2 has middle
        commitAll(repo1, new String[]{"First"});
        commitAll(repo2, new String[]{"Middle 1", "Middle 2"});
        commitAll(repo1, new String[]{"Last"});
        
        // Make sure repos have correct sizes
        assertEquals(2, repo1.getRepoSize());
        assertEquals(2, repo2.getRepoSize());

        // Synchronize repo2 into repo1
        repo1.synchronize(repo2);
        assertEquals(4, repo1.getRepoSize());
        assertEquals(0, repo2.getRepoSize());

        // Make sure the history of repo1 is correctly synchronized
        testHistory(repo1, 4, new String[]{"First", "Middle 1", "Middle 2", "Last"});
    }

    @Test
    @Timeout(1)
    @DisplayName("synchronize() - Empty case")
    public void testSynchronizeEmpty() throws InterruptedException {
        // Initialize commit messages - only repo1 has commits
        commitAll(repo1, new String[]{"Alpha", "Beta", "Gamma"});
        
        // Make sure repos have correct sizes
        assertEquals(3, repo1.getRepoSize());
        assertEquals(0, repo2.getRepoSize());

        // Synchronize empty repo2 into repo1
        repo1.synchronize(repo2);
        assertEquals(3, repo1.getRepoSize());
        assertEquals(0, repo2.getRepoSize());

        // Make sure the history of repo1 is unchanged
        testHistory(repo1, 3, new String[]{"Alpha", "Beta", "Gamma"});
    }

    @Test
    @Timeout(1)
    @DisplayName("synchronize() - End case")
    public void testSynchronizeEnd() throws InterruptedException {
        // Initialize commit messages - repo2 has older commits
        commitAll(repo2, new String[]{"Ancient 1", "Ancient 2"});
        commitAll(repo1, new String[]{"Recent 1", "Recent 2", "Recent 3"});
        
        // Make sure repos have correct sizes
        assertEquals(3, repo1.getRepoSize());
        assertEquals(2, repo2.getRepoSize());

        // Synchronize repo2 into repo1
        repo1.synchronize(repo2);
        assertEquals(5, repo1.getRepoSize());
        assertEquals(0, repo2.getRepoSize());

        // Make sure the history of repo1 is correctly synchronized
        testHistory(repo1, 5, new String[]{"Ancient 1", "Ancient 2", "Recent 1", "Recent 2", "Recent 3"});
    }

    /////////////////////////////////////////////////////////////////////////////////
    // PROVIDED HELPER METHODS (You don't have to use these if you don't want to!) //
    /////////////////////////////////////////////////////////////////////////////////

    // Commits all of the provided messages into the provided repo, making sure timestamps
    // are correctly sequential (no ties). If used, make sure to include
    //      'throws InterruptedException'
    // much like we do with 'throws FileNotFoundException'. 
    // repo and messages should be non-null.
    // Example useage:
    //
    // repo1:
    //      head -> null
    // To commit the messages "one", "two", "three", "four"
    //      commitAll(repo1, new String[]{"one", "two", "three", "four"})
    // This results in the following after picture
    // repo1:
    //      head -> "four" -> "three" -> "two" -> "one" -> null
    //
    // YOU DO NOT NEED TO UNDERSTAND HOW THIS METHOD WORKS TO USE IT! (this is why documentation
    // is important!)
    public void commitAll(Repository repo, String[] messages) throws InterruptedException {
        // Commit all of the provided messages
        for (String message : messages) {
            int size = repo.getRepoSize();
            repo.commit(message);
            
            // Make sure exactly one commit was added to the repo
            assertEquals(size + 1, repo.getRepoSize(),
                         String.format("Size not correctly updated after commiting message [%s]",
                                       message));

            // Sleep to guarantee that all commits have different time stamps
            Thread.sleep(2);
        }
    }

    // Makes sure the given repositories history is correct up to 'n' commits, checking against
    // all commits made in order. repo and allCommits should be non-null.
    // Example useage:
    //
    // repo1:
    //      head -> "four" -> "three" -> "two" -> "one" -> null
    //      (Commits made in the order ["one", "two", "three", "four"])
    // To test the getHistory() method up to n=3 commits this can be done with:
    //      testHistory(repo1, 3, new String[]{"one", "two", "three", "four"})
    // Similarly, to test getHistory() up to n=4 commits you'd use:
    //      testHistory(repo1, 4, new String[]{"one", "two", "three", "four"})
    //
    // YOU DO NOT NEED TO UNDERSTAND HOW THIS METHOD WORKS TO USE IT! (this is why documentation
    // is important!)
    public void testHistory(Repository repo, int n, String[] allCommits) {
        int totalCommits = repo.getRepoSize();
        assertTrue(n <= totalCommits,
                   String.format("Provided n [%d] too big. Only [%d] commits",
                                 n, totalCommits));
        
        String[] nCommits = repo.getHistory(n).split("\n");
        
        assertTrue(nCommits.length <= n,
                   String.format("getHistory(n) returned more than n [%d] commits", n));
        assertTrue(nCommits.length <= allCommits.length,
                   String.format("Not enough expected commits to check against. " +
                                 "Expected at least [%d]. Actual [%d]",
                                 n, allCommits.length));
        
        for (int i = 0; i < n; i++) {
            String commit = nCommits[i];

            // Old commit messages/ids are on the left and the more recent commit messages/ids are
            // on the right so need to traverse from right to left
            int backwardsIndex = totalCommits - 1 - i;
            String commitMessage = allCommits[backwardsIndex];

            assertTrue(commit.contains(commitMessage),
                       String.format("Commit [%s] doesn't contain expected message [%s]",
                                     commit, commitMessage));
            assertTrue(commit.contains("" + backwardsIndex),
                       String.format("Commit [%s] doesn't contain expected id [%d]",
                                     commit, backwardsIndex));
        }
    }
}
