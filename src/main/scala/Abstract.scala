/**
  * Created by qingmin.chen on 8/11/2016.
  */
trait Abstract {

  type T
  def transform(x:T):T
  val initial :T
  var current :T

}

class Concrete extends Abstract {
  override type T = String

  override def transform(x: String): String = x+x

  override val initial: T = "initial"
  override var current: T = initial
}


/***
  * val限定了合法的实现方式，任何实现val的类型也应该是val，而不是var或者是def
  * 抽象的方法可以被具体地实现，或者被定义成val来定义
  * */
abstract class Fruit{
   val v:String
   def m:String
}

abstract class Apple extends Fruit{

  val v:String
  val m:String     //可以用val实现def m这个抽象方法

}

//abstract class BadApple extends Fruit{
//
//  def v:String   //这里这样用是错误的，不能用def重写val，但是不知道为什么这里没有编译报错
//  def m:String
//
//}

/***
  * 声明为类成员的var都自带有getter和setter方法
  * */
trait AbstractTime{
  var hour:Int
  var minute:Int
}

trait RationalAll{
  val numberArg:Int
  val denomArg:Int

}

object RationalTest extends RationalAll{
  val numberArg = 1
  val denomArg =2

}

class RationalClass(n:Int,d:Int) extends {
  val numberArg = n
  val denomArg = d
}with RationalAll {

  //  def + (that:RationalClass) = new RationalClass{
  //    numberArg*that.denomArg +that.numberArg*denomArg,denomArg*that.denomArg
  //  }with
  }

  object Demo {
    val x = {
      println("initialized demo")
      "done"
    }
  }

  object Demo2 {
    lazy val x = {
      //懒加载的参数绝对不会计算第二次
      println("initialized demo")
      "done"
    }
  }

  object test extends App {
//    Demo //这里没有lazy，所以在初始化类型的时候，就初始化了x的值
//    println("1")
//    Demo.x
//    println("2")
    //  Demo2
    //  println("3")
    //  Demo2.x
    //  println("4")

//      val x : Int =2
//      println(
//      new RationalTrait{
//        val numberArg = 2*x
//        val denomArg = 3*x
//      })    //这个结果之所以报错是因为在初始化这个特制的时候，因为这个赋值还没有算全,那么初始值默认还是0，所以就会抛异常

    val y:Int =3
    println(new LazyRationalTrait{
      val numberArg = 2*y
      val denomArg = 6*y
    })   //懒加载的作用就是避免因为顺序不清楚导致异常，懒加载可以避免如何安排val定义顺序，以确保所有的东西在使用之前已经完成定义
  }

  /** *
    * 实际上object定义的单例对象，可以被看做是用匿名类描述对象内容的懒加载val定义的缩写
    * */

    trait RationalTrait {
      val numberArg: Int
      val denomArg: Int
      require(denomArg != 0)
      private val g = gcd(numberArg, denomArg)
      val numer = numberArg / g
      val denom = denomArg / g

      private def gcd(a: Int, b: Int): Int =
        if (b == 0) a else gcd(b, a % b)

      override def toString = numer + "/" + denom
    }

    trait LazyRationalTrait {
      val numberArg: Int
      val denomArg: Int

      lazy val numer = numberArg / g
      lazy val denom = denomArg / g

      //override def toString = numer + "/" + denom

      private lazy val g={
        require(denomArg != 0)
        gcd(numberArg,denomArg)
      }

      private def gcd(a: Int, b: Int): Int =
        if (b == 0) a else gcd(b, a % b)

      override def toString = numer + "/" + denom
    }

