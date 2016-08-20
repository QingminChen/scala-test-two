/**
  * Created by qingmin.chen on 8/9/2016.
  */
class QueueNew[+T] private (private[this] var leading:List[T],private[this] var trailing:List[T]){   //0,B->A   这个队列不是很好理解

  private def mirror() =
    if(leading.isEmpty){
      while(!trailing.isEmpty){
        leading = trailing.head::leading
        trailing = trailing.tail

      }
  }

  def head:T ={
    mirror()
    leading.head
  }

  def tail:QueueNew[T] ={
    mirror()
    new QueueNew(leading.tail,trailing)
  }

  def append [U>:T](x:U) = {
    new QueueNew[U](leading,x::trailing)
  }
}
