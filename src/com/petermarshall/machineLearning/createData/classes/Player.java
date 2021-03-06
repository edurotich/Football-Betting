package com.petermarshall.machineLearning.createData.classes;

//Decision taken in this class to have separate fields for home and away instead of having all the data in 1 ratings array and filtering
//every time we want home/away. Choice taken for speed as we will be sorting through a teams players for each match, so performance wise
//it's better to not have to calculate these things each time.
public class Player {
    private String playerName;
    
    private int ovrMins = 0;
    private int homeMins = 0;
    private int awayMins = 0;
    
    private int totalGames = 0;
    private int homeGames = 0;
    private int awayGames = 0;
    
    private double weightedOvrRating = 0;
    private double weightedHomeRating = 0;
    private double weightedAwayRating = 0;
    
    private double totalOvrRating = 0;
    private double totalHomeRating = 0;
    private double totalAwayRating = 0;

    public Player(String playerName, int mins, double rating, boolean homeTeam) {
        this.playerName = playerName;

        addMatchMinsRating(mins, rating, homeTeam);
    }
    //only to be used when creating a Player from a match that has yet to be played and will be sent off to be predicted.
    public Player(String playerName) {
        this.playerName = playerName;
    }

    public void addMatchMinsRating(int mins, double rating, boolean homeTeam) {
        this.ovrMins += mins;
        this.totalGames++;
        this.totalOvrRating += rating;
        this.weightedOvrRating += mins * rating;

        if (homeTeam) {
            this.homeMins += mins;
            this.homeGames++;
            this.totalHomeRating += rating;
            this.weightedHomeRating += rating * mins;
        } else {
            this.awayMins += mins;
            this.awayGames++;
            this.totalAwayRating += rating;
            this.weightedAwayRating += rating * mins;
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getOvrMins() {
        return ovrMins;
    }
    public int getHomeMins() {
        return homeMins;
    }
    public int getAwayMins() {
        return awayMins;
    }

    public double getWeightedOvrRating() {
        return weightedOvrRating;
    }
    public double getWeightedHomeRating() {
        return weightedHomeRating;
    }
    public double getWeightedAwayRating() {
        return weightedAwayRating;
    }

    //if we have no data on the player yet, give the default rating of 6.
    public double getAvgOvrRating() {
        return this.totalGames == 0 ? 6 : this.totalOvrRating/this.totalGames;
    }
    public double getAvgHomeRating() {
        return this.homeGames == 0 ? 6 : this.totalHomeRating/this.homeGames;
    }
    public double getAvgAwayRating() {
        return this.awayGames == 0 ? 6 : this.totalAwayRating/this.awayGames;
    }
}
