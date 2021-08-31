package com.example.learnkt

import java.lang.StringBuilder
import kotlin.reflect.KProperty

interface Element {
    fun render(builder: StringBuilder, indent: String): String
}

open class BaseElement(var name: String, var content: String = "") : Element {
    val children = ArrayList<Element>()
    val hasMap = HashMap<String, String>()

    override fun render(builder: StringBuilder, indent: String): String {
        builder.append("$indent<$name>\n")
        if (content.isNotBlank()) {
            builder.append(" $indent$content\n")
        }
        children.forEach() {
            it.render(builder, "$indent")
        }
        builder.append("$indent</$name>\n")
        return builder.toString()
    }


    protected fun <T : BaseElement> initString(element: T, block: T.() -> String): T {
        val content = element.block()
        element.content = content
        children.add(element)
        return element
    }

    protected fun <T : Element> init(element: T, block: T.() -> Unit): T {
        element.block()
        children.add(element)
        return element
    }

    operator fun String.invoke(block: BaseElement.() -> Unit): BaseElement {
        val element = BaseElement(this)
        element.block()
        this@BaseElement.children += element
        return element
    }

    operator fun String.invoke(value: String) {
        this@BaseElement.hasMap[this] = value
    }

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
    class Body :BaseElement("body"){
        fun h1(block: H1.()->String)=initString(H1("H1"),block)
        fun p(block: P.()->String)=initString(P("p"),block)
        fun img(src:String,alt:String){

        }
    }

    class P(content: String=""):BaseElement("p",content)
    class H1(content: String=""):BaseElement("h1",content)
    class Title(content: String):BaseElement("title",content)

    /**property.name 即指的是 src  alt
     * 这里 by hasMap 其实就是调用HashMap中的getValue setValue
     * 这个是扩展出来的本来需要写的 然后在HASHMAP中扩展出来的
     */
    operator fun HashMap<String,String>.getValue(thisRef:Any,property:KProperty<*>):String{
       return get(property.name)?:""
    }

    operator fun HashMap<String,String>.setValue(thisRef: Any,property: KProperty<*>,value:String){
        put(property.name,value)
    }

    class IMG:BaseElement("img"){
        var scr:String by hasMap
        var alt:String by hasMap

        override fun render(builder: StringBuilder, indent: String): String {
            builder.append("$indent<$name")
            builder.append(renderAttributes())
            builder.append(" /$name>\n")

            return builder.toString()
        }

        private fun renderAttributes():String{
            val builder=StringBuilder()
            for((attr,value) in hasMap){
                builder.append(" $attr=\"$value\"")
            }
            return builder.toString()
        }



    }



}