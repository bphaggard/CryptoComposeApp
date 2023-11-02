package com.example.cryptocompose.screencomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptocompose.R

@Composable
fun CurrenciesCard(
    usLabel: String,
    euLabel: String,
    czLabel: String,
    usValue: String,
    euValue: String,
    czValue: String
){
    Card(
        modifier = Modifier
            .height(170.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    modifier = Modifier.height(38.dp),
                    painter = painterResource(id = R.drawable.united_states),
                    contentDescription = "USA_logo"
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = usLabel
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    maxLines = 1,
                    text = usValue
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    modifier = Modifier.height(38.dp),
                    painter = painterResource(id = R.drawable.european_union),
                    contentDescription = "EUR_logo"
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = euLabel
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    maxLines = 1,
                    text = euValue
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    modifier = Modifier.height(38.dp),
                    painter = painterResource(id = R.drawable.czech_republic),
                    contentDescription = "CZK_logo"
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = czLabel
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    maxLines = 1,
                    text = czValue
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCard(){
    CurrenciesCard(
        usLabel = "BTC value in USD :",
        euLabel = "BTC value in EUR :",
        czLabel = "BTC value in CZK :",
        usValue = "34212.12",
        euValue = "29854.42",
        czValue = "1125963.32")
}