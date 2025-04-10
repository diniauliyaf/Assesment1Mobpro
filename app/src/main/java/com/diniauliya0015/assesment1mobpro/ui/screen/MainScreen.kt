package com.diniauliya0015.assesment1mobpro.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.diniauliya0015.assesment1mobpro.R
import com.diniauliya0015.assesment1mobpro.navigation.Screen
import com.diniauliya0015.assesment1mobpro.ui.theme.Assesment1MobproTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    val showMenu = remember { mutableStateOf(false) }
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {showMenu.value = true}) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(R.string.menu_overflow)
                        )
                    }
                }
            )
            if (showMenu.value){
                Column (
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 170.dp, end = 10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.surface)
                ){
                    Box(
                        modifier = Modifier
                            .size(height = 50.dp, width = 200.dp)
                            .clickable {
                                showMenu.value = false
                                navController.navigate(Screen.About.route) }
                            .padding(15.dp)
                    ){
                        Text(
                            text = stringResource(R.string.tentang_aplikasi),
                            color = MaterialTheme.colorScheme.onSurface)
                    }
                    Box(
                        modifier = Modifier
                            .size(height = 50.dp, width = 200.dp)
                            .clickable {
                                showMenu.value = false
                                navController.navigate(Screen.Rumus.route) }
                            .padding(15.dp)
                    ){
                        Text(text = stringResource(R.string.rumus))
                    }
                }
            }
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
    var dayaError by remember { mutableStateOf(false) }
    var energi by remember { mutableStateOf("") }
    var energiError by remember { mutableStateOf(false) }
    var waktu by remember { mutableStateOf("") }
    var waktuError by remember { mutableStateOf(false) }
    var hitungDaya by remember { mutableFloatStateOf(0f) }
    var hitungEnergi by remember { mutableFloatStateOf(0f) }
    var hitungWaktu by remember { mutableFloatStateOf(0f) }
    val hitungDayaText = stringResource(R.string.hitung_daya)
    val hitungEnergiText = stringResource(R.string.hitung_energi)
    val hitungWaktuText = stringResource(R.string.hitung_waktu)

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
                            trailingIcon = { IconPicker(energiError, stringResource(R.string.satuan_energi)) },
                            supportingText = { ErrorHint(energiError) },
                            isError = energiError,
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
                            trailingIcon = { IconPicker(waktuError, stringResource(R.string.satuan_waktu)) },
                            supportingText = { ErrorHint(waktuError) },
                            isError = waktuError,
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = daya,
                            onValueChange = { daya = it },
                            label = { Text(text = stringResource(R.string.daya)) },
                            trailingIcon = { IconPicker(dayaError, stringResource(R.string.satuan_daya)) },
                            supportingText = { ErrorHint(dayaError) },
                            isError = dayaError,
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
                            trailingIcon = { IconPicker(waktuError, stringResource(R.string.satuan_waktu)) },
                            supportingText = { ErrorHint(waktuError) },
                            isError = waktuError,
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = daya,
                            onValueChange = { daya = it },
                            label = { Text(text = stringResource(R.string.daya)) },
                            trailingIcon = { IconPicker(dayaError, stringResource(R.string.satuan_daya)) },
                            supportingText = { ErrorHint(dayaError) },
                            isError = dayaError,
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
                            trailingIcon = { IconPicker(energiError, stringResource(R.string.satuan_energi)) },
                            supportingText = { ErrorHint(energiError) },
                            isError = energiError,
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
                onClick = {
                    when (selectedText) {
                        hitungDayaText -> {
                            energiError = (energi.isBlank() || energi == "0")
                            waktuError = (waktu.isBlank() || waktu == "0")
                            if (!energiError && !waktuError) {
                                hitungDaya = rumusDaya(energi.toFloat(), waktu.toFloat())
                                hitungEnergi = 0f
                                hitungWaktu = 0f
                            }
                        }
                        hitungEnergiText -> {
                            dayaError = (daya.isBlank() || daya == "0")
                            waktuError = (waktu.isBlank() || waktu == "0")
                            if (!dayaError && !waktuError) {
                                hitungEnergi = rumusEnergi(daya.toFloat(), waktu.toFloat())
                                hitungDaya = 0f
                                hitungWaktu = 0f
                            }
                        }
                        hitungWaktuText -> {
                            dayaError = (daya.isBlank() || daya == "0")
                            energiError = (energi.isBlank() || energi == "0")
                            if (!dayaError && !energiError) {
                                hitungWaktu = rumusWaktu(energi.toFloat(), daya.toFloat())
                                hitungDaya = 0f
                                hitungEnergi = 0f
                            }
                        }
                    }
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.hitung))
            }
            when (selectedText) {
                stringResource(R.string.hitung_daya) -> {
                    if (hitungDaya != 0f) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = 1.dp
                        )
                        Text(
                            text = stringResource(R.string.hasil_daya, hitungDaya),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(top = 15.dp)
                        )
                    }
                }
                stringResource(R.string.hitung_energi) -> {
                    if (hitungEnergi !=0f) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = 1.dp
                        )
                        Text(
                            text = stringResource(R.string.hasil_energi, hitungEnergi),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(top = 15.dp)
                        )
                    }
                }
                stringResource(R.string.hitung_waktu) -> {
                    if (hitungWaktu !=0f) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = 1.dp
                        )
                        Text(
                            text = stringResource(R.string.hasil_waktu, hitungWaktu),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(top = 15.dp)
                        )
                    }
                }
            }

        }
    }
}

private fun rumusDaya(energi:Float, waktu:Float): Float{
    return energi / waktu
}
private fun rumusEnergi(daya:Float, waktu:Float): Float{
    return daya * waktu
}
private fun rumusWaktu(energi: Float, daya: Float): Float{
    return energi / daya
}

@Composable
fun IconPicker(isError: Boolean, unit:String) {
    if (isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreview() {
    Assesment1MobproTheme {
        MainScreen(rememberNavController())
    }
}