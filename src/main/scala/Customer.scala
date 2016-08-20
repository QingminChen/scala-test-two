/**
  * Created by qingmin.chen on 8/9/2016.
  */
object Customer extends App{
   def getTitle(p : Publication):String = p.title
   Library.printBookList(getTitle)       //这里为什么传了空参数依然可以编译过,这里getTitle的参数类型是Publication，printBookList参数类型是Book，Publication是Book的超类，任何定义在超类中的成员都可以在子类中生效

}

class Publication(val title:String)

class Book(title:String) extends Publication(title)

object Library{
  val books: Set[Book] = Set(new Book("program in scala"),new Book("Walden"))
  def printBookList (info : Book => AnyRef) {
      for(book<-books)println(info(book))
  }
}
