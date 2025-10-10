package com.example.sbtechincaltest.presentation.screens

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import com.bumptech.glide.integration.compose.placeholder

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.CrossfadePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.sbtechincaltest.R
import com.example.sbtechincaltest.data.model.PhotosModel
import com.example.sbtechincaltest.presentation.model.PhotosUIModel

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalAnimationApi::class)
@Composable
fun PhotosRowScreen(images : PhotosUIModel) {

    Card(modifier = Modifier.padding(10.dp)
        .wrapContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(10.dp)){

        Row(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ){
            GlideImage(
                model = images.thumbnailUrl,
                contentDescription = "Photo image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(12.dp)
                                    .size(96.dp),
                failure = placeholder(R.drawable.ic_launcher_foreground),
            )

            Column(modifier = Modifier.padding(12.dp)
                ){
                Text(images.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp)
            }
        }

    }



}