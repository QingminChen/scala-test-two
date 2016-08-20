/**
  * Created by qingmin.chen on 8/18/2016.
  */
abstract class Email {
  def isEmail(s:String):Boolean
  def domain(s:String):String
  def user(s:String):String
}

object Email extends App{
//  val emailAddress = "qingmin.chen@accenture.com"
//  if(Email.isEmail(emailAddress)) {
//    println(user(emailAddress)+" AT "+domain(emailAddress))
//  }else{
//    println("not an email address")
//  }
  /**
    * apply这个方法使得email对象可以直接使用括号应用方法的参数，例如可以直接写为：Email("qingmin.chen","accenture.com")构造字符串qingmin.chen@accenture.com
    * unapply这个方法是Email称为抽取器的原因，apply是将两个传入的字符串拼成一个email地址字符串返回，而unapply则是传入email地址字符串并返回可能存在的两个字符串
    * 地址和域
    * 但unapply必须还能传力传入字符串并非email地址的情况，这也是为什么它返回的是Option类型的字符串对偶的原因
    *
    *例如：
    * unapply("John@epfl.cn") 等价于Some("John","epfl.ch")
    * unapply("John Doe") 等价于 None
    *
    * 执行以下代码的话：
    * selectorString match {case Email(user,domain) => ...}   一旦模式匹配碰到与抽取器对象所指的模式，它就会在选择器表达式中调用抽取器的unapply方法，即：Email.unapply(selectorString)
    *
    * apply称为注入方法，因为它可以传入的参数生成指定的字符串
    * unapply称为抽取方法可以把传入的子集抽取其中的一部分
    * 一般来讲注入和抽取方法都在一个对象中，这样既可以把对象名当做构造器用也可以当作模式用，这个对使用样本类作模式匹配的管理的魔方
    * 也可以只在对象内定义抽取方法，不定义相对应的注入方法，对象本省被称为抽取器，与是否具有apply方法无关，若包含了注入方法，就应该与抽取方法呈对欧关系
    * Email.unapply(Email.apply(user,domain)) 结果：Some(user,domain)
    *
    * 如果Some的参数是一对元组队，那么Some((user,domian))  等同于  Some(user,domain)
    */

  def apply(user:String,domain:String) = user + "@" + domain
  def unapply(str:String):Option[(String,String)] = {
    val parts = str.split("@")
    if(parts.length==2) Some(parts(0),parts(1)) else None
  }
}

/**
  * scala中没有一元的元组
  * */
