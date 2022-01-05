package com.jetpackcomposelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jetpackcomposelearning.ui.theme.JetpackComposeLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeLearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val greetingListState = remember {
        mutableStateListOf("Ahsan", "Ahsan")
    }
    val textFieldContentState = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList(greetingListState, {
            greetingListState.add(textFieldContentState.value)
        },
            textFieldContentState.value,
            { newValue ->
                textFieldContentState.value = newValue
            })
    }
}

@Composable
fun GreetingList(
    names: List<String>,
    onClick: () -> Unit,
    initialTextFieldValue: String,
    onValueChangeListener: (newName: String) -> Unit
) {

    for (name in names)
        GreetingText(name)


    TextField(value = initialTextFieldValue, onValueChange = onValueChangeListener)

    Button(
        onClick = onClick
    ) {
        Text(text = "Add new name")
    }

}

@Composable
fun GreetingText(name: String) {
    Text(
        text = "Hello $name!", style = MaterialTheme.typography.h2
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeLearningTheme {
        MainScreen()
    }
}