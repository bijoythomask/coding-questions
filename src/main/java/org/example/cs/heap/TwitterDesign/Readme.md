# Design Twitter

## Problem Statement

Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in their news feed. Your design should support the following methods:

*   `postTweet(userId, tweetId)`: Compose a new tweet.
*   `getNewsFeed(userId)`: Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
*   `follow(followerId, followeeId)`: Follower follows a followee.
*   `unfollow(followerId, followeeId)`: Follower unfollows a followee.

## Solution Approach

We will use a combination of `HashMap` and a `PriorityQueue` (max-heap) to solve this problem efficiently.

1.  **Data Structures:**
    *   A global `timestamp` counter to order tweets chronologically.
    *   A `HashMap<Integer, User>` to store user-specific data.
    *   A `User` class containing:
        *   A `Set<Integer>` of `followees` to store the IDs of users they follow.
        *   A `Tweet` head reference to a singly-linked list of their own tweets.
    *   A `Tweet` class containing the `tweetId`, `timestamp`, and a `next` reference to the next tweet.

2.  **`postTweet(userId, tweetId)`:**
    *   When a user posts, we create a new `Tweet` object with the `tweetId` and the current global `timestamp`.
    *   This new tweet becomes the new head of the user's tweet linked list. This is an O(1) operation.

3.  **`getNewsFeed(userId)`:**
    *   This is the most complex part. We need to merge the k most recent tweets from the user and all their followees. A **max-heap** is perfect for this.
    *   First, get the set of users the current user follows.
    *   Create a `PriorityQueue` (max-heap) ordered by `timestamp` in descending order.
    *   For the user and each of their followees, if they have posted any tweets, add the head of their tweet linked list to the max-heap.
    *   Now, loop 10 times (or until the heap is empty) to get the top 10 tweets:
        *   `poll()` the tweet with the largest timestamp from the heap. This is the most recent tweet across all considered users.
        *   Add its `tweetId` to the result list.
        *   If this tweet has a `next` tweet in its linked list, add the `next` tweet to the heap. This ensures we continue to consider tweets from the user who posted the most recent one.
    *   This process is a k-way merge, which efficiently finds the most recent tweets without having to look at every single tweet from every followed user.

4.  **`follow(followerId, followeeId)`:**
    *   Add the `followeeId` to the set of followed users for the `followerId`.

5.  **`unfollow(followerId, followeeId)`:**
    *   Remove the `followeeId` from the set of followed users for the `followerId`.

## Complexity Analysis
-   `postTweet`: O(1).
-   `follow`/`unfollow`: O(1).
-   `getNewsFeed`: O(K * log(F)), where K is the number of tweets to retrieve (10) and F is the number of followees. The heap size is at most F+1. We perform K poll/add operations.

## Example

```java
Twitter twitter = new Twitter();
twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
twitter.getNewsFeed(1); // -> [5]
twitter.follow(1, 2); // User 1 follows user 2.
twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
twitter.getNewsFeed(1); // -> [6, 5]
twitter.unfollow(1, 2); // User 1 unfollows user 2.
twitter.getNewsFeed(1); // -> [5]
```

## Alternate Approach: Brute-Force Retrieval
A simpler but less efficient way to implement `getNewsFeed` would be:
1.  Gather all tweets from the user and all of their followees into a single list.
2.  Sort this entire list of tweets by timestamp in descending order.
3.  Return the first 10 tweets from the sorted list.

This is much slower, with a complexity of O(T log T), where T is the total number of tweets from all followed users, which can be very large. The heap-based k-way merge approach is significantly more efficient.
