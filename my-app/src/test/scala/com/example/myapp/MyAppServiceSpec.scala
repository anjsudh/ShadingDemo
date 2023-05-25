package com.example.myapp

import org.example.liba.ClassA
import org.example.libb.ClassB
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.{be, noException}

class MyAppServiceSpec extends AnyFlatSpec with GivenWhenThen {
  "MyApp" should "should greet " in {
    Given("a real instance of ClassA and ClassB")
    val instanceA = new ClassA()
    val instanceB = new ClassB()
    val myApp = new MyApp(instanceA, instanceB)

    When("sayHelloFromAllLibraries is called")
    Then("no exception is thrown")
    noException should be thrownBy myApp.sayHelloFromAllLibraries("World")

  }
}
