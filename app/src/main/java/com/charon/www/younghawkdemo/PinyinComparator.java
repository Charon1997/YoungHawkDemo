package com.charon.www.younghawkdemo;

import android.util.Log;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import java.util.Comparator;

/**
 * Created by Charon on 2017/3/7.
 */
public class PinyinComparator implements Comparator<Object> {
    @Override
    public int compare(Object o, Object t1) {
        String name1 = (String) o;
        String name2 = (String) t1;
        String str1 = getPingYin(name1);
        String str2 = getPingYin(name2);
        return str1.compareTo(str2);
    }

    private String getPingYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] input = inputString.trim().toCharArray();// 把字符串转化成字符数组
        String output = "";
        try {
            for (char anInput : input) {          // \\u4E00是unicode编码，判断是不是中文
                if (Character.toString(anInput).matches("[\\u4E00-\\u9FA5]+")) {            // 将汉语拼音的全拼存到temp数组
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(anInput, format);            // 取拼音的第一个读音
                    output += temp[0];
                }          // 大写字母转化成小写字母
                else if (anInput > 'A' && anInput < 'Z') {
                    output += Character.toString(anInput);
                    output = output.toLowerCase();
                }
                output += Character.toString(anInput);
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
        return output;
    }
}


