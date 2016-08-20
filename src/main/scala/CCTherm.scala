/**
  * Created by qingmin.chen on 8/20/2016.
  */
abstract class CCTherm {
  val description:String
  val yearMade:Int
  val dateObtained:String
  val bookPrice:Int
  val purchasePrice:Int
  val condition:Int
  override def toString = description

  def toXML =
     <cctherm>
       <description>{description}</description>
       <yearMade>{yearMade}</yearMade>
       <dateObtained>{dateObtained}</dateObtained>
       <bookPrice>{bookPrice}</bookPrice>
       <purchasePrice>{purchasePrice}</purchasePrice>
       <condition>{condition}</condition>
     </cctherm>

}

/**
  *如果想在xml格式中包含花括号('{'或者'}',就可以直接写，而不需要转义符)
  * */
object CCTherm extends App{

     val therm = new CCTherm{
       val description:String = "hot dog #5"
       val yearMade:Int = 1952
       val dateObtained:String = "March 14,2006"
       val bookPrice:Int = 2199
       val purchasePrice:Int = 500
       val condition:Int = 9
     }
     println(therm.toXML)

  println(<a>{{brace yourself}}</a>)
  //输出结果就是   <a>{brace yourself}</a>


  /**
    *这里就是提取纯文本:结果就是Sounds  good
    * */
  println(<a>Sounds <tag/> good</a>.text)

  /**
    * 这里对&gt自动解码为>:结果就是input  ---> output
    * */
  println(<a>input  ---&gt; output</a>.text)


  /**
    * 抽取子元素:如果你想要通过标签找到子元素，只要调用\ 加标签就可以,结果就是：<b><c>hello</c></b>
    * 只是这里没有看出来 "\" 和 "\\" 的区别
    * */
  println(<a><b><c>hello</c></b></a>\ "b")
  println(<a><b><c>hello</c></b></a>\\ "b")

  /**
    * 抽取属性,可以同样使用"\" 和 "\\"，只要在属性名称之前加上at符号，即"@"，结果就是：Joe
    * */
  val joe = <employee name="Joe" rank="code monkey" serial="123"/>
  println(joe \ "@name")


  def fromXML(node:scala.xml.Node): CCTherm =
     new CCTherm{
       val description = (node \ "description").text
       val yearMade = (node \ "yearMade").text.toInt
       val dateObtained = (node \ "dateObtained").text
       val bookPrice = (node \ "bookPrice").text.toInt
       val purchasePrice = (node \ "purchasePrice").text.toInt
       val condition = (node \ "condition").text.toInt
     }

  /**
    * 执行结果：hot dog #5   为什么只显示一个字段
    * */
  println(fromXML(therm.toXML))

  /**
    * XML与字节流之间的转换，建议从XML直接转字节调方法，如果自己先从XML转到String再转到字节，会要考虑字符串编码的问题了
    * 把XMl转成字节文件 XML.save
    * 需要选择的部分包括：文件名，要保存的节点即字符编码，第四个参数决定是否在文件头写上包含字符编码的XML声明，第五个参数是这个XML文档类型，也可以是null，未指定
    *
    * 这个执行结果就是在当前项目根目录下生成therm1.xml文件，内容如下：
    * <?xml version='1.0' encoding='UTF-8'?>
    *   <cctherm>
    *   <description>hot dog #5</description>
    *   <yearMade>1952</yearMade>
    *   <dateObtained>March 14,2006</dateObtained>
    *   <bookPrice>2199</bookPrice>
    *   <purchasePrice>500</purchasePrice>
    *   <condition>9</condition>
    * </cctherm>
    * */
  println(scala.xml.XML.save("therm1.xml",therm.toXML,"UTF-8",true,null))

  /**
    * 加载文件loadFile ，就可以直接打印出来
    * */
  println(scala.xml.XML.loadFile("therm1.xml"))


  /**
    *针对各种读写对象 "reader" 和 "writer"  以及输入输出流 "input stream" 和 "output stream" 的读写方法
    * */

}


