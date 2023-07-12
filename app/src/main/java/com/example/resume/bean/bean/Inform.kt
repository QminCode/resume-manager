package com.example.resume.bean.bean

/**
 *@author :yinxiaolong
 *@describe : com.example.resume.bean.bean
 *@date :2023-07-11 16:32
 */
class Inform : ArrayList<InformItem>()



/*"姓名": "张三",
"年龄": 19,
"最高学历": "本科",
"毕业院校": "哈啰工业大学",
"工作年限": 2,
"应聘岗位": "CEO",
"关键描述信息": "曾在腾讯任职CEO三年",
"岗位匹配相似度": 0.07044591341089992*/
data class InformItem(
    val age: Int,
    val graduated_school: String,
    val highest_education_level: String,
    val key_info: String,
    val name: String,
    val position: String,
    val similarity: Double,
    val working_years: Int
)