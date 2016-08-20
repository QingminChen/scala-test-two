/**
  * Created by qingmin.chen on 8/2/2016.
  */

/**
  * 关于时间开销问题，append操作应该在常量时间内完成。
  * 为了做到这一点，我们使用两个List，分别称为leading和trailing，来表达队列。
  * leading包含前段元素，而trailing包含了反向排列的后段元素。队列在任何时刻的所有内容都可以表示为leading ::: trailing.reverse。
  * 想要添加新元素，只要使用::操作符将其添加到trailing，使得append是常量时间。
  * 当原始的空队列通过后继的append操作构建起来时，trailing将不断增加，而leading始终是空白的
  * 于是，在对空的leading第一次执行head或者tail操作之前，trailing应该被反转并复制给leading，这个操作称为mirror。
  * mirror操作花费的时间大概与队列的元素数量成正比，但仅在leading为空时。
  * 如果leading非空，它将直接返回。
  * 因为head和tail调用了mirror，所以他们的复杂度与队列长度呈线性关系。
  * 实际上，假设leading为空时，mirror才翻转并复制，那么n个元素需要进行n次tail之后才进行复制。这n次tail操作平均分担了mirror操作的时间复杂度，也相当于常量时间了。
  * */

  /**
    * head 返回队列的第一个元素
    * tail 返回除第一个元素之外的队列
    * append 返回尾部添加了指定元素的新队列
    * */
class Queue[T](private val leading :List[T],private val trailing :List[T]) {

      private def mirror =
        if(leading.isEmpty)
           new Queue(trailing.reverse,Nil)
        else
           this
      def head = mirror.leading.head

      def tail ={
        val q = mirror

      }

}
