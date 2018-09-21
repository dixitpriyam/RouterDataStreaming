//package testscripts
//
//import java.sql.Timestamp
//
//import org.apache.spark.sql.SparkSession
//import org.apache.spark.sql.functions._
//import org.apache.spark.sql.types.StructType
//object StructStreamRouterFromFileWindow{
//  def main (args:Array[String]){
//
//
//    val spark = SparkSession
//      .builder
//      .appName("StructuredNetworkWordCount")
//      .config("spark.master", "local")
//      .config("spark.sql.warehouse.dir", "file:///C:/Users/priyamdixit/Desktop/RDA_01/spark-warehouse")
//      .getOrCreate()
//
//    val userSchema = new StructType().add("name", "string").add("age","String")
//    // Create DataFrame representing the stream of input lines from connection to host:port
//
//    //    val df = sqlContext.read.
//    //      format("com.databricks.spark.csv").
//    //      option("header","true").
//    //      option("inferSchema","true").
//    //      load("employee.csv") //load data from a file
//
//
//
//    val lines = spark.readStream
//      .format("com.databricks.spark.csv")
//      .schema(userSchema)
//      .load("file:///C:/Users/priyamdixit/Desktop/SparkData/C2ImportCalEventSample.csv")
//
//    import spark.implicits._
//    // Split the lines into words
//    // Split the lines into words, retaining timestamps
//    val words = lines.as[(String, Timestamp)].flatMap(line =>
//      line._1.split(" ").map(word => (word, line._2))
//    ).toDF("word", "timestamp")
//
//    // Group the data by window and word and compute the count of each group
//    val windowedCounts = words.groupBy(
//      window($"timestamp", "10 seconds", "5 seconds"), $"word"
//    ).count().orderBy("window")
//
//    // Start running the query that prints the windowed word counts to the console
//    val query = windowedCounts.writeStream
//      .outputMode("complete")
//      .format("console")
//      .option("truncate", "false")
//      .start()
//
//    query.awaitTermination()
//  }
//}
