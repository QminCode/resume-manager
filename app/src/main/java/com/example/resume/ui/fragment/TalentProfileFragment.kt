package com.example.resume.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.resume.bean.bean.Inform
import com.example.resume.bean.bean.InformItem
import kotlinx.coroutines.launch
import rxhttp.toAwait
import rxhttp.wrapper.param.RxHttp

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

                    Button(
                        onClick = { lifecycleScope.launch {
                            var newItem=RxHttp.get("http://shuzhirecruit.nat300.top/similarity_v2/${textName}。${textDescribe}。").toAwait<Inform>().await()
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

            items(itemList) {
                Column(
                    Modifier
                        .padding(10.dp)
                        .background(Color(0xfff6f9fc), shape = RoundedCornerShape(4.dp))) {
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp))
                    Row(Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = it.name,
                        )
                        Text(
                            text = it.key_info,
                        )
                    }
                }
            }
        }


    }

    @Preview
    @Composable
    fun MyComposable() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Blue)
            )
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Green)
            )
        }
    }


    @Preview
    @Composable
    fun MyComposable1() {
        LazyColumn {
            items(10) { rowItem ->
                LazyRow {
                    items(10) { columnItem ->
                        Text(
                            text = "Row $rowItem - Column $columnItem",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}