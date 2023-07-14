package com.example.resume.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.mvvm.core.livedata.DownloadResultState.Companion.onProgress
import com.example.mvvm.ext.toast
import com.example.resume.bean.bean.Inform
import com.example.resume.ui.activity.ui.theme.ResumeTheme
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import rxhttp.toAwait
import rxhttp.toFlow
import rxhttp.wrapper.param.RxHttp

class AddManuallyFragment : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyView()
        }
    }

    @Composable
    fun MyView() {
        Column {
            Text(
                text = "手动简历上传",
                Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 25.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace
            )
            var textName by rememberSaveable {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = textName,
                onValueChange = { textName = it },
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .height(60.dp),
                label = { Text(text = "姓名") },
                placeholder = {
                    Text(
                        text = "请输入姓名",
                        fontSize = 12.sp,
                        color = Color.Blue
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xfff6f9fc),  // 背景等相关颜色
                    unfocusedIndicatorColor = Color(0xffdde0e3),
                    focusedIndicatorColor = Color(0xff969eed),//获得焦点时外圈颜色
                    errorIndicatorColor = Color.Red,
                    placeholderColor = Color.Red,
                    textColor = Color.Black,
                    cursorColor = Color.Red
                ),

                )
            var highest_education_level by rememberSaveable() { mutableStateOf("") }
            OutlinedTextField(
                value = highest_education_level,
                onValueChange = { highest_education_level = it },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                    .height(60.dp),
                label = { Text(text = "学历") },
                placeholder = {
                    Text(
                        text = "请输入学历",
                        fontSize = 12.sp,
                        color = Color.Blue
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xfff6f9fc),  // 背景等相关颜色
                    unfocusedIndicatorColor = Color(0xffdde0e3),
                    focusedIndicatorColor = Color(0xff969eed),//获得焦点时外圈颜色
                    errorIndicatorColor = Color.Red,
                    placeholderColor = Color.Red,
                    textColor = Color.Black,
                    cursorColor = Color.Red
                ),

                )
            var graduated_school by rememberSaveable() { mutableStateOf("") }
            OutlinedTextField(
                value = graduated_school,
                onValueChange = { graduated_school = it },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                    .height(60.dp),
                label = { Text(text = "毕业院校") },
                placeholder = {
                    Text(
                        text = "请输入学校",
                        fontSize = 12.sp,
                        color = Color.Blue
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xfff6f9fc),  // 背景等相关颜色
                    unfocusedIndicatorColor = Color(0xffdde0e3),
                    focusedIndicatorColor = Color(0xff969eed),//获得焦点时外圈颜色
                    errorIndicatorColor = Color.Red,
                    placeholderColor = Color.Red,
                    textColor = Color.Black,
                    cursorColor = Color.Red
                ),
            )
            var working_years by rememberSaveable() { mutableStateOf("") }
            OutlinedTextField(
                value = working_years,
                onValueChange = { working_years = it },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                    .height(60.dp),
                label = { Text(text = "工作年限") },
                placeholder = {
                    Text(
                        text = "请输入工作年限",
                        fontSize = 12.sp,
                        color = Color.Blue
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xfff6f9fc),  // 背景等相关颜色
                    unfocusedIndicatorColor = Color(0xffdde0e3),
                    focusedIndicatorColor = Color(0xff969eed),//获得焦点时外圈颜色
                    errorIndicatorColor = Color.Red,
                    placeholderColor = Color.Red,
                    textColor = Color.Black,
                    cursorColor = Color.Red
                ),
            )
            var position by rememberSaveable() { mutableStateOf("") }
            OutlinedTextField(
                value = position,
                onValueChange = { position = it },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                    .height(60.dp),
                label = { Text(text = "应聘岗位") },
                placeholder = {
                    Text(
                        text = "应聘岗位",
                        fontSize = 12.sp,
                        color = Color.Blue
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xfff6f9fc),  // 背景等相关颜色
                    unfocusedIndicatorColor = Color(0xffdde0e3),
                    focusedIndicatorColor = Color(0xff969eed),//获得焦点时外圈颜色
                    errorIndicatorColor = Color.Red,
                    placeholderColor = Color.Red,
                    textColor = Color.Black,
                    cursorColor = Color.Red
                ),
            )
            var age by rememberSaveable() { mutableStateOf("") }
            OutlinedTextField(
                value =age,
                onValueChange = { age = it },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                    .height(60.dp),
                label = { Text(text = "年龄") },
                placeholder = {
                    Text(
                        text = "年龄",
                        fontSize = 12.sp,
                        color = Color.Blue
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xfff6f9fc),  // 背景等相关颜色
                    unfocusedIndicatorColor = Color(0xffdde0e3),
                    focusedIndicatorColor = Color(0xff969eed),//获得焦点时外圈颜色
                    errorIndicatorColor = Color.Red,
                    placeholderColor = Color.Red,
                    textColor = Color.Black,
                    cursorColor = Color.Red
                ),
            )
            var key_info by rememberSaveable() { mutableStateOf("") }
            OutlinedTextField(
                value =key_info,
                onValueChange = { key_info = it },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                    .height(60.dp),
                label = { Text(text = "关键信息") },
                placeholder = {
                    Text(
                        text = "关键信息",
                        fontSize = 12.sp,
                        color = Color.Blue
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xfff6f9fc),  // 背景等相关颜色
                    unfocusedIndicatorColor = Color(0xffdde0e3),
                    focusedIndicatorColor = Color(0xff969eed),//获得焦点时外圈颜色
                    errorIndicatorColor = Color.Red,
                    placeholderColor = Color.Red,
                    textColor = Color.Black,
                    cursorColor = Color.Red
                ),
            )

            Button(
                onClick = {
                    var list= listOf<String>("小学",
                        "初中", "中专", "高中", "大专", "本科", "硕士", "博士"
                )
                    val map= HashMap<String,String>()
                    val mapInt=HashMap<String,Int>()
                    map["name"]=textName
                    mapInt["age"]=age.toInt()
                    mapInt["highest_education_level"]= list.indexOf(highest_education_level)
                    map["graduated_school"]=graduated_school
                    mapInt["working_years"]=working_years.toInt()
                    map["position"]=position
                    map["key_info"]=key_info
                    lifecycleScope.launch {
                            RxHttp.postJson("http://shuzhirecruit.nat300.top/enter_manually")
                                .add("name",textName)
                                .add("age",age.toInt())
                                .add("highest_education_level",list.indexOf(highest_education_level))
                                .add("graduated_school",graduated_school)
                                .add("working_years",working_years.toInt())
                                .add("position",position)
                                .add("key_info",key_info)
                                .addHeader("TYPE","enter_manually").toFlow<String>()
                                .catch {
                                //异常回调
                            }.collect {
                                //成功回调
                                "上传成功".toast()
                            }

                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff6772e5),
                    contentColor = Color.White
                )
            ) { Text(text = "提交") }

        }
    }
}



