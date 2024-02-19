package com.example.ama_bmi_laskuri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ama_bmi_laskuri.ui.theme.AMa_BMI_LaskuriTheme
//package com.example.ama_bmi_laskuri

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AMa_BMI_LaskuriTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    BMIScreen()
                }


            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMIScreen() {
    var pituus by remember { mutableStateOf("") }
    var paino by remember { mutableStateOf("") }
    var tulos by remember { mutableStateOf("Painoindeksi") }
    var onAikuinen by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(15.dp)

    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "BMI laskuri",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF990073)
            )
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(top = 30.dp)
            ){

                TextField(
                    value = pituus,
                    onValueChange = { pituus = it },
                    label = {
                        Text(
                            text = "Pituus (cm)",
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom = 20.dp)
                        ) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xffffe6f0),
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                    )
                )
            }

            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            ){

                TextField(
                    value = paino,
                    onValueChange = { paino = it },
                    label = {
                        Text(
                            "Paino (kg)",
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom = 20.dp)
                        ) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xffffe6f0),
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                    )

                )

            }
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ){
                Checkbox(
                    checked = onAikuinen,
                    onCheckedChange = {onAikuinen = it},
                )
                Text(text = "Olen aikuinen", modifier = Modifier.padding(20.dp))
            }
            Button(onClick = {
                tulos = bMiTulos(
                    pituus.toDouble(),
                    paino.toDouble(),
                    onAikuinen)
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                colors = ButtonDefaults.buttonColors(Color(0xFF990073))
            ){
                Text(text = "Laske")
            }
            if (tulos.isNotBlank()){
                Text(
                    text = tulos,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(30.dp)
                )
            }
        }

    }

        }
        fun bMiTulos (pituus:Double, paino:Double, aikuinen:Boolean): String{
            var pituusMetreina = pituus / 100
            return if (aikuinen) {
                var bmi = 1.3 * paino / (pituusMetreina.pow(2.5))
                "Painoindeksi on $bmi"
            }
            else {
                "Ei voida laskea"
            }
        }


@Preview
@Composable
fun BMIScreenPrev(){
    BMIScreen()
}


