package org.example.liba

import com.example.baselib.BaseLibrary

class ClassA extends BaseLibrary {
  override def sayHello(name: String): String = {
    super.sayHello(s"from lib-a: $name")
  }
}
