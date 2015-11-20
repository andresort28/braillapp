package com.project.rubikon.braillapp.Model;

/**
 * Created by jfelipeescobart on 29/10/2015.
 */
public class Tweet {

    private String tweet, translation;

    public Tweet(String tweet){
        this.tweet = tweet;
        translation=translate();
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getTraduccion() {
        return translation;
    }

    public void setTraduccion(String translation) {
        this.translation = translation;
    }

    public String translate(){
        String braille = "";
        String tweetUpperCase = tweet.toUpperCase();
        char[] characters = tweetUpperCase.toCharArray();

        for (int i = 0; i < characters.length; i++) {

            for (int j = 0; j < AsciiCharacter.CHARACTERS.length; j++) {

                if (characters[i]==(char)AsciiCharacter.CHARACTERS[j])

                    braille+=AsciiCharacter.BINARIES[j];

                else

                    braille+=AsciiCharacter.BINARIES[0];
            }
        }

        return braille;
    }
}
