//import scala.actors.Actor

import akka.actor.{Props, ActorSystem, Actor}
import scala.concurrent.duration._

/**
  * Created by qingmin.chen on 8/28/2016.
  */
class SillyActor1 extends Actor{//这个scala中的actor已经在逐渐废弃了，只能使用akka里面的actor
//    def act(): Unit ={   //这个act方法是老的scala中必须要继承实现的，新版里面应该是用recieve
//        for(i <- 1 to 5){
//          println("I'm acting!")
//          Thread.sleep(1000)
//        }
//    }
      def receive = {
//            for(i <- 1 to 5){
//              println("I'm acting!")
//              Thread.sleep(1000)
//            }
                //Actor.emptyBehavior
          case start =>
            context.actorSelection("/user/sillyActor2") ! "silly2,have you got the message?"
      }

      def react ={

      }

}

class SillyActor2 extends Actor{//这个scala中的actor已经在逐渐废弃了，只能使用akka里面的actor
//    def act(): Unit ={   //这个act方法是老的scala中必须要继承实现的，新版里面应该是用recieve
//        for(i <- 1 to 5){
//          println("I'm acting!")
//          Thread.sleep(1000)
//        }
//    }
      def receive = {
        //            for(i <- 1 to 5){
        //              println("I'm acting!")
        //              Thread.sleep(1000)
        //            }
        //Actor.emptyBehavior
        case msg => println("receive message2:"+msg)
      }

}

case object start

object SillyActor extends App{



  val system = ActorSystem("Test")
  val sillyActor1 = system.actorOf(Props[SillyActor1], name = "sillyActor1")
  val sillyActor2 = system.actorOf(Props[SillyActor2], name = "sillyActor2")

  import system.dispatcher
  system.scheduler.schedule(0 milliseconds, 1 minutes, sillyActor1, start)
}
