# Design Twitter

## Problem Statement

Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in their news feed. Your design should support the following methods:

*   `postTweet(userId, tweetId)`: Compose a new tweet.
*   `getNewsFeed(userId)`: Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
*   `follow(followerId, followeeId)`: Follower follows a followee.
*   `unfollow(followerId, followeeId)`: Follower unfollows a followee.

## Solution Approach

We will use a combination of `HashMap` and a `PriorityQueue` (min-heap) to solve this problem efficiently.

1.  **Data Structures:**
    *   A `HashMap<Integer, List<Tweet>> userTweets` to store the tweets for each user. The key is the `userId` and the value is a list of `Tweet` objects.
    *   A `HashMap<Integer, Set<Integer>> following` to store the users that a user follows. The key is the `followerId` and the value is a set of `followeeId`s.
    *   A global `timestamp` to maintain the order of the tweets.
    *   A `Tweet` class to store the `tweetId` and its `timestamp`.

2.  **`postTweet(userId, tweetId)`:**
    *   When a user posts a tweet, we create a new `Tweet` object with the `tweetId` and the current `timestamp`.
    *   We add this tweet to the `userTweets` map for the given `userId`.

3.  **`getNewsFeed(userId)`:**
    *   This is the most complex part. We need to get the 10 most recent tweets from the user and the users they follow.
    *   We will use a `PriorityQueue` (min-heap) of size 10 to keep track of the 10 most recent tweets seen so far. The heap will be ordered by the `timestamp` of the tweets.
    *   First, get the set of users the current user follows from the `following` map.
    *   Get the tweets of the current user and add them to a list.
    *   For each followed user, get their tweets and add them to the list.
    *   Iterate through the list of tweets and add them to the `PriorityQueue`. If the size of the heap exceeds 10, we remove the tweet with the smallest `timestamp` (the oldest tweet).
    *   After iterating through all the tweets, the `PriorityQueue` will contain the 10 most recent tweets. We can then extract the `tweetId`s from the heap and return them in the correct order (most recent to least recent).

4.  **`follow(followerId, followeeId)`:**
    *   We add the `followeeId` to the set of followed users for the `followerId` in the `following` map.

5.  **`unfollow(followerId, followeeId)`:**
    *   We remove the `followeeId` from the set of followed users for the `followerId` in the `following` map.

## Example

```java
Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return [6, 5].
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should now return [5].
twitter.getNewsFeed(1);
```
