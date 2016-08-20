/**
  * Created by qingmin.chen on 8/10/2016.
  */
class Person (val firstName:String,val lastName:String)extends Ordered[Person]{    //就是姓和名都是符合排序才是可以的

  override def compare(that: Person): Int = {
    val lastNameComparison = lastName.compareToIgnoreCase(that.lastName)
    if(lastNameComparison!=0){
      lastNameComparison
    }else{
      firstName.compareToIgnoreCase(that.firstName)
    }
  }

  override def toString = firstName+" "+lastName


}

/***
  * 因为Person混入了Ordered的特质，所以Person是Ordered的子类，因为T<:Ordered[T]，说明上界必须是Ordered，那么参数类型一定是它的子类
  * */
object Person extends App{
  val robot = new Person("robot","zones")
  val sally = new Person("sally","smith")
  println(robot<sally)

  val people = List(new Person("A","B"),new Person("B","C"),new Person("C","A"))


  def orderedMergeSort[T<:Ordered[T]](xs:List[T]):List[T]={
    def merge(xs:List[T],ys:List[T]):List[T]=
      (xs,ys) match{
        case(Nil,_)=>ys
        case(_,Nil)=>xs
        case(x::xs1,y::ys1)=>
          if(x<y) x::merge(xs1,ys)
          else y::merge(xs,ys1)

      }
    val n= xs.length/2    //这相当于一个二分法
    if(n==0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(orderedMergeSort(ys),orderedMergeSort(zs))
    }
  }

  println(orderedMergeSort(people))
  toString
  
}
/***
  * 这个不可以对整数列表排序，因为Int不是Ordered的子类，所以还不够通用
  * */
