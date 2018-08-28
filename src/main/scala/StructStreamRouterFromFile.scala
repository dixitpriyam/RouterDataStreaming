import org.apache.spark.sql.SparkSession

object StructStreamRouterFromFile{
  def main (args:Array[String]){


    val spark = SparkSession
      .builder
      .appName("StructuredNetworkWordCount")
      .getOrCreate()
    // Create DataFrame representing the stream of input lines from connection to host:port
    val lines = spark.readStream
      .format("socket")
      .option("path", "path/to/destination/dir")
      .load()


  }

}
