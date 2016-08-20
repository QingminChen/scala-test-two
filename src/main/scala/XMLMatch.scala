/**
  * Created by qingmin.chen on 8/20/2016.
  */
object XMLMatch extends App{
   def proc(node:scala.xml.Node):String =
      node match {
        case <a>{contents}</a> => "It's an a: "+ contents
        case <b>{contents}</b> => "It's a b: "+ contents
        case _=> "It‘s something else"
      }

   println(proc(<a>apple</a>))
   println(proc(<b>banana</b>))
   println(proc(<c>cherry</c>))

  /**
    * 以上的例子只能寻找<a>或者<b>中单个子节点的内容，以下的情况就不可以了
    * */
  println(proc(<a>a <em>red</em> apple</a>))
  println(proc(<a/>))


  /**
    * 以下是执行对节点序列而不是当个节点的匹配，任意序列XML节点的模式写作   '_*'
    * 这里就是通过@模式被绑定到了contents变量上面
    * */
  def procNew(node:scala.xml.Node):String =
    node match {
      case <a>{contents @ _*}</a> => "It's an a: "+ contents
      case <b>{contents @ _*}</b> => "It's a b: "+ contents
      case _=> "It‘s something else"
    }
  println(procNew(<a>a <em>red</em> apple</a>))
  println(procNew(<a/>))


  /**
    * 可以结合for进行迭代遍历XML，把需要的部分找到，其它部分忽略掉
    * */
  val catalog1 =
    <catalog>
      <cctherm>
        <description>hot dog #5</description>
        <yearMade>1952</yearMade>
        <dateObtained>March 14,2006</dateObtained>
        <bookPrice>2199</bookPrice>
        <purchasePrice>500</purchasePrice>
        <condition>9</condition>
      </cctherm>
      <cctherm>
        <description>Sprite Boy</description>
        <yearMade>1964</yearMade>
        <dateObtained>April 28,2003</dateObtained>
        <bookPrice>1695</bookPrice>
        <purchasePrice>595</purchasePrice>
        <condition>5</condition>
      </cctherm>
    </catalog>

  val catalog2 =
    <catalog>
      <cctherm>
        <description>hot dog #5</description>
        <yearMade>1952</yearMade>
        <dateObtained>March 14,2006</dateObtained>
        <bookPrice>2199</bookPrice>
        <purchasePrice>500</purchasePrice>
        <condition>9</condition>
      </cctherm>
      <cctherm>
        <description>Sprite Boy</description>
        <yearMade>1964</yearMade>
        <dateObtained>April 28,2003</dateObtained>
        <bookPrice>1695</bookPrice>
        <purchasePrice>595</purchasePrice>
        <condition>5</condition>
      </cctherm>
    </catalog>

  /**
    * 以下的匹配是包括空白的
    * * */
  println("====================================================================")
    catalog1 match {
      case <catalog>{therms @ _*}</catalog> =>
        for(therm <- therms)
          println("processing: "+ (therm \ "description").text)
    }

  /**
    * 以下的匹配是不包括空白的
    * * */
println("====================================================================")

     catalog2 match {
       case <catalog>{therms @_*}</catalog> =>
         for (therm @ <cctherm>{_*}</cctherm> <- therms)              //模式<cctherm>{_*}</cctherm>描述的是这个子集，并限制for表达是枚举能够匹配这个模式的条目
             println ("processing: " + (therm \ "description").text)
     }

}
