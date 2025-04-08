package com.diniauliya0015.assesment1mobpro.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    var daya by remember { mutableStateOf("") }
    var energi by remember { mutableStateOf("") }
    var waktu by remember { mutableStateOf("") }
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
            modifier = Modifier
                .size(230.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(id = R.string.app_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
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
            when (selectedText) {
                stringResource(R.string.hitung_daya) -> {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = energi,
                            onValueChange = { energi = it },
                            label = { Text(text = stringResource(R.string.energi)) },
                            trailingIcon = {
                                Text(
                                    text = stringResource(R.string.satuan_energi),
                                    fontSize = 12.sp
                                )
                            },
                            textStyle = TextStyle(fontSize = 14.sp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = waktu,
                            onValueChange = { waktu = it },
                            label = { Text(text = stringResource(R.string.waktu)) },
                            trailingIcon = {
                                Text(
                                    text = stringResource(R.string.satuan_waktu),
                                    fontSize = 12.sp
                                )
                            },
                            textStyle = TextStyle(fontSize = 14.sp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            )
                        )
                    }
                }
                stringResource(R.string.hitung_energi) -> {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = daya,
                            onValueChange = { daya = it },
                            label = { Text(text = stringResource(R.string.daya)) },
                            trailingIcon = {
                                Text(
                                    text = stringResource(R.string.satuan_daya),
                                    fontSize = 12.sp
                                )
                            },
                            textStyle = TextStyle(fontSize = 14.sp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = waktu,
                            onValueChange = { waktu = it },
                            label = { Text(text = stringResource(R.string.waktu)) },
                            trailingIcon = {
                                Text(
                                    text = stringResource(R.string.satuan_waktu),
                                    fontSize = 12.sp
                                )
                            },
                            textStyle = TextStyle(fontSize = 14.sp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            )
                        )
                    }
                }
                stringResource(R.string.hitung_waktu) -> {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = daya,
                            onValueChange = { daya = it },
                            label = { Text(text = stringResource(R.string.daya)) },
                            trailingIcon = {
                                Text(
                                    text = stringResource(R.string.satuan_daya),
                                    fontSize = 12.sp
                                )
                            },
                            textStyle = TextStyle(fontSize = 14.sp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = energi,
                            onValueChange = { energi = it },
                            label = { Text(text = stringResource(R.string.energi)) },
                            trailingIcon = {
                                Text(
                                    text = stringResource(R.string.satuan_energi),
                                    fontSize = 12.sp
                                )
                            },
                            textStyle = TextStyle(fontSize = 14.sp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            )
                        )
                    }
                }
            }
            Button(
                onClick = {},
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.hitung))
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