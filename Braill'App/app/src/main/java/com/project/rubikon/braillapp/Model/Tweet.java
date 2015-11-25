package com.project.rubikon.braillapp.Model;

import java.util.ArrayList;


public class Tweet {

    private String tweet;
    private ArrayList<String> translation;

    public Tweet(String tweet) {
        this.tweet = tweet;
        translation = new ArrayList<String>();
        translate();
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public ArrayList<String> getTraslation() {
        return translation;
    }

    public void setTraslation(ArrayList<String> translation) {
        this.translation = translation;
    }

    public void translate() {
        String braille = "";
        String tweetUpperCase = tweet.toUpperCase();
        char[] characters = tweetUpperCase.toCharArray();

        int counter = 0;
        for (int i = 0; i < characters.length; i++) {
            for (int j = 0; j < AsciiCharacter.CHARACTERS.length; j++) {
                if (characters[i] == (char) AsciiCharacter.CHARACTERS[j]) {
                    braille += AsciiCharacter.BINARIES[j];
                    if (i % 8 == 0) {
                        translation.add(braille);
                        braille = "";
                    }
                    break;
                }
            }
        }
    }
}
