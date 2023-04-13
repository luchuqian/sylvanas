import org.apache.spark.sql.SparkSession



object SparkDemo {

  val spark = SparkSession
    .builder()
    .master("yarn-client")
    .appName("New SS")
    .config("spark.executor.instances", "10")
    .config("spark.executor.memory", "10g")
    .getOrCreate()
  import spark.implicits._


}
