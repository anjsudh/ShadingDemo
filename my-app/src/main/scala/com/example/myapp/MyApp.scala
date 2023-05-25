package com.example.myapp

import org.example.liba.ClassA
import org.example.libb.ClassB

class MyApp(a: ClassA, b: ClassB) {

  def sayHelloFromAllLibraries(name: String) {
    println(a.sayHello(name))
    println(b.sayHi(name))
  }
}
