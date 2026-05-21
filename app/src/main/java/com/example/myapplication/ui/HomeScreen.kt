package com.example.myapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.data.ProfileRepository
import com.example.myapplication.R

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = ProfileRepository(context)
    val restaurantName = repository.getRestaurantName()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Top Section: Brand Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 24.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.size(220.dp),
                shape = RoundedCornerShape(40.dp), // More elegant rounded corners
                color = Color.White,
                shadowElevation = 12.dp // Deeper shadow for premium feel
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Mamamia",
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Benvenuto a",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = FontFamily.Cursive,
                    fontStyle = FontStyle.Italic,
                    letterSpacing = 1.sp
                ),
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Mamamia",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontFamily = FontFamily.Cursive,
                    fontSize = 64.sp // Slightly larger for impact
                ),
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            Text(
                "ITALIAN STREET FOOD",
                style = MaterialTheme.typography.labelLarge.copy(
                    letterSpacing = 4.sp // Wider spacing for "High Fashion" vibe
                ),
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
        }

        // Promotional Banner / Featured Section (Horizontal Scroll)
        Text(
            "Spesial Hari Ini",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
        )
        
        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth().height(200.dp)
        ) {
            val promos = listOf(
                Triple("Diskon 20% Pizza", R.drawable.piza, "1"),
                Triple("Gratis Gelato", R.drawable.gelato, "10"),
                Triple("Paket Lasagna", R.drawable.lasa, "3")
            )
            items(promos) { promo ->
                Card(
                    modifier = Modifier
                        .width(280.dp)
                        .fillMaxHeight()
                        .clickable { navController.navigate("detail/${promo.third}") },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = promo.second),
                            contentDescription = promo.first,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        // Overlay gradient agar teks terbaca
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                                        startY = 100f
                                    )
                                )
                        )
                        Text(
                            promo.first,
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Navigation Quick Links
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Button(
                onClick = { navController.navigate("menu") },
                modifier = Modifier.fillMaxWidth().height(60.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("🍕 Jelajahi Menu", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedButton(
                onClick = { navController.navigate("profile") },
                modifier = Modifier.fillMaxWidth().height(60.dp),
                shape = RoundedCornerShape(16.dp),
                border = ButtonDefaults.outlinedButtonBorder
            ) {
                Text("🇮🇹 Tentang Kami", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}
