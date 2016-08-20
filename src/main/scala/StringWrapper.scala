/**
  * Created by qingmin.chen on 8/14/2016.
  */


/**
  * 这里的隐式转换，就是把原本不是RandomAccessSeq的子类String通过这个普通方法实现一个适配器，让String变成RandomAccessSeq的子类，这个隐式转换唯一
  * 的区别就是前面有修饰符implicit
  * 以下这个例子是针对2.7版本来说的，因为我用的2.11，所以这个特质已经在新版本中废弃了
  * */
object StringWrapper extends App{
//  implicit def stringWrapper(s: String) =
//    new RandomAccessSeq[Char] {
//      def length = s.length
//      def apply(i: Int) = s.charAt(i)
//    }
//
//    stringWrapper("123abc") exists (_)

  //implicit def int2Fraction(n: Int) = Fraction(n, 1)
  val list1 = List(1,2,3,4)
  val list2 = List("1","2","3","4")
  val list3 = list1::list2
  println(list3)
}

//abstract class Fruit{
//
//}
//class Apple extends Fruit{
////  val m:String = "apple"
////  val v:String = "apple"
//  override def m:String="apple"
//  override def v:String="apple"
//}
//
//class Orange extends Fruit{
////  val m:String = "Orange"
////  val v:String = "Orange"
//  def m:String="Orange"
//  def v:String="Orange"
//}

