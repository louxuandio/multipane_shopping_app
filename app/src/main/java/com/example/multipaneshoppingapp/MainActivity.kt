package com.example.multipaneshoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multipaneshoppingapp.ui.theme.MultipaneShoppingAppTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultipaneShoppingAppTheme {

            }
        }
    }
}

@Composable
fun mainpane{
    class Product(val name: String, val price: String, val description: String)
    var products = listOf(
        Product("Steve Job's brain piece", "$100", "This is a piece of Steve Job's brain, order it before CS exams to get high score."),
        Product("Albert Einstein's Thought Wave", "$200", "Ever wondered what it feels like to think like Einstein? Get a piece of his thought wave and feel the brilliance flow through you."),
        Product("Nikola Tesla's Electric Spark", "$150", "Tap into the mind of the greatest inventor. A spark of Tesla's genius to electrify your ideas and energy levels."),
        Product("Shakespeare's Lost Quill", "$75", "Write the next great play or sonnet with a piece of Shakespeare's legendary quill. It’ll inspire words beyond your wildest imagination."),
        Product("Da Vinci's Brushstroke", "$120", "Create masterpieces like Da Vinci himself. A single brushstroke from his genius to elevate your artistic talents."),
        Product("Cleopatra's Charm", "$90", "Capture the timeless elegance of Cleopatra. Wear her charm to unlock unmatched charisma and influence in any room.")
    )
    LazyColumn (
        modifier = Modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        items(products){product ->
            Card(
                onClick = {subpane(product)}
            ) {
                Text(text = product.name)
            }
        }
    }
}
fun subpane(product){
    if (product == null){
        Text("Select a product to view details.")
    }else{
        Column {
            Text(text = "Price: " + product.price,
                fontStyle = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(text = product.discription)
        }
    }
}
