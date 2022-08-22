package com.example.rahul.gridview.kotlin


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.rahul.gridview.R
import kotlinx.android.synthetic.main.activity_kotlin_demo.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class KotlinDemo : AppCompatActivity() {

    //? : return value if not null else return null


     var firstName : String? = ""
     var lastName : String ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_demo)
        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        firstName = "Rahul"

       lastName = "Chaurasia"
       // loadTypeSafeData()
      //  loadArrayData()
      // HashMapTutorial()

        val person = Teacher("Rahul")

        person.displayAge()

        val car = Car("SUZ","YYY")
    }



    fun loadTypeSafeData(){

     // 1 : ?  return value if not null else return null
       println("Hello ${firstName}  ${lastName?.length}")


     // 2 : ?.let  It execute  the block only if lastName is not null

     var a=   lastName?.let {


         lastName!!.length
        }

        println( "a is ${a} " )

        // 3 : ?:  Elvis Operator   if lastName is not null thean used it oherwise default value

        val lenLastName = lastName?.length ?: 1



        var b = lastName!!.length



         println( "b is ${b}" )
    }

    fun loadArrayData(){
        val nums2 = (3..12).toList().toTypedArray()
        println(Arrays.toString(nums2))


        val num1  = intArrayOf(1,3,5)
        for (element in num1)
        {
            println(element)
        }


        var num3 : ArrayList<Department> = ArrayList<Department>()
    //    var num3  = ArrayList<Department>()

        num3.add(Department("SALES","1"))
        num3.add(Department("HR","2"))
        num3.add(Department("IT","3"))

        num3.add(Department("Production","4"))
        num3.add(Department("Marketing","5"))
        num3.add(Department("Retails","6"))


        for( elem in num3) {

            println( "${elem.depName} and ${elem.id}")
        }




    }

    fun HashMapTutorial(){

        // hASh mAP : KEY - VALUE pair

        var myMap = HashMap<Int,String>()   // Mutable , read and write and no fixed size

        myMap.put(1,"A")
        myMap.put(2,"B")
        myMap.put(3,"C")
        myMap.put(4,"D")
        myMap.put(5,"E")
        myMap.put(6,"F")
        myMap.put(7,"D")


        for(key in myMap.keys){
            println("${key} are in map ${myMap.get(key)} ")
        }



    }


}



data class Department(
    val depName: String,
    val id: String
)

class Car (var type : String){

    var id: String = "Q"


    constructor(id: String, type: String) : this(type) {
        this.id = id
        this.type = type
    }
}
class Teacher(name: String): Person(name) {

    var age : Int

    init {
        age = 24
    }

    override fun displayAge() {
        println("Non-abstract class displayAge function overridden. Age is $age")
    }
}

abstract class Person(name: String) {

    init {
        println("Abstract Class. init block. Person name is $name")
    }

    abstract fun displayAge()
}