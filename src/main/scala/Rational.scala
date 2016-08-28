/**
  * Created by qingmin.chen on 8/28/2016.
  */
class Rational(n:Int,d:Int) {

  require(d!=0)
  private val g=gcd(n.abs,d.abs)
  val number = (if (d<0) -n else n) /g
  val denom = d.abs / g

  private def gcd(a:Int,b:Int):Int={
    if(b!=0) a else gcd(b,a%b)
  }

  override def equals (other:Any) : Boolean={
    other match {
      case that:Rational =>
        (that canEqual this) && number == that.number && denom == that.denom
      case _=>false
    }
  }

  def canEqual(other :Any) : Boolean ={
    other.isInstanceOf[Rational]
  }

  override def hashCode : Int = 41*(41+number)+ denom

  override def toString :String = if(denom == 1) number.toString else number +"/"+ denom

  /**如果要在非final的类中重写equal方法，就应该创建canEqual方法，
    * 如果equals的定义是继承自AnyRef(即equals没有在类继承关系的杭冕被重新定义)，则canEqual的定义竟会是新的
    * 否则它将重写之前的同名方法的定义*/

  /**
    * 有一个例外就是关于重定义了继承自AnyRef的equals方法的final类，就不需要定义canEqual，传给canEqual的队形类型应该为Any   就是def canEqual(other:Any):BOolean=
    * 入股欧参数对象是当前类的实例则canEqual返回true(即canEqual定义所在的类里面)，否则返回是false   other.isInstanceOf[Rational]
    * 在equals方法中，记得声明传入的对象类型是Any    override def equals(other:Any):Boolean =
    * 将equals的方法体写为单个match表达式，而match的选择器应为传递给equals的对象    other match{//.....}
    * match 表达式应有两个case，第一个声明为你所以定义equals方法的类的类型模式   case that:Rational =>
    * 在这个case的语句体中，编写一个表达式，把两个对象要相等必须为true的独立表达式以逻辑与的方式结合起来，如果要重写equals方法非AnyRef的那一个，你很可能想要包含对超类的equals方法的调用  super.equals(that) &&
    * 如果为首个引用canEqual的类定义equals方法，应该调用其参数的canEqual方法将this作为参数船进入  (that.canEqual(this)) &&
    * 重写的equals方法也应该包含canEqual的调用，除非它们都包含了对super.equals的调用
    * 对于第二个case，用一个通配的莫斯返回false    case_=>false
    * 在做hashCode的时候，最好选用奇质数
    * 如果hashCode计算影响性能，可以考虑把hashCode缓存起来，如果对象是不可变的话，可以把hashCode缓存到val中去
    * 说白了如果对象不是final的，要重写canEqual方法
    * 如果觉得实现一个比较如此困难，就可以将比较的对象的类定义为杨蓓类，这样scala编辑器将会自动添加正确的符合各项要求的equals和hashCode方法
   * */
}
