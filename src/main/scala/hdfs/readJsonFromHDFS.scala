package hdfs

import java.net.URI

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}


//import play.api._

object readJsonFromHDFS {
  def main(args: Array[String]): Unit = {
    val hdfs = FileSystem.get(new URI("hdfs://yourUrl:port/"), new Configuration())
    val path = new Path("/path/to/file/")
    val stream = hdfs.open(path)
    def readLines = Stream.cons(stream.readLine, Stream.continually( stream.readLine))

    //This example checks line for null and prints every existing line consequentally
    readLines.takeWhile(_ != null).foreach(line => println(line))
  }
}
