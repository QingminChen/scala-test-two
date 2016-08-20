

/**
  * Created by qingmin.chen on 8/13/2016.
  */
abstract class AbstractCurrency {
   type Currency <:AbstractCurrency
   val amount:Long
   def designation:String
   override def toString = amount+""+designation
//  def + (that:Currency):Currency = new Currency{
//     val amount = this.amount + that.amount
//
//  }
}

abstract class Dollar extends AbstractCurrency{
  type Currency = Dollar
  def designation ="dollar"
}

abstract class Euro extends AbstractCurrency{
  type Currency = Euro
  def designation ="euro"
}

object Europe extends CurrencyZone{
  abstract class Euro extends AbstractCurrency{
    def designation = "EUR"
  }
  type Currency = Euro
  def make(cents:Long) = new Euro{
    val amount = cents
  }
  val Cent =  make(1)
  val Euro = make(100)
  val CurrencyUnit = Euro
}

object Japan extends CurrencyZone{
  abstract class Yen extends AbstractCurrency{
    def designation = "JPY"
  }
  type Currency = Yen
  def make(yen:Long) = new Yen {
    val amount = yen
  }
  val Yen =  make(1)
  val CurrencyUnit = Yen
}

object US extends CurrencyZone{
  abstract class Dollar extends AbstractCurrency{
    def designation="USD"
  }
  type Currency = Dollar
  def make (cents:Long) = new Dollar {
    val amount = cents
  }
  val Cent = make(1)
  val Dollar = make(100)
  val CurrencyUnit = Dollar
}

//如果在定义某个未知的东西，可以在这个类中先定义为抽象的

abstract class CurrencyZone{
  val CurrencyUnit:Currency
  type Currency <:AbstractCurrency   //这里就是说这个前面的类型一定是要AbstractCurrency的子类
  def make(x:Long):Currency       //这个是产生一个Currency的工厂
  abstract class AbstractCurrency {
    val amount:Long
    def designation:String
    //override def toString = amount+""+designation
    def + (that:Currency) : Currency = make(this.amount + that.amount)
    def * (x:Double) : Currency = make(this.amount *x.toLong)
    def - (that:Currency) : Currency = make(this.amount - that.amount)
    def / (that:Double)= make((this.amount / that).toLong)
    def / (that:Currency) = this.amount.toDouble / that.amount
    def from(other:CurrencyZone#AbstractCurrency) : Currency=
      make(Math.round(
        other.amount.toDouble * Converter.exchangeRate(other.designation)(this.designation)
        )
      )
    private def decimals(n:Long):Int=
      if(n==1)0 else 1+decimals(n/10)
    override def toString =
      ((amount.toDouble/CurrencyUnit.amount.toDouble) formatted ("%." + decimals(CurrencyUnit.amount) + "f")+" "+ designation)
  }
}

//object USA extends CurrencyZone{
//  abstract class Dollar extends AbstractCurrency{
//    def designation="USD"
//  }
//  type Currency = Dollar
//  def make (x:Long) = new Dollar {
//    val amount = x
//  }
//  val CurrencyUnit= Currency
//
//}



object Converter{
  var exchangeRate = Map(
    "USD" -> Map("USD" -> 1.0,"EUR" -> 0.7596,"JPY" -> 1.211,"CHF" -> 1.223),
    "EUR" -> Map("USD" -> 1.316,"EUR" -> 1.0,"JPY" -> 1.594,"CHF" -> 1.623),
    "JPY" -> Map("USD" -> 0.8257,"EUR" -> 0.6272,"JPY" -> 1.0,"CHF" -> 1.018),
    "CHF" -> Map("USD" -> 0.8108,"EUR" -> 0.6160,"JPY" -> 0.982,"CHF" -> 1.0)
  )
}

object CurrencyZone extends App{
  val res1 = Japan.Yen from US.Dollar*100
  println(res1)
  val res2 = Europe.Euro from res1
  println(res2)
  val res3 = US.Dollar from res2
  println(res3)
}