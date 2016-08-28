/**
  * Created by qingmin.chen on 8/26/2016.
  */
trait Tree[+T] {
  def elem:T
  def left:Tree[T]
  def right:Tree[T]
}

object EmptyTree extends App with Tree[Nothing]{
  def elem = throw new NoSuchElementException("EmptyTree.elem")
  def left = throw new NoSuchElementException("EmptyTree.left")
  def right = throw new NoSuchElementException("EmptyTree.right")
  println("123")
}

/** 这里并不是说两个Branch的子类类型不一样，就一定不相等，
  * 比如包含单个Nil和包含两个空子树的branche就是相等的
  * 这里的不可变类型T是灭有进行比较的，就是前面说的类型擦除机制
  * 模式中以小写字母开头的类型参数代表未知类型
  */

//class Branch[+T](
//     val elem :T,
//     val left:Tree[T],
//     val right:Tree[T]
//)extends Tree[T]{
//  override def equals (other:Any) = other match {
//    case that : Branch[T] =>this.elem == that.elem && this.left == that.left && this.right == that.right
//      /**对于任何类型都可以匹配成功那个,以下这两种写法都是可以的*/
//    //case that : Branch[t] =>this.elem == that.elem && this.left == that.left && this.right == that.right
//    //case that : Branch[_] =>this.elem == that.elem && this.left == that.left && this.right == that.right
//    case _=> false
//  }
//}

class Branch[+T](
     val elem :T,
     val left:Tree[T],
     val right:Tree[T]
)extends Tree[T] {
  override def equals(other: Any) = other match {
    //case that : Branch[T] =>this.elem == that.elem && this.left == that.left && this.right == that.right
    /** 对于任何类型都可以匹配成功那个,以下这两种写法都是可以的 */
    //case that: Branch[t] => this.elem == that.elem && this.left == that.left && this.right == that.right
      /**这种写法也是可以的*/
    case that:Branch[_] => (that.canEqual(this))&& this.elem == that.elem && this.left == that.left && this.right == that.right
    //case that : Branch[_] =>this.elem == that.elem && this.left == that.left && this.right == that.right
    case _ => false
  }

  override def hashCode(): Int = {
    41 * (
      41 * (
        41 + elem.hashCode()
        ) + left.hashCode()
      ) + right.hashCode()
  }

  def canEqual(other: Any) = other match {
    case that: Branch[_] => true
    case _ => false
  }

    //这个写法等同于上面的写法,Branch[_]讲的是方法的类型参数，是所谓的存在类型的简写,有着位置部分的类型
    /**def canEqual(other:Any) = other.isInstanceOf[Branch[_]]*/
}