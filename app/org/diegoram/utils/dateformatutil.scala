package org.diegoram.utils


import java.text.SimpleDateFormat
import java.util.Date


object DateFormatUtil {
  val DATE_FORMAT_REGEXPS = Map(
    "^\\d{8}$" -> "yyyyMMdd",
    "^\\d{1,2}-\\d{1,2}-\\d{4}$" -> "dd-MM-yyyy",
    "^\\d{4}-\\d{1,2}-\\d{1,2}$" -> "yyyy-MM-dd",
    "^\\d{1,2}/\\d{1,2}/\\d{4}$" -> "MM/dd/yyyy",
    "^\\d{4}/\\d{1,2}/\\d{1,2}$" -> "yyyy/MM/dd",
    "^\\d{12}$" -> "yyyyMMddHHmm",
    "^\\d{8}\\s\\d{4}$" -> "yyyyMMdd HHmm",
    "^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$" -> "dd-MM-yyyy HH:mm",
    "^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$" -> "yyyy-MM-dd HH:mm",
    "^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$" -> "MM/dd/yyyy HH:mm",
    "^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$" -> "yyyy/MM/dd HH:mm",
    "^\\d{14}$" -> "yyyyMMddHHmmss",
    "^\\d{8}\\s\\d{6}$" -> "yyyyMMdd HHmmss",
    "^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$" -> "dd-MM-yyyy HH:mm:ss",
    "^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$" -> "yyyy-MM-dd HH:mm:ss",
    "^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$" -> "MM/dd/yyyy HH:mm:ss",
    "^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$" -> "yyyy/MM/dd HH:mm:ss",
    "^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{2}:\\d{2}.\\d{3}$" -> "yyyy-MM-dd'T'HH:mm:ss.SSS",
    "^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{2}:\\d{2}.\\d{3}(Z|[\\+\\-]\\d{4})$" -> "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
    "^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{2}:\\d{2}.\\d{3}[\\+\\-]\\d{2}:\\d{2}$" -> "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
  )

  def determineDateFormat(dateString: String): Option[String] = {
    val matched = DATE_FORMAT_REGEXPS.filterKeys(regexp => dateString.matches(regexp))
    if (matched.isEmpty) None else Some(matched.head._2)
  }

  def stringToDate(dateString: String): Option[Date] = {
    determineDateFormat(dateString) match {
      case Some(df) => Some(new SimpleDateFormat(df).parse(dateString))
      case None =>
        // see if the string is a long value
        stringToLong(dateString) match {
          case Some(l) => Some(new Date(l))
          case _ => None
        }
    }
  }

  def stringToLong(str: String): Option[Long] = try {
    Some(str.toLong)
  } catch {
    case _ => None
  }
}
