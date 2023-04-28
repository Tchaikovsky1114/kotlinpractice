
from
class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultText = findViewById<TextView>(R.id.textViewResult);
        val maleButton = findViewById<RadioButton>(R.id.radioButtonMale)
        val femaleButton = findViewById<RadioButton>(R.id.radioButtonFemale)
        val ageEditText = findViewById<EditText>(R.id.editTextAge)
        val feetEditText = findViewById<EditText>(R.id.editTextFeet)
        val inchesEditText = findViewById<EditText>(R.id.editTextInches)
        val weightEditText = findViewById<EditText>(R.id.editTextWeight)
        val calculateButton = findViewById<Button>(R.id.buttonCalculate)


        // 버튼 클릭시 bmi 지수와 나이에 비례한 bmi 출력
        calculateButton.setOnClickListener {
            val age = if(ageEditText.text.isEmpty()) 0 else ageEditText.text.toString().toInt()
            val feet = if (feetEditText.text.isEmpty()) 0 else feetEditText.text.toString().toInt()
            val inches = if(inchesEditText.text.isEmpty()) 0 else inchesEditText.text.toString().toInt()
            val weight = if (weightEditText.text.isEmpty()) 0 else weightEditText.text.toString().toInt()
            val height = (feet * 12 + inches) * 0.0254
            val bmi =  String.format("%.1f", weight / (height * height)).toDouble()

            if(age == 0 || feet == 0 || inches == 0 || weight == 0 ) {
                Toast.makeText(this, "모든 값을 입력해주세요.", Toast.LENGTH_SHORT).show()
                resultText.text = "모든 값을 입력해주세요."
                return@setOnClickListener
            }
            if (maleButton.isChecked) {
                if (age >= 20) {
                    if (bmi < 18.5) {
                        resultText.text = "BMI: $bmi (저체중)"
                    } else if (bmi < 25) {
                        resultText.text = "BMI: $bmi (정상)"
                    } else if (bmi < 30) {
                        resultText.text = "BMI: $bmi (과체중)"
                    } else {
                        resultText.text = "BMI: $bmi (비만)"
                    }
                } else {
                    if (bmi < 19) {
                        resultText.text = "BMI: $bmi (저체중)"
                    } else if (bmi < 24) {
                        resultText.text = "BMI: $bmi (정상)"
                    } else if (bmi < 29) {
                        resultText.text = "BMI: $bmi (과체중)"
                    } else {
                        resultText.text = "BMI: $bmi (비만)"
                    }
                }
            } else if (femaleButton.isChecked) {
                if (age >= 20) {
                    if (bmi < 18.5) {
                        resultText.text = "BMI: $bmi (저체중)"
                    } else if (bmi < 25) {
                        resultText.text = "BMI: $bmi (정상)"
                    } else if (bmi < 30) {
                        resultText.text = "BMI: $bmi (과체중)"
                    } else {
                        resultText.text = "BMI: $bmi (비만)"
                    }
                } else {
                    if (bmi < 18) {
                        resultText.text = "BMI: $bmi (저체중)"
                    } else if (bmi < 23) {
                        resultText.text = "BMI: $bmi (정상)"
                    } else if (bmi < 28) {
                        resultText.text = "BMI: $bmi (과체중)"
                    } else {
                        resultText.text = "BMI: $bmi (비만)"
                    }
                }
            }
        }
    }
}

to
class MainActivity : AppCompatActivity() {

    // 1. 사용할 위젯을 늦은 초기화로 선언
    private lateinit var resultText : TextView
    private lateinit var maleButton : RadioButton
    private lateinit var femaleButton : RadioButton
    private lateinit var ageEditText : EditText
    private lateinit var feetEditText :EditText
    private lateinit var inchesEditText :EditText
    private lateinit var weightEditText :EditText
    private lateinit var calculateButton :Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 초기화 한 값 할당
         resultText = findViewById<TextView>(R.id.textViewResult);
         maleButton = findViewById<RadioButton>(R.id.radioButtonMale)
         femaleButton = findViewById<RadioButton>(R.id.radioButtonFemale)
         ageEditText = findViewById<EditText>(R.id.editTextAge)
         feetEditText = findViewById<EditText>(R.id.editTextFeet)
         inchesEditText = findViewById<EditText>(R.id.editTextInches)
         weightEditText = findViewById<EditText>(R.id.editTextWeight)
         calculateButton = findViewById<Button>(R.id.buttonCalculate)

        // 버튼 클릭시 bmi 지수와 나이에 비례한 bmi 출력
        calculateButton.setOnClickListener {
            calculateBMI()
        }
    }


    //BMI를 측정하는 함수를 onCreate 외부에서 선언
    @SuppressLint("SetTextI18n")
    private fun calculateBMI() {
        val age = if(ageEditText.text.isEmpty()) 0 else ageEditText.text.toString().toInt()
        val feet = if (feetEditText.text.isEmpty()) 0 else feetEditText.text.toString().toInt()
        val inches = if(inchesEditText.text.isEmpty()) 0 else inchesEditText.text.toString().toInt()
        val weight = if (weightEditText.text.isEmpty()) 0 else weightEditText.text.toString().toInt()
        if(age == 0 || feet == 0 || inches == 0 || weight == 0 ) {
            showToastAndSetRulstText("모든 값을 입력해주세요")
            return
        }
        val height = (feet * 12 + inches) * 0.0254
        val bmi =  String.format("%.1f", weight / (height * height)).toDouble()
        val bmiCategory = getBmiCategory(bmi, age, maleButton.isChecked)
        setResultText(bmiCategory, bmi)
    }

    // bmi를 통해 유저가 어떤 범주에 속하는지 확인하는 함수
    private fun getBmiCategory(bmi: Double, age: Int, isMale: Boolean): String {
        return if (isMale) {
            if (age >= 20) {
                when {
                    bmi < 18.5 -> "저체중"
                    bmi < 25 -> "정상"
                    bmi < 30 -> "과체중"
                    else -> "비만"
                }
            } else {
                when {
                    bmi < 19 -> "저체중"
                    bmi < 24 -> "정상"
                    bmi < 29 -> "과체중"
                    else -> "비만"
                }
            }
        } else {
            if (age >= 20) {
                when {
                    bmi < 18.5 -> "저체중"
                    bmi < 25 -> "정상"
                    bmi < 30 -> "과체중"
                    else -> "비만"
                }
            } else {
                when {
                    bmi < 18 -> "저체중"
                    bmi < 23 -> "정상"
                    bmi < 28 -> "과체중"
                    else -> "비만"
                }
            }
        }
    }

    // 토스트 메세지와 결과값을 출력하는 함수
    private fun showToastAndSetRulstText(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        resultText.text = message
    }

    // 결과값을 화면에 출력하는 함수
    @SuppressLint("SetTextI18n")
    private fun setResultText (bmiCategory: String, bmi: Double) {
        resultText.text = "당신의 BMI 지수는 ${bmi}이며, $bmiCategory 입니다."
    }
}
