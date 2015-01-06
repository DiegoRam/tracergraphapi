package org.diegoram

import com.ansvia.graph.BlueprintsWrapper.DbObject


case class User(userId: String, screenName: String) extends DbObject

case class Tweet(tweetId: String, text: String, timeStamp: Long) extends DbObject

