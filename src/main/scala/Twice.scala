/**
  * Created by qingmin.chen on 8/18/2016.
  */
object Twice extends App {
    def apply(s:String):String = s+s
    def unapply(s:String):Option[String] = {
      val length = s.length/2
      val half = s.substring(0,length)
      if(half == s.substring(length)) Some(half) else None
    }
}

/**
  * 抽取器模式不绑定任何变量，这种unapply方法返回布尔值，来说明是否匹配成功
  * */
object UpperCase extends App{
  def unapply(s:String):Boolean = s.toUpperCase == s
  def userTwiceUpper(s:String) = s match {
    /**
      * UpperCase()这里的括号一定不可以省略掉，否则就会跟UpperCase对象进行匹配检查
      * */
    case Email(Twice(x @ UpperCase()),domain) =>              //这里要匹配邮箱地址，并且邮箱地址的@前面的用户名称必须是全部大写，并且重复两遍的邮箱地址
      "match: "+ x +" in domain "+domain
    case _ => "no match"
  }

  println(userTwiceUpper("DADA@aacenture.com"))
}


/**
  * unapplySeq这个就是抽取器如果是变参的
  * */
object  Domain{
  def apply(parts:String*):String={  //可选
    parts.reverse.mkString(",")
  }
  def unapplySeq(whole:String):Option[Seq[String]]=Some(whole.split("\\.").reverse)
  //不可选,并且seq它是若干序列类的（List，Array，RichString等）的共有超类

//  dom match{
//    case Domain("org","acm") => println("acm.org")
//    case Domain("com","sum","java") => println("java.sun.com")
//    case Domain("net",_*) => println("a .net domain")
//  }

  def isTomIntDotCom(s:String):Boolean = s match {
    case Email("tom",Domain("com",_*))=>true
    case _ => false
  }

  println(isTomIntDotCom("tom@sun.com"))
}

/**
  * 不知道这个方法为什么Some是Option子类，List是Seq子类，但是这里却说不匹配
  * */
object ExpandedEmail extends App{
  def unapplySeq(email:String): Option[(String,Array[String])]={
    val parts =email split "@"
    if(parts.length == 2)
      Some(parts(0),parts(1).split("\\.").reverse)
    else
      None
  }

  val s = "tom@support.epfl.ch"
  //println(ExpandedEmail(name,topdom,subdoms @ _*)) = s
}
