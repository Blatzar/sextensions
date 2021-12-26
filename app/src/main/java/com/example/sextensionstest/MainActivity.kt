package com.example.sextensionstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bsh.Interpreter
import network.Requests
import kotlin.concurrent.thread

var app = Requests()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        app.initClient(this)

        val interpreter = Interpreter()
        thread {
            interpreter.eval(
                """
            import network.Requests;
            Requests session = new Requests();
//            AppResponse response = session.get("https://www.beanshell.org/manual/syntax.html");
//            System.out.println(session.get("http://www.beanshell.org/manual/syntax.html").text);
            
            int addTwoNumbers( int a, int b ) {
                return a + b;
            }

            sum = addTwoNumbers( 5, 7 );  // 12
            
            System.out.println("Running here");
            System.out.println(sum);
            """.trimIndent()
            )
        }

    }
}