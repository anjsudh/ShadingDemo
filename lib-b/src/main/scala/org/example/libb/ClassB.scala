package org.example.libb

import com.example.baselib.BaseLibrary

class ClassB extends BaseLibrary {
  override def sayHi(name: String): String = {
    super.sayHi(s"from lib-b: $name")
  }
}
