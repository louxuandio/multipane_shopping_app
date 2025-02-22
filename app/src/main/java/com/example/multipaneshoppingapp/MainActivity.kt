package com.example.multipaneshoppingapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
        setContent {
            MultipaneShoppingAppTheme {
                MainScreen()
            }
        }
    }
}

class Product(val name: String, val price: String, val description: String)
@Composable
fun MainScreen(){
    var products = listOf(
        Product("Steve Job's brain piece", "$100", "This is a piece of Steve Job's brain, order it before CS exams to get high score."),
        Product("Albert Einstein's Thought Wave", "$200", "Ever wondered what it feels like to think like Einstein? Get a piece of his thought wave and feel the brilliance flow through you."),
        Product("Nikola Tesla's Electric Spark", "$150", "Tap into the mind of the greatest inventor. A spark of Tesla's genius to electrify your ideas and energy levels."),
        Product("Shakespeare's Lost Quill", "$75", "Write the next great play or sonnet with a piece of Shakespeare's legendary quill. It’ll inspire words beyond your wildest imagination."),
        Product("Da Vinci's Brushstroke", "$120", "Create masterpieces like Da Vinci himself. A single brushstroke from his genius to elevate your artistic talents."),
        Product("Cleopatra's Charm", "$90", "Capture the timeless elegance of Cleopatra. Wear her charm to unlock unmatched charisma and influence in any room.")
    )
    var selectedProduct by remember{ mutableStateOf<Product?>(null) }
    val isPortrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT //ChatGPT

    if ( !isPortrait){
        // Two-pane layout for landscape view
        Row (modifier = Modifier.fillMaxSize()){
            ProductList(products=products, selectedProduct, onProductSelected = {selectedProduct = it}, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            VerticalDivider(color = Color.Gray, thickness = 1.dp)
            subpane(selectedProduct, modifier = Modifier.weight(1f))
        }
    }else{
        // Single-pane layout for portrait view
        // if no product is selected, show product list
        if (selectedProduct==null){
            ProductList(products, selectedProduct, onProductSelected = {selectedProduct = it}, modifier = Modifier.fillMaxSize())
        }else{
            //else, show details
            subpane(selectedProduct, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
// I asked ChatGPT how to modify selectedProduct, which is defined in MainScreen, in ProductList.
// ProductList does not modify the state directly but instead triggers the onProductSelected function to notify MainScreen
fun ProductList(products:List<Product>, selectedProduct: Product?,onProductSelected: (Product) -> Unit, modifier: Modifier){
    LazyColumn (
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        items(products){product ->
            Card(
                onClick = {onProductSelected(product)},
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = product.name, fontSize = 24.sp, modifier = Modifier.padding(12.dp))
            }
        }
    }
}
@Composable
fun subpane(selectedProduct:Product?, modifier: Modifier){
    if (selectedProduct == null){
        Text("Select a product to view details.",
            modifier = Modifier.padding(8.dp))
    }else{
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Price: ${selectedProduct.price}",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(text = selectedProduct.description,
                fontSize = 20.sp)
        }
    }
}
