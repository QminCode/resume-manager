package com.example.resume.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.lifecycle.lifecycleScope
import com.example.resume.bean.bean.Inform
import com.example.resume.bean.bean.InformItem
import kotlinx.coroutines.launch
import rxhttp.toAwait
import rxhttp.wrapper.param.RxHttp
import com.example.resume.R
/**
 *@author :yinxiaolong
 *@describe : com.example.resume.ui.fragment
 *@date :2023-07-10 15:39
 */
class TalentProfileFragment :Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                TalentProfileFragmentView()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @Composable
    fun TalentProfileFragmentView(){
        val itemList = remember() {
            mutableStateListOf<InformItem>()
        }
        LazyColumn(){
            item {
                Column (modifier = Modifier
                    .padding(10.dp)) {
                    var textName by rememberSaveable {
                        mutableStateOf("")
                    }
                    Text(text = "职位名", Modifier.padding(start = 30.dp), fontSize = 20.sp)
                    OutlinedTextField(
                        value = textName,
                        onValueChange = { textName = it },
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        label = { Text(text = "职位名") },
                        placeholder = {
                            Text(
                                text = "请输入职位名",
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
                    var textDescribe by rememberSaveable {
                        mutableStateOf("")
                    }
                    Text(
                        text = "职位描述",
                        Modifier.padding(start = 30.dp),
                        fontSize = 20.sp
                    )
                    OutlinedTextField(
                        value = textDescribe,
                        onValueChange = { textDescribe = it },
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp)
                            .height(200.dp),
                        label = { Text(text = "职位描述") },
                        placeholder = {
                            Text(
                                text = "请输入职位描述",
                                fontSize = 12.sp,
                                color = Color.Blue
                            )
                        },
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
                    val text_xueli = remember {
                        mutableStateOf("学历选择")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "学历选择:")
                        Spacer(modifier = Modifier.width(10.dp))
                        Selector(text_xueli)
                    }
                    var age by rememberSaveable {
                        mutableStateOf("")
                    }
                    OutlinedTextField(
                        value = age,
                        onValueChange = { age = it },
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        label = { Text(text = "年龄") },
                        placeholder = {
                            Text(
                                text = "请输入年龄",
                                fontSize = 12.sp,
                                color = Color.Blue
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
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
                        onClick = { lifecycleScope.launch {
                            var newItem=RxHttp.get("http://shuzhirecruit.nat300.top/similarity_v2/${textName}。${textDescribe}。${text_xueli.value}。${age}").toAwait<Inform>().await()
                            itemList.clear()
                            for (item in newItem){
                                itemList.add(item)
                            }
                        } }, Modifier.align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xff6772e5),
                            contentColor = Color.White
                        )
                    ) { Text(text = "提交") }
                }
            }

/*
            "姓名": "张三",
            "年龄": 19,
            "最高学历": "本科",
            "毕业院校": "哈啰工业大学",
            "工作年限": 2,
            "应聘岗位": "CEO",
            "关键描述信息": "曾在腾讯任职CEO三年",
            "岗位匹配相似度": 0.07044591341089992*/
            items(itemList) {
                LazyColumItem(it)

            }
            /*item {
                repeat(3){
                    LazyColumItem(InformItem(
                        age = 19,
                        graduated_school ="哈啰工业大学",
                        name = "张三",
                        key_info = "曾在腾讯任职CEO三年",
                        working_years = 2,
                        similarity = 0.070445913410,
                        position = "CEO",
                        highest_education_level = "本科",

                        ))
                }
            }*/

        }


    }


    @Composable
    fun LazyColumItem(item: InformItem) {
        Column(
            Modifier
                .padding(20.dp)
                .background(Color(0xfff6f9fc), shape = RoundedCornerShape(4.dp))
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = item.position, color = Color.Black, fontSize = 20.sp, fontStyle = FontStyle.Italic
            )
            Text(
                text = "相似度匹配:${item.similarity}",
            )
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "${item.name} |", color = Color(0xff6673e4))
                Text(text = "${item.highest_education_level} |", color = Color(0xff6673e4))
                Text(text = "${item.working_years}年", color = Color(0xff6673e4))

            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id =R.drawable.dot ), contentDescription =null,
                    Modifier.size(20.dp),
                contentScale = ContentScale.FillBounds)
                Text(text = "${item.graduated_school}")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id =R.drawable.dot ), contentDescription =null,
                    Modifier.size(20.dp),
                    contentScale = ContentScale.Fit)
                Text(text = "${item.age}岁")
            }
            Text(text = item.key_info, color = Color(0xff3355df),fontSize = 13.sp, modifier = Modifier
                .padding(3.dp)
                .background(Color.White, RoundedCornerShape(10.dp))
                .padding(3.dp))
        }

    }

    @Preview
    @Composable
    fun RadioButtonDemo(){
        val tags = arrayListOf("选项一", "选项二", "选项三", "选项四", "选项五")
        var selectedTag = remember { mutableStateOf("Null") }
        Column(modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
                .horizontalScroll(rememberScrollState()),           //加一个横向滚动修饰符
            ) {
                tags.forEach {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = it==selectedTag.value,           //是否选中
                            onClick = {
                                selectedTag.value=it
                            },                                          //点击事件
                            modifier=Modifier.padding(2.dp),            //修饰符
                            enabled=true,                               //是否启用
                            colors= RadioButtonDefaults.colors(
                                selectedColor= Color(0xff3355df),                 //选中的颜色
                                unselectedColor = Color.Black,              //未选中的颜色
                                disabledColor= Color.Green                   //禁用的颜色
                            )
                        )
                        Text(text = it,textAlign = TextAlign.Center)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }



    @Composable
    fun Selector(text:MutableState<String>) {
        var expanded by remember {
            mutableStateOf(false)
        }

        Column {
            Text(text = "${text.value} ▼", modifier = Modifier
                .padding(6.dp)
                .background(Color.Cyan, shape = RoundedCornerShape(6.dp))
                .clickable {
                    expanded = !expanded
                }
                .padding(5.dp) )

            DropdownMenu(
                expanded = expanded, onDismissRequest = {
                    expanded = false
                } ,
                properties = PopupProperties(
                    focusable = true,
                    dismissOnBackPress = false,
                    dismissOnClickOutside = true,
                    securePolicy = SecureFlagPolicy.SecureOn//是否可以截图。
                )
            ) {
                DropdownMenuItem(onClick = {
                    expanded = !expanded
                    text.value="博士"
                } ) {
                    Text(text = "博士")
                }

                DropdownMenuItem(onClick = {
                    expanded = !expanded
                    text.value="硕士"
                } ) {
                    Text(text = "硕士")
                }
                DropdownMenuItem(onClick = {
                    expanded = !expanded
                    text.value="本科"
                } ) {
                    Text(text = "本科")
                }
                DropdownMenuItem(onClick = {
                    expanded = !expanded
                    text.value="大专"
                } ) {
                    Text(text = "大专")
                }
                DropdownMenuItem(onClick = {
                    expanded = !expanded
                    text.value="中专"
                } ) {
                    Text(text = "中专")
                }
                DropdownMenuItem(onClick = {
                    expanded = !expanded
                    text.value="高中"
                } ) {
                    Text(text = "高中")
                }
                DropdownMenuItem(onClick = {
                    expanded = !expanded
                    text.value="初中"
                } ) {
                    Text(text = "初中")
                }
                DropdownMenuItem(onClick = {
                    expanded = !expanded
                    text.value="小学"
                } ) {
                    Text(text = "小学")
                }
            }
        }
    }

}