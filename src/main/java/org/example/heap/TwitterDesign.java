package org.example.heap;

/**
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.

Implement the Twitter class:

Twitter() Initializes your twitter object.
void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.
**/

import java.util.*;

public class TwitterDesign {

    private static int timestamp = 0;
    private Map<Integer, Set<Integer>> followers;
    private Map<Integer, List<Tweet>> tweets;

    public TwitterDesign() {
        followers = new HashMap<>();
        tweets = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweets.putIfAbsent(userId, new ArrayList<>());
        tweets.get(userId).add(new Tweet(tweetId, timestamp++));
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> b.time - a.time);
        Set<Integer> users = followers.getOrDefault(userId, new HashSet<>());
        users.add(userId);
        for (int user : users) {
            List<Tweet> userTweets = tweets.getOrDefault(user, new ArrayList<>());
            for (Tweet tweet : userTweets) {
                pq.add(tweet);
                if (pq.size() > 10) {
                    pq.poll();
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(0, pq.poll().id);
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        followers.putIfAbsent(followerId, new HashSet<>());
        followers.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followers.containsKey(followerId)) {
            followers.get(followerId).remove(followeeId);
        }
    }

    private static class Tweet {
        int id;
        int time;

        Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }
}

