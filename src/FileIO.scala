import java.io.FileWriter
import java.io._
import scala.collection.mutable
import scala.io.Source

object FileIO {
  def main(args: Array[String]): Unit = {

    val items = mutable.MutableList[(String, String, String, String, String)]()

    def prompt(s: String) = {
      println(s);
      io.StdIn.readLine()
    }

    Source
      .fromFile("purchases.txt")
      .getLines().drop(1)
      .foreach(line => {
        val purchase = {
          val Array(id, date, cc, cvv, category) = line.split(",").map(_.trim)
          (id, date, cc, cvv, category)
        }
        items += purchase
      })

    def choice = {
      val seq = Seq("Furniture", "Alcohol", "Toiletries", "Shoes", "Food", "Jewelry").mkString("\n")
      prompt(s"\nPlease type a category:\n${seq}\n").toUpperCase
    }

    val input = choice
    val pw = new PrintWriter(new File("newfile.txt"))
    val r = items
      .filter(i => i._5.toUpperCase == input)
    .map(i => pw.write(s"Customer: ${i._1}, Date: ${i._2}\n"))
    pw.close()
  }

}