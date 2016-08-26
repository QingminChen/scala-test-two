/**
  * Created by qingmin.chen on 8/22/2016.
  */
class Point(val x:Int,val y:Int){

  override def hashCode = 41*(41+x)+y
  override def equals(other:Any) = other match{
    case that : Point => this.x==that.x && this.y==that.y
    case _=>false
  }
}

