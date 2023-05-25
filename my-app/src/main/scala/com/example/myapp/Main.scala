package com.example.myapp

import org.example.liba.ClassA
import org.example.libb.ClassB

object Main {
  def main(args: Array[String]): Unit = {

    val instanceA = new ClassA()
    val instanceB = new ClassB()
    new MyApp(instanceA, instanceB)
      .sayHelloFromAllLibraries("Anjana")
  }
}
