/**
  * Created by qingmin.chen on 8/13/2016.
  */
object Color extends Enumeration{
  val Red = "valueTest"
  val Green = "valueTest"
  val Blue = "valueTest"

  //如果三个参数右边的值一样，还可以写成这样
  //val Red,Green,Blue = "valueTest"

  /***
    * 这个Color有三个值，我们可以直接引用Color.Red,或者引用全部Color._
    */

}

object Direction extends Enumeration{
  val North = Value("north")
  val South = Value("South")
  val East = Value("east")
  val West = Value("west")

}

object DirectionTest extends App{
  for(d <- Direction.values){//看来API已经改了
    println(d)
  }
}
