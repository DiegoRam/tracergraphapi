package org.diegoram

import java.util.Date

import com.ansvia.graph.BlueprintsWrapper.DbObject



case class User(userId: String, screenName: String) extends DbObject

case class Tweet(tweetId: String, text: String, timeStamp: Date) extends DbObject

