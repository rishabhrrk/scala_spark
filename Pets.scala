package com.rishabhrrk.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.rdd._
import org.apache.log4j._

  def pet(line:String)={
    val record = line.split(",")
    val petid = record(0)
    val petname = record(1)
    val owner = record(3)
    (owner,(petname,petid))
  }
  
  def owner(line:String)={
    val record = line.split(",")
    val ownerid = record(0)
    val ownername = record(1)
    val city = record(2)
    (ownerid,(ownername,city))
  }
  
  def main(args: Array[String]){
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]","Pets")
    val pet_lines = sc.textFile("E:/Lab/Spark/Datasets Day 1 Onsite/Pets.csv")
    val owner_lines = sc.textFile("E:/Lab/Spark/Datasets Day 1 Onsite/Owner.csv")
    val pet_line = pet_lines.map(pet)
    //val pet_pair = pet_line.map(x=> (x._3,(x._2,x._1)))
    val owner_line = owner_lines.map(owner)
    //val owner_pair = owner_line.map(x=> (x._1,x._2))
    pet_line.join(owner_line).foreach(println)  
  }
}