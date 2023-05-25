package com.example.myapp

import org.example.liba.ClassA
import org.example.libb.ClassB
import org.mockito.MockitoSugar.{mock, verify}
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec

class MyAppSpec extends AnyFlatSpec with GivenWhenThen {
  "MyApp" should "should greet " in {
    Given("a mock instance of ClassA and ClassB")
    val instanceA = mock[ClassA]
    val instanceB = mock[ClassB]
    val myApp = new MyApp(instanceA, instanceB)

    When("sayHelloFromAllLibraries is called")
    myApp.sayHelloFromAllLibraries("World")

    Then("sayHello is called on ClassA and ClassB")
    verify(instanceA).sayHello("World")
    verify(instanceB).sayHi("World")
  }
}
