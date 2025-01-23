package ir.dunijet.mashinhesab


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Toast
import ir.dunijet.mashinhesab.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    private var operator:Char ='+'
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumberClicked()
        onOperatorClicked()
    }


    private fun onOperatorClicked() {
       binding.btnJam.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val myChar = binding.txtExpression.text.last()
                if (myChar == '+' || myChar == '-' || myChar == '*' || myChar == '/') {

                } else {
                    appendText("+")
                }
            }
       }
        binding.btnMenha.setOnClickListener {
            if(binding.txtExpression.text.isNotEmpty()){

                val myChar= binding.txtExpression.text.last()
                if(myChar=='+' || myChar=='-' || myChar=='*' || myChar=='/'){

                }else{

                    appendText("-")
                }
            }
        }
        binding.btnZarb.setOnClickListener {
            if(binding.txtExpression.text.isNotEmpty()){
                val myChar= binding.txtExpression.text.last()
                if(myChar=='+' || myChar=='-' || myChar=='*' || myChar=='/'){

                }else{

                    appendText("*")
                }

            }
        }
        binding.btnTagsim.setOnClickListener {
            if(binding.txtExpression.text.isNotEmpty()){

                val myChar= binding.txtExpression.text.last()
                if(myChar=='+' || myChar=='-' || myChar=='*' || myChar=='/'){

                }else{

                    appendText("/")
                }
            }
        }
        binding.btnParantezBaste.setOnClickListener {
            appendText(")")
        }
        binding.btnParantezBaz.setOnClickListener {
            appendText("(")
        }
        binding.btnMosavi.setOnClickListener {

            try {


                val expression = ExpressionBuilder(binding.txtExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    binding.txtJavab.text = longResult.toString()
                } else {
                    binding.txtJavab.text = result.toString()
                }
            }catch (e:Exception){
                binding.txtExpression.text = ""
                binding.txtJavab.text = ""
                Toast.makeText(this,"ارور رخ داده!!!",Toast.LENGTH_SHORT).show()

            }

        }
        binding.btnAc.setOnClickListener {
            binding.txtExpression.text = ""
            binding.txtJavab.text = ""
        }
        binding.btnPakidan.setOnClickListener {
            val oldtext = binding.txtExpression.text.toString()
            if (oldtext.isNotEmpty()) {
                binding.txtExpression.text = oldtext.substring(0, oldtext.length - 1)
            }
        }
    }

    private fun onNumberClicked() {

        binding.btn0.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()) {
                appendText("0")
            }
        }
        binding.btn1.setOnClickListener {
            appendText("1")
        }
        binding.btn2.setOnClickListener {
            appendText("2")
        }
        binding.btn3.setOnClickListener {
            appendText("3")
        }
        binding.btn4.setOnClickListener {
            appendText("4")
        }
        binding.btn5.setOnClickListener {
            appendText("5")
        }
        binding.btn6.setOnClickListener {
            appendText("6")
        }
        binding.btn7.setOnClickListener {
            appendText("7")
        }
        binding.btn8.setOnClickListener {
            appendText("8")
        }
        binding.btn9.setOnClickListener {
            appendText("9")
        }
        binding.btnDot.setOnClickListener {
            if (binding.txtExpression.text.isEmpty() || binding.txtJavab.text.isNotEmpty()) {
                appendText("0.")
            } else if (!binding.txtExpression.text.contains(".")) {
                appendText(".")
            }

        }

    }

    private fun appendText(newText: String) {

        if (binding.txtJavab.text.isNotEmpty()) {
            binding.txtExpression.text = " "
        }
        binding.txtJavab.text = ""

        binding.txtExpression.append(newText) //اینجا عدد یا اپراتور را اضافه می کنیم




        val viewTree:ViewTreeObserver = binding.horizpntalScrollViewTxtExpression.viewTreeObserver
         viewTree.addOnGlobalLayoutListener(object: OnGlobalLayoutListener{
            override fun onGlobalLayout() {
               binding.horizpntalScrollViewTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(this)
                binding.horizpntalScrollViewTxtExpression.scrollTo(binding.txtExpression.width,0)
            }

        })

       

    }

}