package com.project.rubikon.braillapp;

        import android.util.Log;

        import twitter4j.AsyncTwitter;
        import twitter4j.AsyncTwitterFactory;
        import twitter4j.TwitterListener;
        import twitter4j.conf.ConfigurationBuilder;

public class TweetRepository {
    private static TweetRepository instance;

    private TweetRepository() {
        Log.d("TAG-RUBIKON", "TweetRepository created");
    }

    public static TweetRepository getInstance() {
        if (instance == null) {
            instance = new TweetRepository();
        }
        return instance;
    }

    private ConfigurationBuilder getConfiguration() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("iwq8znX0kMkqlzkM27EEeSd0r")
                .setOAuthConsumerSecret("JUEDwL5li2P1E9qNAfnbKO58pH1bzfV6Co8imrY93pN7y3YWlE")
                .setOAuthAccessToken("284720086-o3FgPBsvBpwrsjV78BiJoO7dA6GM68M2GoTlfMDY")
                .setOAuthAccessTokenSecret("awu0C0z92jYHAhDH9R1sXEP2OUKgowPsJGddDBNyBPxY0");

        return cb;
    }

    public void getTimelineAsync(TwitterListener listener) {

        AsyncTwitterFactory factory = new AsyncTwitterFactory((getConfiguration().build()));
        AsyncTwitter asyncTwitter = factory.getInstance();
        asyncTwitter.addListener(listener);
        asyncTwitter.getUserTimeline("@NTN24");
        //asyncTwitter.getHomeTimeline();
    }
}
