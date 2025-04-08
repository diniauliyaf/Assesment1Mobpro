package com.diniauliya0015.assesment1mobpro.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diniauliya0015.assesment1mobpro.R
import com.diniauliya0015.assesment1mobpro.ui.theme.Assesment1MobproTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ){ innerPadding->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(modifier: Modifier = Modifier){
    val gambarAwal = painterResource(R.drawable.segitiga_rumus)
    val list = listOf(
        stringResource(R.string.hitung_daya),
        stringResource(R.string.hitung_energi),
        stringResource(R.string.hitung_waktu)
    )
    var selectedText by remember { mutableStateOf(list[0]) }
    var isExpanded by remember { mutableStateOf(false) }
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Image(
            painter = gambarAwal,
            contentDescription = stringResource(R.string.gambar_awal),
            modifier = modifier
                .size(230.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(id = R.string.app_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = !isExpanded}
            ) {
                TextField(
                    modifier = Modifier
                        .width(220.dp)
                        .menuAnchor(),
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(text = stringResource(R.string.pilih_rumus)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)  },
                    textStyle = TextStyle(fontSize = 16.sp),
                    singleLine = true
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }) {
                    list.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = {
                                selectedText = list[index]
                                isExpanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreview() {
    Assesment1MobproTheme {
        MainScreen()
    }
}